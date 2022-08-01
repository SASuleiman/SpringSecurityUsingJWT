package com.suleiman.TransactionEntitiyWithJWT.Entity;

import com.suleiman.TransactionEntitiyWithJWT.Repository.TransactionRepo;
import com.suleiman.TransactionEntitiyWithJWT.Utils.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Component
public class TransactionCommandLineRunner implements CommandLineRunner {

    @Autowired
    TransactionRepo transactionRepo;
 public static  Logger logger = LoggerFactory.getLogger(TransactionCommandLineRunner.class.getName());
    @Override
    public void run(String... args) throws Exception {
        Transaction successTxn = new Transaction("2115494978", "2345678956", LocalDateTime.now(), TransactionStatus.SUCCESS,"123456789","100000","Suleiman");
        transactionRepo.save(successTxn);

        Transaction pendingTxn = new Transaction("0059931556","4567895678",LocalDateTime.now(),TransactionStatus.PENDING,UUID.randomUUID().toString(),"400000","Kolapo");
            transactionRepo.save(pendingTxn);

        Transaction failedTxn = new Transaction("2345678965","56789023465",LocalDateTime.now(),TransactionStatus.FAILED,UUID.randomUUID().toString(),"450000","Ajibola");
        transactionRepo.save(failedTxn);

        logger.info("-----------------------Command line runner created some class-------------------");
        transactionRepo.findAll().stream().forEach((txn) -> logger.info(txn.toString()));
        logger.info("---------------------------------------------------------------------------------");


    }
}
