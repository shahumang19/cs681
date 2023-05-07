package edu.umb.cs681.hw16.unsafe;

public class AuctionItem {
    private int itemId;
    private String itemName;
    private double currentBid;
    private String currentBidder;

    public AuctionItem(int itemId, String itemName, double startingBid) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.currentBid = startingBid;
        this.currentBidder = "";
    }

    public void placeBid(String bidder, double amount) {
        if (amount > currentBid) {
            currentBid = amount;
            currentBidder = bidder;
            System.out.println("Bid accepted: " + bidder + " bid $" + amount + " for item " + itemId);
        } else {
            System.out.println("Bid rejected: " + bidder + " bid $" + amount + " for item " + itemId);
        }
    }

    public String getCurrentBidder() {
        return currentBidder;
    }

    public double getCurrentBid() {
        return currentBid;
    }
}