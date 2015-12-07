package server;

import shared.AuctionServerFactory;
import shared.Constants;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain {
    public static void main(String[] args) {

        System.out.println("Tworzę instancję serwera");

        // utworzenie serwera
        AuctionServerFactory factory = new AuctionServerFactory();
        IAuctionServer serwer = factory.createInstance();

        // uruchomienie daemona serwera
        try {
            System.out.println("Uruchamiam serwer");
            System.setProperty("java.rmi.server.hostname", Constants.HOSTNAME);
            Naming.bind("serwerAukcyjny", serwer);
//            IAuctionServer stub =
//                    (IAuctionServer) UnicastRemoteObject.exportObject(serwer);
//            Registry registry = LocateRegistry.getRegistry();
//            registry.rebind("serwerAukcyjny", stub);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

        // sprawdzanie czasu aukcji
        System.out.println("Tworzę licznik czasowy");
        AuctionTimer timer = new AuctionTimer((AuctionServerImpl) serwer);
        new Thread(timer).start();
    }
}
