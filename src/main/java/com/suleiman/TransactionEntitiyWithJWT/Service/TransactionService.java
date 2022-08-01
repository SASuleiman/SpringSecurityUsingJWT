package com.suleiman.TransactionEntitiyWithJWT.Service;

import com.suleiman.TransactionEntitiyWithJWT.Dao.TransactionReference;
import com.suleiman.TransactionEntitiyWithJWT.Entity.Transaction;
import com.suleiman.TransactionEntitiyWithJWT.Entity.TransactionCommandLineRunner;
import com.suleiman.TransactionEntitiyWithJWT.Repository.TransactionRepo;
import com.suleiman.TransactionEntitiyWithJWT.Utils.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    public static Logger logger = LoggerFactory.getLogger(TransactionService.class.getName());
    @Autowired
    TransactionRepo transactionRepo;

    public List<Transaction> getAllTransaction() {
        logger.info("getting a list of all transactions");
       return transactionRepo.findAll();
    }

    public Transaction getTransactionByReference(String ref) {
        logger.info("transaction reference : " + ref);
        boolean isPresent = checkAvailability(ref);
        if(!isPresent) {
          logger.error("the ref does not exist");
          throw new IllegalArgumentException(String.format("the reference number %s does not exist in the db",ref));
      }
            logger.info("successfully fetched transaction");
          return transactionRepo.findTransactionByTransactionReference(ref);
    }

    public void addTransaction(Transaction transaction) {
        String transactionReference = transaction.getTransactionReference();
        logger.info("transaction reference : " + transactionReference);
        boolean isPresent = checkAvailability(transactionReference);
        if(isPresent) {
            logger.error("the transaction reference already exists in the database");
            throw new  IllegalArgumentException("cannot add a transaction");
        }
        transactionRepo.save(transaction);
        logger.info("transaction added");
//        return String.format("Succesfully added transaction with reference number %s",transaction.getTransactionReference());
    }
    @Transactional
    public void deleteTransaction(String transactionRef) {
//        String ref = transaction.getTransactionReference();
        boolean availability = checkAvailability(transactionRef);
        if(!availability) {
            throw new IllegalArgumentException(" trying to delete a transaction that is not available in the db");
        }

        Transaction transactionToBeDeleted = transactionRepo.findTransactionByTransactionReference(transactionRef);
        transactionRepo.delete(transactionToBeDeleted);
        logger.info("the transaction has successfully been deleted");


    }

    @Transactional
    public void updateTransactionStatus(TransactionReference transactionReference) {
        String ref = transactionReference.getTransactionReference();
        logger.info(ref);
        logger.info(transactionReference.getTransactionStatus().getStatus());

        boolean availability = checkAvailability(ref);
        if(!availability) {
            throw new IllegalArgumentException("trying to update a transaction that is not available in the db");
        }

        Transaction transaction = transactionRepo.findTransactionByTransactionReference(ref);
        transaction.setTransactionStatus(transactionReference.getTransactionStatus());
        logger.info("successfully updated the transaction");

    }

    private boolean checkAvailability(String input) {
        boolean isPresent = Optional.ofNullable(transactionRepo.findTransactionByTransactionReference(input)).isPresent() ;
        return isPresent;
    }
}
