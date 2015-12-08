package client;

import server.IAuctionServer;
import shared.AuctionListenerFactory;
import shared.AuctionType;
import shared.Constants;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class ClientMain {

    public static final String ADRES_SERWERA = "rmi://" + Constants.HOSTNAME + "/serwerAukcyjny";

    public static void main(String[] args) {

        System.out.println("Tworzę instancje klientów");

        // tworzenie klientów
        AuctionListenerFactory factory = new AuctionListenerFactory();
        IAuctionListener klient1 = factory.createInstance();
        IAuctionListener klient2 = factory.createInstance();

        // połączenie z serwerem

        IAuctionServer serwer = null;
        try {
            System.out.println("Łączę się z serwerem");
            Remote ro = Naming.lookup(ADRES_SERWERA);
            serwer = (IAuctionServer) ro;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // wystawianie przedmiotów
        try {
            System.out.println("Wystawiam dwa przedmioty");
            placeItemForBid(serwer, "owner", "ksiazka", "Ksiazka", 1.0, 5, AuctionType.ENGLISH_AUCTION);
            placeItemForBid(serwer, "owner", "tablet", "Tablet", 100.0, 10, AuctionType.ENGLISH_AUCTION);
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }

        // przebijanie
        try {
            System.out.println("Licytuję");
			placeBid(serwer, klient1, "Klient1", "ksiazka", 10.0);
			placeBid(serwer, klient2, "Klient2", "tablet", 200.0);
			placeBid(serwer, klient1, "Klient1", "tablet", 300.0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

        System.out.println("Oczekiwanie na zamkniecie aukcji");

        new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    public static void placeItemForBid(IAuctionServer server, String ownerName, String itemName, String itemDesc, double startBid, int auctionTime, AuctionType auctionType) throws RemoteException {
        server.placeItemForBid(ownerName, itemName, itemDesc, startBid, auctionTime, auctionType.getType());
    }

    public static void placeBid(IAuctionServer server, IAuctionListener client, String bidderName, String itemName, double bid) throws RemoteException {
        server.registerListener(client, itemName);
        server.bidOnItem(bidderName, itemName, bid);
    }

}
