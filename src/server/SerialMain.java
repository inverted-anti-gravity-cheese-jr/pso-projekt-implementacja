package server;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SerialMain {
    public static void main(String[] args) {
        // utworzenie serwera
        IAuctionServer serwer = new AuctionServerImpl();

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
