package com.suleiman.TransactionEntitiyWithJWT.Dto;


import com.suleiman.TransactionEntitiyWithJWT.Utils.TransactionStatus;

public class TransactionReference {
    private String transactionReference;
    private TransactionStatus  transactionStatus;

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
