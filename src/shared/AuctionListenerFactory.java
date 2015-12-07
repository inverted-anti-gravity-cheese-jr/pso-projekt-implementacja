package shared;

import client.AuctionListenerImpl;
import client.IAuctionListener;

public class AuctionListenerFactory extends AbstractAuctionComponentsFactory<IAuctionListener> {
    @Override
    public IAuctionListener createInstance() {
        return new AuctionListenerImpl();
    }
}
