package com.picpay.transactions.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.picpay.transactions.domain.Transaction;
import com.picpay.transactions.domain.dto.TransactionDTO;
import com.picpay.transactions.service.TransactionService;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionResource {
	
	@Autowired
	private TransactionService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<TransactionDTO> findUser(@PathVariable Integer id) {
		Transaction transaction = service.find(id);
		TransactionDTO transactionDTO = service.fromTransaction(transaction);
		return ResponseEntity.ok().body(transactionDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<TransactionDTO> insertTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
		Transaction transaction = service.fromTransactionDTO(transactionDTO);
		transaction = service.insert(transaction);
		transactionDTO = service.fromTransaction(transaction);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(transaction.getId()).toUri();
		return ResponseEntity.created(uri).body(transactionDTO);
	}
	
}
