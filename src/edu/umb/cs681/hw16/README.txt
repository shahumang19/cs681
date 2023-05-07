Online Auction App: 
An online auction app that allows multiple users to bid on the same item. In this app there
is a class called AuctionItem which stores the details of the item to be sold and AuctionHandler
class which places bids for a user in a multi-threaded environment.

Thread-Safe Issues:
The app could potentially generate a race condition if two users submit their bids 
at the same time. The race condition could result in the item being sold to the 
wrong bidder or for the wrong price.

Thread-Safe revisions:
The app didn't use locks initially. So I added ReentrantReadWriteLock to guard the shared variables.
This code would only consider one user's bid at a time while placing the bid.