package server;

import client.IAuctionListener;
import shared.BiddingStrategyValidator;
import shared.DutchAuctionBiddingStrategyValidator;
import shared.EnglishAuctionBiddingStrategyValidator;
import shared.Item;

import java.rmi.RemoteException;
import java.util.*;

public class AuctionServerImpl implements IAuctionServer {

    protected Map<String, List<IAuctionListener>> listeners = new HashMap<>();

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
        Item newItem = new Item(ownerName, itemName, itemDesc, startBid, null, auctionTime, validator, Calendar.getInstance());
        if(items.contains(newItem)) {
            throw new RemoteException("Item with that name already exists!");
        }
        else {
            items.add(newItem);
            System.out.println("Wystawiono przedmiot " + newItem + " na aukcje typu " + newItem.getValidator().getClass().getName() + ".");
        }
    }

    @Override
    public void bidOnItem(String bidderName, String itemName, double bid) throws RemoteException {
        Item item = findItem(itemName);
        if(item.validateBid(bid)) {
            item.bid(bidderName, bid);
            System.out.println("Uzytkownik " + item.getCurrentBiddersName() + " podniosl stawke do " + item.getCurrentBid() + " pozostaly czas " + item.getRemainingTime() + ".");
            notifyObservers(item, "rebid");
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
        if(findItem(itemName) != null) {
            if(!listeners.containsKey(itemName)) {
                listeners.put(itemName, Arrays.asList(al));
            }
            else {
                List<IAuctionListener> listn = (List<IAuctionListener>) this.listeners.get(itemName);
                if(!listn.contains(al)) {
                    listn.add(al);
                }
            }
            System.out.println("Dołączono obserwatora do przedmiotu " + itemName);
        }
    }

    public void closeAuction(String itemName) throws RemoteException {
        Item item = findItem(itemName);
        notifyObservers(item, "close");
        Server.getInstance().getItemList().remove(item);
        listeners.remove(itemName);
    }

    protected void notifyObservers(Item item, String action) throws RemoteException {
        for(IAuctionListener client: listeners.get(item.getName())) {
            client.update(item, action);
        }
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
