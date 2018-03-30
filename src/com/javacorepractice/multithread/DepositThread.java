package com.javacorepractice.multithread;

import java.util.logging.Logger;

/**
 * Created by Manikanta Challa on 30-Mar-18.
 */
public class DepositThread extends Thread {

    final static Logger logger = Logger.getLogger(DepositThread.class.getSimpleName());

    private Account account;
    private double depositAmount;
    public DepositThread(Account account,String threadName,double depositAmount){
        super(threadName);
        this.account = account;
        this.depositAmount = depositAmount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void run() {
        try {
            
            logger.info("Account Balance at [" + currentThread().getName() + "] " + account.doDeposite(this.depositAmount));
        } catch (Exception e) {
            logger.info("exception " + e.getMessage());
        }
    }
}
