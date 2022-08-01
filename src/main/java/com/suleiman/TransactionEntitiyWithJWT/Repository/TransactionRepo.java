package com.suleiman.TransactionEntitiyWithJWT.Repository;

import com.suleiman.TransactionEntitiyWithJWT.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long> {
Transaction findTransactionByTransactionReference(String transactionReference);

}
