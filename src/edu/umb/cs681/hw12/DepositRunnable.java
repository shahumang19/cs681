package edu.umb.cs681.hw12;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

public class DepositRunnable implements Runnable{
	private BankAccount account;
	private AtomicBoolean done = new AtomicBoolean(false);
	
	public DepositRunnable(BankAccount account) {
		this.account = account;
	}

	public void setDone(){
		this.done.set(true);
	}
	
	public void run(){
		for(int i = 0; i < 10; i++){
			if (this.done.get()){break;}
			account.deposit(100);
			try{
				Thread.sleep( Duration.ofSeconds(2) );
			}catch(InterruptedException e){
				continue;
			}
		}
	}
}
