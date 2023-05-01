package edu.umb.cs681.hw12;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

public class WithdrawRunnable implements Runnable{
	private BankAccount account;
	private AtomicBoolean done = new AtomicBoolean(false);
	
	public WithdrawRunnable(BankAccount account) {
		this.account = account;
	}

	public void setDone(){
		this.done.set(true);
	}
	
	public void run(){
		try{
			for(int i = 0; i < 10; i++){
				if (this.done.get()){break;}
				account.withdraw(100);
				Thread.sleep( Duration.ofSeconds(2) );
			}
		}catch(InterruptedException exception){}
	}
}
