package server;

import shared.IAuctionListener;
import shared.Item;

import java.rmi.RemoteException;

public class AuctionServerImpl implements IAuctionServer {

    @Override
    public void placeItemForBid(String ownerName, String itemName, String itemDesc, double startBid, int auctionTime) throws RemoteException {

    }

    @Override
    public void bidOnItem(String bidderName, String itemName, double bid) throws RemoteException {

    }

    @Override
    public Item[] getItems() throws RemoteException {
        return null;
    }

    @Override
    public void registerListener(IAuctionListener al, String itemName) throws RemoteException {

    }
}
