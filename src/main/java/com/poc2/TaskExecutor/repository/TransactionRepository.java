package com.poc2.TaskExecutor.repository;

import com.poc2.TaskExecutor.entity.Transactions;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
    @Query(value = "SELECT transactionID from Production.TransactionHistory", nativeQuery = true)
    List<Integer> getAllId();

    @Query(value = "INSERT INTO ThreadTransaction values (:transactionId)", nativeQuery = true)
    void saveInThreadTransactionTable(@Param("transactionId") Integer a);

    @Modifying
    @Query(value = "UPDATE PT SET PT.ActualCost = 200.00 FROM Production.TransactionHistory as PT WHEre TransactionID = :transactionId", nativeQuery = true)
    void updateInThreadTransactionTable(@Param("transactionId") Integer a);
}
