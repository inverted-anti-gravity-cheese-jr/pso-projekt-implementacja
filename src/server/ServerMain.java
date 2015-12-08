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

            // sprawdzanie czasu aukcji
            System.out.println("Tworzę licznik czasowy");
            AuctionTimerImpl timer = new AuctionTimerImpl((AuctionServerImpl) serwer);
            //Naming.bind("serwerCzasowy", timer);

            IAuctionTimer timStub =
                    (IAuctionTimer) UnicastRemoteObject.exportObject(timer, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("serwerCzasowy", timStub);

            timStub.startTimer();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
