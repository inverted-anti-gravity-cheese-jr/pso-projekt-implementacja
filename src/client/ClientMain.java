package client;

import server.IAuctionServer;
import shared.AuctionType;
import shared.Item;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class ClientMain {

    public static String ADRES_SERWERA = "rmi://localhost/serwerAukcyjny";

    public static void main(String[] args) {

        // tworzenie klientów

        IAuctionListener klient1 = new AuctionListenerImpl();
        IAuctionListener klient2 = new AuctionListenerImpl();

        // połączenie z serwerem

        IAuctionServer serwer = null;
        try {
            Remote ro = Naming.lookup(ADRES_SERWERA);
            serwer = (IAuctionServer) ro;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // wystawianie przedmiotów
        try {
            placeItemForBid(serwer, "owner", "ksiazka", "Ksiazka", 1.0, 5, AuctionType.ENGLISH_AUCTION);
            placeItemForBid(serwer, "owner", "tablet", "Tablet", 100.0, 10, AuctionType.ENGLISH_AUCTION);
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }

        // przebijanie


    }

    public static void placeItemForBid(IAuctionServer server, String ownerName, String itemName, String itemDesc, double startBid, int auctionTime, AuctionType auctionType) throws RemoteException {
        server.placeItemForBid(ownerName, itemName, itemDesc, startBid, auctionTime, auctionType.getType());
    }

    public static void placeBid(IAuctionServer server, IAuctionListener client, String bidderName, String itemName, double bid) throws RemoteException {
        server.registerListener(client, itemName);
        server.bidOnItem(bidderName, itemName, bid);
    }

}
