package client;

import shared.Item;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AuctionListenerImpl implements IAuctionListener {

    @Override
    public void update(Item item, String action) throws RemoteException {
        if(action.equals("rebid")) {
            Calendar endDate = ((Calendar) item.getStartDate().clone());
            endDate.add(Calendar.SECOND, item.getRemainingTime());


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            System.out.println("Uzytkownik " + item.getCurrentBiddersName() + " podniosl stawke do " + item.getCurrentBid() + " aukcja trwa do " + sdf.format(endDate.getTime()) + ".");
        }
        else {
            System.out.println("Aukcja przedmiotu " + item + " została zakończona.");
        }
    }
}
