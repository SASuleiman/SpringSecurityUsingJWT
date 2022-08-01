package com.suleiman.TransactionEntitiyWithJWT.Controller;

import com.suleiman.TransactionEntitiyWithJWT.Dto.TransactionReference;
import com.suleiman.TransactionEntitiyWithJWT.Entity.Transaction;
import com.suleiman.TransactionEntitiyWithJWT.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction/api/v1/")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("/getAll")
    public List<Transaction> getAllTransaction() {
      return   transactionService.getAllTransaction();
    }

    @PostMapping
    public Transaction getTransactionByReference(@RequestBody TransactionReference transactionReference) {
        System.out.println(transactionReference.getTransactionReference());
        return  transactionService.getTransactionByReference(transactionReference.getTransactionReference());
    }

    @PostMapping("/addTransaction")
    public void addTransaction(@RequestBody Transaction transaction) {
        transactionService.addTransaction(transaction);
    }

    @DeleteMapping
    public void deleteTransaction(@RequestBody TransactionReference transactionReference) {
        String ref = transactionReference.getTransactionReference();
        transactionService.deleteTransaction(ref);
    }

    @PutMapping("/updateStatus")
    public void updateTransaction(@RequestBody TransactionReference transactionReference) {
        transactionService.updateTransactionStatus(transactionReference);
    }

//


}
