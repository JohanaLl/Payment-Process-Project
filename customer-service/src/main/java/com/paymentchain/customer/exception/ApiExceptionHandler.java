package com.paymentchain.customer.exception;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.paymentchain.customer.common.StandarizedApiExceptionresponse;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleUnknownHostException(Exception ex) {
		StandarizedApiExceptionresponse standarizedApiExceptionresponse = new StandarizedApiExceptionresponse
				("TECNICO", "Imput Output Error", "1024", ex.getMessage());
		return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(standarizedApiExceptionresponse);
	}
}
