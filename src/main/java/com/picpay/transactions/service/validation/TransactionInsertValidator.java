package com.picpay.transactions.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.picpay.transactions.domain.dto.TransactionDTO;
import com.picpay.transactions.domain.dto.UserDTO;
import com.picpay.transactions.resource.exception.FieldMessageError;
import com.picpay.transactions.service.UserApiAccess;

public class TransactionInsertValidator implements ConstraintValidator<TransactionInsert, TransactionDTO> {
	
	@Autowired
	private UserApiAccess userApi;
	
	@Override
	public void initialize(TransactionInsert ann) {
	}

	@Override
	public boolean isValid(TransactionDTO transactionDTO, ConstraintValidatorContext context) {
		
		List<FieldMessageError> list = new ArrayList<>();
		
		UserDTO userDTO;
		if(transactionDTO.getPayerId() != null) {
			userDTO = userApi.getUser(transactionDTO.getPayerId());
			if(userDTO == null) {
				list.add(new FieldMessageError("payerId", "user pagador não cadastrado"));
			}
		}

		if(transactionDTO.getPayeeId() != null) {
			userDTO = userApi.getUser(transactionDTO.getPayeeId());
			if(userDTO == null) {
				list.add(new FieldMessageError("payeeId", "user recebedor não cadastrado"));
			}
		}
		
		for (FieldMessageError e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

