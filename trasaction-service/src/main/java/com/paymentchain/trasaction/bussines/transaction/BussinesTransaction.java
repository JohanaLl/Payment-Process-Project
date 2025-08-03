package com.paymentchain.trasaction.bussines.transaction;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException.NotFound;

import com.paymentchain.common.controller.CommonController;
import com.paymentchain.trasaction.entity.Transaction;
import com.paymentchain.trasaction.exception.BussinesRuleException;
import com.paymentchain.trasaction.service.TransactionService;
import com.paymentchain.trasaction.utils.UtilString;

import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Service
public class BussinesTransaction extends CommonController<Transaction, TransactionService>{

	public BussinesTransaction(TransactionService service) {
		super(service);
	}

	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	//Definir propiedades de timeOut para HTTPCLIENT
	HttpClient client = HttpClient.create()
			//Connection Timeout: is a period within which a connection between a client and a server must be stablished
			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.option(EpollChannelOption.TCP_KEEPIDLE, 300)
			.option(EpollChannelOption.TCP_KEEPINTVL, 60)
			.responseTimeout(Duration.ofSeconds(1))
			.doOnConnected(connection -> {
				connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
				connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
			});
	
	/**
	 * Método para crear una transacción
	 * @param trx
	 * @return
	 * @throws BussinesRuleException 
	 */
	public Transaction createTrx(Transaction trx) throws BussinesRuleException {
		
//		Optional.of(trx)
//			.filter(tran -> tran.getFee() > 0 && tran.getAmount() > tran.getFee())
//			.ifPresent(tran -> tran.setAmount(tran.getAmount() - tran.getFee()));
		
		//Validación de campos obligatorios
		if (UtilString.findIsNull(trx.getId().toString()) || UtilString.findIsNull(trx.getReference())) {
			BussinesRuleException bussinesRuleException = new BussinesRuleException("error.validation.transaction.id.ref.isempty", HttpStatus.PRECONDITION_FAILED);
			throw bussinesRuleException;
		}
				
		//No permite crear trx con ref duplicada
		Transaction transaction = service.findByReference(trx.getReference());
		if (transaction != null) {
			BussinesRuleException bussinesRuleException = new BussinesRuleException("error.validation.transaction.ref.duplicate", HttpStatus.PRECONDITION_FAILED);
			throw bussinesRuleException;
		}
		
		//No permite crear trx si la cuenta a la que se va a sociar no existe
		if (!getAccount(trx.getAccountIban())) {
			BussinesRuleException bussinesRuleException = new BussinesRuleException("error.validation.transaction.accoun.not.exist", HttpStatus.PRECONDITION_FAILED);
			throw bussinesRuleException;
		}
    	
		trx.setDescription(trx.updateType(trx));
		Transaction trxDB = service.create(trx);
		
    	//Actualizar el saldo de la cuenta
		updateAccountBalance(trx.getAccountIban(), trx.getAmount() - trx.getFee());
		
		return trxDB;
	}
	
	private boolean getAccount(String iban) {

		 try {
			 
			 WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
						.baseUrl("http://ACCOUNT-SERVICE/api/account")
						.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.build();
			 
			 build.get()
			 	.uri("/byIban/{iban}", iban) //@PathVariable
				.retrieve()
				.bodyToMono(Void.class)
				.block();
			 
			 return true;
			 
		} catch (NotFound e) {
			return false;
		}
	}
	
	private void updateAccountBalance(String accountIban, double amount) throws BussinesRuleException {

		 try {
			 
			 WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
						.baseUrl("http://ACCOUNT-SERVICE/api/account")
						.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.build();
			 
			 build.put()
			 	.uri("/modifyBanlance/{accountIban}/{amount}", accountIban, amount) //@PathVariable
				.retrieve()
				.bodyToMono(Void.class)
				.block();

		} catch (NotFound e) {
			BussinesRuleException bussinesRuleException = new BussinesRuleException("error.update.banlace.transaction", HttpStatus.PRECONDITION_FAILED);
			throw bussinesRuleException; 
		}
	}
	
}
