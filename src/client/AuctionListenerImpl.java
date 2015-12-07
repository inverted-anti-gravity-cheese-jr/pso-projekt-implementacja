package client;

import shared.Item;

import java.rmi.RemoteException;

public class AuctionListenerImpl implements IAuctionListener {

    @Override
    public void update(Item item) throws RemoteException {
        System.out.println("Uzytkownik " + item.getCurrentBiddersName() + " podniosl stawke do " + item.getCurrentBid() + " pozostaly czas " + item.getRemainingTime() + ".");
    }
}
