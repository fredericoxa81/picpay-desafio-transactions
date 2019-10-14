package com.picpay.transactions.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.picpay.transactions.service.validation.TransactionInsert;

@TransactionInsert
public class TransactionDTO  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull(message="Preenchimento obrigatório")
	@JsonProperty("payee_id")
	private Integer payeeId;
	
	@NotNull(message="Preenchimento obrigatório")
	@JsonProperty("payer_id")
	private Integer payerId;
	
	@JsonProperty("transaction_date")
	private Date transactionDate;
	
	@NotNull(message="Preenchimento obrigatório")
	private BigDecimal value;
	
	public TransactionDTO() {
		
	}

	public TransactionDTO(Integer id, Integer payeeId, Integer payerId, Date transactionDate, BigDecimal value) {
		super();
		this.id = id;
		this.payeeId = payeeId;
		this.payerId = payerId;
		this.transactionDate = transactionDate;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Integer payeeId) {
		this.payeeId = payeeId;
	}

	public Integer getPayerId() {
		return payerId;
	}

	public void setPayerId(Integer payerId) {
		this.payerId = payerId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
