package server;

import client.IAuctionListener;
import shared.Item;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAuctionServer extends Remote, Serializable {
    void placeItemForBid(String ownerName, String itemName, String itemDesc, double startBid, int auctionTime, String auctionType) throws RemoteException;

    void bidOnItem(String bidderName, String itemName, double bid) throws RemoteException;

    Item[] getItems() throws RemoteException;

    void registerListener(IAuctionListener al, String itemName) throws RemoteException;
}
