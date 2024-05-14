package com.poc2.TaskExecutor.service;

import com.poc2.TaskExecutor.entity.Transactions;
import com.poc2.TaskExecutor.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {
    private TransactionRepository transactionRepository;

    public List<Transactions> getAllTransactions(){
        System.out.println("transaction is called!!!!!!!!");
        return transactionRepository.findAll();
    }

    public List<Integer> getAllTransactionId(){
        return transactionRepository.getAllId();
    }

    @Transactional
    public void workWithTransactionId(List<Integer> batch){
        for(Integer a: batch){
           Optional<Transactions> transactions =  transactionRepository.findById(a);
           if (transactions.isPresent()){
               System.out.println(a+" Transaction is coming  thread"+ Thread.currentThread().getName());
               try{
                   transactionRepository.updateInThreadTransactionTable(a);
               }catch (Exception e){
                   System.out.println(e.getMessage());
               }
           }
        }
    }

}
