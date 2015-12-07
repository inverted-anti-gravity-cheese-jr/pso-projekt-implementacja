package server;

import client.IAuctionListener;
import shared.BiddingStrategyValidator;
import shared.DutchAuctionBiddingStrategyValidator;
import shared.EnglishAuctionBiddingStrategyValidator;
import shared.Item;

import java.rmi.RemoteException;
import java.util.List;

public class AuctionServerImpl implements IAuctionServer {

    @Override
    public void placeItemForBid(String ownerName, String itemName, String itemDesc, double startBid, int auctionTime, String auctionType) throws RemoteException {
        List<Item> items = Server.getInstance().getItemList();
        BiddingStrategyValidator validator;
        if(auctionType.equals("English")) {
            validator = new EnglishAuctionBiddingStrategyValidator();
        }
        else {
            validator = new DutchAuctionBiddingStrategyValidator();
        }
        Item newItem = new Item(ownerName, itemName, itemDesc, startBid, null, auctionTime, validator);
        if(items.contains(newItem)) {
            throw new RemoteException("Item with that name already exists!");
        }
        items.add(newItem);
    }

    @Override
    public void bidOnItem(String bidderName, String itemName, double bid) throws RemoteException {
        Item item = findItem(itemName);
        if(item.validateBid(bid)) {
            item.bid(bidderName, bid);
        }
        else {
            String message;
            if(item.getValidator() instanceof EnglishAuctionBiddingStrategyValidator) {
                message = "In English Auction type, new bid must not be lower than the last bid.";
            }
            else {
                message = "In Dutch Auction type, new bid must nut be higher than the last bid.";
            }
            throw new RemoteException("Wrong bid value. " + message);
        }
    }

    @Override
    public Item[] getItems() throws RemoteException {
        return (Item[]) Server.getInstance().getItemList().toArray();
    }

    @Override
    public void registerListener(IAuctionListener al, String itemName) throws RemoteException {
        
    }

    protected Item findItem(String itemName) {
        for(Item item : Server.getInstance().getItemList()) {
            if(item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }
}
