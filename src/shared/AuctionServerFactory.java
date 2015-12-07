package shared;

import server.AuctionServerImpl;
import server.IAuctionServer;

public class AuctionServerFactory extends AbstractAuctionComponentsFactory<IAuctionServer> {
    @Override
    public IAuctionServer createInstance() {
        return new AuctionServerImpl();
    }
}
