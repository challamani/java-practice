package com.practice.threads.banking;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NonSynchronizationExec {

	final static Logger logger = Logger.getLogger(NonSynchronizationExec.class.getSimpleName());
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account account = new Account("321329","Sai Sree","Savings",10000.00);
		logger.info("Initial Account:"+account.toString());
		
    	new DepositThread(account,"D-Sai younger brother",3000).start();
        new DepositThread(account,"D-Father",3000).start();
        new DepositThread(account,"D-Mother",3000).start();
        new DepositThread(account,"D-Love",3000).start();
        new DepositThread(account,"D-Friend-Mani",3000).start();
        new DepositThread(account,"Some relatives",3000).start();
        new DepositThread(account,"Some stranger",3000).start();
        new DepositThread(account,"Gift from some source",3000).start();
        
        Runnable runnable = () -> {
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				logger.log(Level.SEVERE, "exception in join:" + e.getMessage());
			}
		};

        Thread waitingThread = new Thread(runnable);

        //let us assume above threads getting finished it's task within 5 minutes.
        try{
        	waitingThread.start();
        	waitingThread.join();
        	logger.info("Account Statement: "+account.toString());
        }catch(Exception e){
        	logger.log(Level.SEVERE,"exception in join:"+e.getMessage());
        }
        
	}

}
