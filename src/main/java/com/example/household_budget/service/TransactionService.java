package com.example.household_budget.service;

import com.example.household_budget.entity.Transaction;
import com.example.household_budget.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findAll(){ return transactionRepository.findAll();}

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }

    public Transaction findById(Long id){
        return transactionRepository.findById(id).orElseThrow();
    }

    public Long sumByType(String type){
        return transactionRepository.sumByType(type);
    }

}
