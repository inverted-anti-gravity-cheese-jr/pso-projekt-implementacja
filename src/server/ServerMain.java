package server;

import shared.AuctionServerFactory;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain {
    public static void main(String[] args) {
        // utworzenie serwera
        AuctionServerFactory factory = new AuctionServerFactory();
        IAuctionServer serwer = factory.createInstance();

        // sprawdzanie czasu aukcji
        AuctionTimer timer = new AuctionTimer((AuctionServerImpl) serwer);
        new Thread(timer).start();

        // uruchomienie daemona serwera
        try {
            IAuctionServer stub =
                    (IAuctionServer) UnicastRemoteObject.exportObject(serwer);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("serwerAukcyjny", stub);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
