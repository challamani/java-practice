package com.javacorepractice.multithread;

import java.util.logging.Logger;

public class SyncDepositThread extends Thread {


    final static Logger logger = Logger.getLogger(DepositThread.class.getSimpleName());

    private SynchronizeAccount account;
    private double depositAmount;
    public SyncDepositThread(SynchronizeAccount account,String threadName,double depositAmount){
        super(threadName);
        this.account = account;
        this.depositAmount = depositAmount;
    }

    public SynchronizeAccount getAccount() {
        return account;
    }

    public void setAccount(SynchronizeAccount account) {
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
