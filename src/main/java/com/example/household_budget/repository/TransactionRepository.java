package com.example.household_budget.repository;

import com.example.household_budget.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT COALESCE(SUM(t.amount),0) FROM Transaction t WHERE t.type = :kind")
    Long sumByType(@Param("kind")String type);
}
