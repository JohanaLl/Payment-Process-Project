package com.paymentchain.account.exception;

import java.util.Locale;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentchain.account.common.StandarizedApiExceptionresponse;

@RestControllerAdvice
public class ApiExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleUnknownHostException(Exception ex) {
		StandarizedApiExceptionresponse standarizedApiExceptionresponse = new StandarizedApiExceptionresponse
				("TECNICO", "Imput Output Error", "1024", ex.getMessage());
		return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(standarizedApiExceptionresponse);
	}
	
	@ExceptionHandler(BussinesRuleException.class)
	public ResponseEntity<?> handleUnknownHostException(BussinesRuleException ex, Locale locale) {
		
		ErrorMessage errorMessage = null;
		String message = messageSource.getMessage(ex.getMessage(), null, locale);
		
	    try {
	    	errorMessage = objectMapper.readValue(message, ErrorMessage.class);
	    } catch (Exception e) {
	    	errorMessage.setCode("UNKNOWN");
	    }

		StandarizedApiExceptionresponse standarizedApiExceptionresponse = new StandarizedApiExceptionresponse(
				"BUSSINES", 
				"Error de validacion", 
				errorMessage.getCode(), 
				errorMessage.getMessage());
		
		return ResponseEntity.status(ex.getHttpStatus()).body(standarizedApiExceptionresponse);
	}
}
