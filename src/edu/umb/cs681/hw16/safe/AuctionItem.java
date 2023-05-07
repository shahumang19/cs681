package edu.umb.cs681.hw16.safe;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AuctionItem {
    private int itemId;
    private String itemName;
    private double currentBid;
    private String currentBidder;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public AuctionItem(int itemId, String itemName, double startingBid) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.currentBid = startingBid;
        this.currentBidder = "";
    }

    public void placeBid(String bidder, double amount) {
        lock.writeLock().lock();
        try{
            if (amount > currentBid) {
                currentBid = amount;
                currentBidder = bidder;
                System.out.println("Bid accepted: " + bidder + " bid $" + amount + " for item " + itemId);
            } else {
                System.out.println("Bid rejected: " + bidder + " bid $" + amount + " for item " + itemId);
            }
        }finally{
            lock.writeLock().unlock();
        }
    }

    public String getCurrentBidder() {
        lock.readLock().lock();
        try{
            return currentBidder;
        }finally{
            lock.readLock().unlock();
        }
    }

    public double getCurrentBid() {
        lock.readLock().lock();
        try{
            return currentBid;
        }finally{
            lock.readLock().unlock();
        }
    }
}