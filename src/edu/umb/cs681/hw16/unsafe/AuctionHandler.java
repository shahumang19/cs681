package edu.umb.cs681.hw16.unsafe;

import java.util.concurrent.atomic.AtomicBoolean;

public class AuctionHandler implements Runnable {
    private AtomicBoolean done = new AtomicBoolean(false);
    private AuctionItem item;
    private String user;

    public AuctionHandler(AuctionItem item, String user){
        this.item = item;
        this.user = user;
    }

    public void setDone(){
        done.set(true);
    }

    @Override
    public void run() {
        while(!done.get()){
            item.placeBid(user, item.getCurrentBid()+Math.random()*100);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+" Interrupted!!!");
            }
        }
    }
}