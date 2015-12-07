package server;

import shared.Item;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;

public class AuctionTimer implements Runnable {

    public AuctionServerImpl server;

    public AuctionTimer(AuctionServerImpl server) {
        this.server = server;
    }

    @Override
    public void run() {
        List<Item> items = Server.getInstance().getItemList();
        while (true) {
            for (Item i : items) {
                Calendar endDate = ((Calendar) i.getStartDate().clone());
                endDate.add(Calendar.SECOND, i.getRemainingTime());
                if (endDate.before(Calendar.getInstance())) {
                    try {
                        System.out.println("Zamykam aukcje " + i + ", upłynął czas.");
                        server.closeAuction(i.getName());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
