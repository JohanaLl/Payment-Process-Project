package com.paymentchain.trasaction.entity;

enum Status {

    PENDING("01"),
    SETTLED("02"),
    REJECTED("03"),
    CANCELLED("04");
	
	private final String code;
	
	Status(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
