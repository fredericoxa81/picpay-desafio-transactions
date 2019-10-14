package com.picpay.transactions.resource.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessageError> errors = new ArrayList<>();

	public ValidationError(Integer code, String message) {
		super(code, message);
	}

	public List<FieldMessageError> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String messagem) {
		errors.add(new FieldMessageError(fieldName, messagem));
	}
}
