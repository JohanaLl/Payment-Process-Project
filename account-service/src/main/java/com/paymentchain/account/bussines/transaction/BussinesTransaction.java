package com.paymentchain.account.bussines.transaction;

import java.net.UnknownHostException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.paymentchain.account.entities.Account;
import com.paymentchain.account.service.AccountService;
import com.paymentchain.common.controller.CommonController;

import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

public class BussinesTransaction extends CommonController<Account, AccountService>{

	public BussinesTransaction(AccountService service) {
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
	 * Método para obtener todas las transacciones asocuadas a una cuenta
	 * @param id
	 * @return
	 * @throws UnknownHostException
	 */
	private List<?> getTansactions(String accountIban) {
		
		WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
				.baseUrl("http://TRASACTION-SERVICE/api/transaction")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		
		List<?> transactions = build.get()
				.uri(uriBuilder -> uriBuilder.path("/customer/transactions")
				.queryParam("iban", accountIban)
				.build())
				.retrieve()
				.bodyToFlux(Object.class)
				.collectList()
				.block();		
		
		return transactions;
	}
	
}
