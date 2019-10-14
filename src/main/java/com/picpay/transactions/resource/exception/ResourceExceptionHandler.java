package com.picpay.transactions.resource.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.picpay.transactions.service.exception.ObjectNotFoundException;
import com.picpay.transactions.service.exception.TransactionNotAuthException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> exception(Exception e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

	@ExceptionHandler(TransactionNotAuthException.class)
	public ResponseEntity<StandardError> transactionNotAuthException(TransactionNotAuthException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		
		ValidationError err = new ValidationError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação nos campos");
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

}
