package com.ugnext.training.expenditureapp;

public class TransactionsClass {
    private String amount;
    private String reason;
    private String transactionDate;
    private String type;

    public TransactionsClass() {
    }

    public TransactionsClass(String amount, String reason, String transactionDate, String type) {
        this.amount = amount;
        this.reason = reason;
        this.transactionDate = transactionDate;
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getType() {
        return type;
    }
}
