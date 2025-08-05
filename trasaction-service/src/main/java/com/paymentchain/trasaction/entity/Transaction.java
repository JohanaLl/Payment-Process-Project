package com.paymentchain.trasaction.entity;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@Schema(description = "Entidad de Transacción")
public class Transaction {

	//Identificador unico de la transacción
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "ID único de la transacción", example = "1")
	private Long id;
	
	//Identificador unico de la transacción para el negocio
	@Schema(description = "Referencia única de la transacción", example = "TRX-2025-001")
	private String reference;
	
	//Número de cuenta bancaria del cliente
	@Schema(description = "Número IBAN de la cuenta", example = "ES91 2100 0418 4502 0005 1332")
	private String accountIban;
	
	//Fecha en que se realizazo la transacción
	@Schema(description = "Fecha de la transacción", example = "2025-07-31T10:30:00")
	private Date date;
	
	//Monto de la transacción, si el valor es negativo sera un debito(disminuye el saldo), si el valor es positivo sera un credito (aumenta el saldo)
	@Schema(description = "Monto de la transacción", example = "150.50")
	private double amount;
	
	//Cómision de la transacción
	@Schema(description = "Comisión de la transacción", example = "0.98")
	private double fee;
	
	//Descripción breve de la transacción
	@Enumerated(EnumType.STRING)
	@Schema(description = "Tipo de transacción", example = "ABONO")
	private Type description;
	
	//Guarda el estado de la transacción
	@Enumerated(EnumType.STRING)
	@Schema(description = "Estado de la transacción", example = "PENDING")
	private Status status;
	
	//Indica el canal por el que se realiza la transacción
	@Enumerated(EnumType.STRING)
	@Schema(description = "Canal de la transacción", example = "WEB")
	private Chanel chanel;
	
    // Método para actualizar el estado basado en la fecha
    public Status updateStatus(Transaction transaction) {
    	
    	Date fechaActual = new Date();
        
        this.status = transaction.getDate().after(fechaActual) 
        		? Status.PENDING
        		: Status.SETTLED;
        
        return status;
    }
    
    /**
     * Método para calcular el tipo de transacción de acuerdo al valor
     * @param transaction
     * @return
     */
    public Type updateType(Transaction transaction) {
    	this.description = transaction.getAmount() > 0 
    			? Type.ABONO
    			: Type.RETIRO;  
    	
    	return description;
    }
    
    
    public double getFee() {
    	return this.fee = 0.98;
    }
}
