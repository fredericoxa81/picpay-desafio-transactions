package com.picpay.transactions.service.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = TransactionInsertValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionInsert {
	String message() default "Erro de validação nos campos";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
