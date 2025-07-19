package com.paymentchain.customer.bussines.transaction;

import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.paymentchain.common.controller.CommonController;
import com.paymentchain.customer.entity.Customer;
import com.paymentchain.customer.entity.CustomerProduct;
import com.paymentchain.customer.exception.BussinesRuleException;
import com.paymentchain.customer.service.CustormerService;

import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Service
public class BussinesTransaction extends CommonController<Customer, CustormerService>{

	
	public BussinesTransaction(CustormerService service) {
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
			//Response Timeout: The maximun time we wait to recieve a response after sending a request 
			.responseTimeout(Duration.ofSeconds(1))
			//Read and write timeout: A read timeout accurs when no data was readwithin a certain
			//period of time, while the write timeout when a write operation cannot finish at a specific time
			.doOnConnected(connection -> {
				connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
				connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
			});
	
	
	/**
	 * Método para Obtener todos los productos de un cliente
	 * @param id
	 * @return
	 * @throws UnknownHostException 
	 */
	private String getProductName(long id) throws UnknownHostException {
		
		String name = "";
		try {
			WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
					.baseUrl("http://PRODUCT-SERVICE/api/product")
					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.build();
			
			JsonNode block = build.method(HttpMethod.GET)
					.uri("/" + id)
					.retrieve()
					.bodyToMono(JsonNode.class)
					.block();
			
			name = block.get("name").asText();
			
		} catch (WebClientResponseException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				return "";
			} else {
				throw new UnknownHostException(e.getMessage());
			}
		}
		
		return name;
		
	}
	
	/**
	 * Método para obteber todas las transacciones asociadas a un cliente
	 * @param accountIban
	 * @return
	 */
	private List<?> getTansactions(String accountIban) {
		
		WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
				.baseUrl("http://TRASACTION-SERVICE/api/transaction")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		
//		List<?> transactions = build.get()
//				.uri(uriBuilder -> uriBuilder.path("/customer/transactions")
//				.queryParam("iban", accountIban)
//				.build())
//				.retrieve()
//				.bodyToFlux(Object.class)
//				.collectList()
//				.block();
		
		List<?> transactions = (List<?>) build.method(HttpMethod.GET)
				.uri("/" + accountIban)
				.retrieve()
				.bodyToMono(JsonNode.class)
				.block();;
		
		return transactions;
	}
	
	/**
	 * Metodo que devuelve un cliente por su código y asigna los productos y las transacciones del cliente
	 * @param code
	 * @return
	 */
	public Customer getFullCustomer(String code) {
		
		Customer customer = service.findByCode(code);
		List<CustomerProduct> products = customer.getProducts();
		
		//Obtener el nombre de cada producto
		products.forEach(x -> {
			String productName;
			try {
				productName = getProductName(x.getId());  //obtener el nombre y id de cada cliente
				x.setProductName(productName); 
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			
		});
		
//		List<?> transactions = getTansactions(customer.getIban());
//		customer.setTransaccions(transactions);
		
		return customer;
	}
	
	/**
	 * Metodo para crear clientes y asignarle los products que llegan en la creación
	 * @throws BussinesRuleException 
	 */
	public Customer postCreate(Customer customer) throws BussinesRuleException, UnknownHostException {
		if (customer.getProducts() != null) {
			for (Iterator <CustomerProduct> itCP = customer.getProducts().iterator(); itCP.hasNext();) {
				CustomerProduct dto = itCP.next();
				String productName = getProductName(dto.getProductId());
				if (productName.isBlank()) {
					BussinesRuleException bussinesRuleException = new BussinesRuleException("1025", "Error validacion, producto con id " + dto.getProductId() + " no existe", HttpStatus.PRECONDITION_FAILED);
					throw bussinesRuleException;
				} else {
					dto.setCustomer(customer);
				}
				
			}
		}
		
		Customer custormerDB = service.save(customer);
		return custormerDB;
	}
}
