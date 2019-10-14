package com.picpay.transactions.service.exception;

public class TransactionNotAuthException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TransactionNotAuthException(String msg) {
		super(msg);
	}
	
	public TransactionNotAuthException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
