package com.core_java_practice.multithread.examples.banking;

/**
 * Created by Manikanta Challa on 30-Mar-18.
 */
public class SynchronizeAccount {

    private String accountNo;
    private String accountHolder;
    private String accountType;
    private Double balance;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNo='" + accountNo + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                '}';
    }

    public SynchronizeAccount(String accountNo, String accountHolder, String accountType, Double balance) {
        this.accountNo = accountNo;
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.balance = balance;
    }

    public synchronized Double doWithdraw(double amount) throws Exception{
        if(amount > this.getBalance()){
            throw new Exception("Insufficient funds");
        }

        this.balance -= amount;
        return this.balance;
    }


    public synchronized Double doDeposit(double amount) throws Exception{
        if(0 > amount){
            throw new Exception("Invalid amount, must be positive integer");
        }

        this.balance += amount;
        return this.balance;
    }
}
