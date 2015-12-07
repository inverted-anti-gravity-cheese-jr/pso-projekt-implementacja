package client;

import shared.Item;

import java.rmi.RemoteException;

public class AuctionListenerImpl implements IAuctionListener {

    @Override
    public void update(Item item, String action) throws RemoteException {
        if(action.equals("rebid")) {
            System.out.println("Uzytkownik " + item.getCurrentBiddersName() + " podniosl stawke do " + item.getCurrentBid() + " pozostaly czas " + item.getRemainingTime() + ".");
        }
        else {
            System.out.println("Aukcja przedmiotu " + item + " została zakończona.");
        }
    }
}
