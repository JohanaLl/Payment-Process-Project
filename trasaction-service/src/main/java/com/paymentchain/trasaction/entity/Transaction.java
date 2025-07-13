package com.paymentchain.trasaction.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transaction {

	//Identificador unico de la transacción
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Identificador unico de la transacción para el negocio
	private String reference;
	
	//Número de cuenta bancaria del cliente
	private String accountIban;
	
	//Fecha en que se realizazo la transacción
	private Date date;
	
	//Monto de la transacción, si el valor es negativo sera un debito(disminuye el saldo), si el valor es positivo sera un credito (aumenta el saldo)
	private double amount;
	
	//Cómision de la transacción
	private double fee;
	
	//Descripción breve de la transacción
	private String description;
	
	//Guarda el estado de la transacción
	@Enumerated(EnumType.STRING)
	private Status status;
	
	//Indica el canal por el que se realiza la transacción
	@Enumerated(EnumType.STRING)
	private Chanel chanel;
	
    // Método para actualizar el estado basado en la fecha
    public Status updateStatus(Transaction transaction) {
    	
    	Date fechaActual = new Date();
        
        this.status = transaction.getDate().after(fechaActual) 
        		? Status.PENDING
        		: Status.SETTLED;
        
        return status;
    }
}
