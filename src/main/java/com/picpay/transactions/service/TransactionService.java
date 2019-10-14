package com.picpay.transactions.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.picpay.transactions.domain.Transaction;
import com.picpay.transactions.domain.dto.TransactionDTO;
import com.picpay.transactions.repository.TransactionRepository;
import com.picpay.transactions.service.exception.ObjectNotFoundException;
import com.picpay.transactions.service.exception.TransactionNotAuthException;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository repository;
	
	@Value("${transaction.value.max}")
	private String transactionValueMax;
	
	public List<Transaction> findAll(){
		return repository.findAll();
	}
	
	public Transaction find(Integer id) {
		Optional<Transaction> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Transação não encontrada! Id: " + id + ", Tipo: " + Transaction.class.getName()));
	}
	
	public Transaction insert(Transaction obj) {
		if(obj.getValue().compareTo(new BigDecimal(this.transactionValueMax)) >= 0) {
			throw new TransactionNotAuthException("Transação não autorizada");
		}
		obj.setId(null);
		obj.setTransactionDate(new Date());
		return repository.save(obj);
	}
	
	public TransactionDTO fromTransaction(Transaction transaction) {
		return new TransactionDTO(transaction.getId(), transaction.getPayeeId(), transaction.getPayerId(),
				transaction.getTransactionDate(), transaction.getValue());
	}

	public Transaction fromTransactionDTO(TransactionDTO transactionDTO) {
		return new Transaction(transactionDTO.getId(), transactionDTO.getPayeeId(), transactionDTO.getPayerId(),
				transactionDTO.getTransactionDate(), transactionDTO.getValue());
	}

}
