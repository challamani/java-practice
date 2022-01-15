package com.core_java_practice.multithread;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SynchronizedExec {

	final static Logger logger = Logger.getLogger(SynchronizedExec.class.getSimpleName());
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SynchronizeAccount account = new SynchronizeAccount("321329","Sai Sree","Savings",10000.00);
		logger.info("Initial Account:"+account.toString());

    	new SyncDepositThread(account,"D-Sai younger brother",3000).start();
        new SyncDepositThread(account,"D-Father",3000).start();
        new SyncDepositThread(account,"D-Mother",3000).start();
        new SyncDepositThread(account,"D-Love",3000).start();
        new SyncDepositThread(account,"D-Friend-Mani",3000).start();
        new SyncDepositThread(account,"Some relatives",3000).start();
        new SyncDepositThread(account,"Some stranger",3000).start();
        new SyncDepositThread(account,"Gift from some source",3000).start();
       
        //functional programming, lambda expression, anonymous call.  
		new Thread( () -> {
			try{
				logger.info("lambda avail balance:"+ account.doDeposite(100.00));
			}catch (Exception e) {
				
			}
			
		} ).start(); 
		
		Thread waitingThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try{
		        	Thread.sleep(5000);
		        }catch(Exception e){
		        	logger.log(Level.SEVERE,"exception in join:"+e.getMessage());
		        }
			}
		});
        
        //let us assume above threads getting finished its taks within 5 minutes.
        try{
        	waitingThread.start();
        	waitingThread.join();
        	logger.info("Account Statement: "+account.toString());
        }catch(Exception e){
        	logger.log(Level.SEVERE,"exception in join:"+e.getMessage());
        }
	}

}
