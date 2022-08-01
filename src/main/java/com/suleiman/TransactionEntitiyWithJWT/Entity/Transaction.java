package com.suleiman.TransactionEntitiyWithJWT.Entity;

import com.suleiman.TransactionEntitiyWithJWT.Utils.TransactionStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private LocalDateTime localDateTimeOfTransaction;
    private TransactionStatus transactionStatus;
    private String amount;
    private String sourceAccountName;

    private String transactionReference;

    public Transaction() {
    }

    public Transaction(String sourceAccountNumber, String destinationAccountNumber, LocalDateTime localDateTimeOfTransaction, TransactionStatus transactionStatus, String transactionReference,String amount,String sourceAccountName) {
        this.id = id;
        this.sourceAccountNumber = sourceAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
        this.localDateTimeOfTransaction = localDateTimeOfTransaction;
        this.transactionStatus = transactionStatus;
        this.transactionReference = transactionReference;
        this.amount = amount;
        this.sourceAccountName = sourceAccountName;
    }

    public Long getId() {
        return id;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public LocalDateTime getLocalDateTimeOfTransaction() {
        return localDateTimeOfTransaction;
    }

    public void setLocalDateTimeOfTransaction(LocalDateTime localDateTimeOfTransaction) {
        this.localDateTimeOfTransaction = localDateTimeOfTransaction;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSourceAccountName() {
        return sourceAccountName;
    }

    public void setSourceAccountName(String sourceAccountName) {
        this.sourceAccountName = sourceAccountName;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", sourceAccountNumber='" + sourceAccountNumber + '\'' +
                ", destinationAccountNumber='" + destinationAccountNumber + '\'' +
                ", localDateTimeOfTransaction=" + localDateTimeOfTransaction +
                ", transactionStatus=" + transactionStatus +
                ", amount='" + amount + '\'' +
                ", sourceAccountName='" + sourceAccountName + '\'' +
                ", transactionReference='" + transactionReference + '\'' +
                '}';
    }
}
