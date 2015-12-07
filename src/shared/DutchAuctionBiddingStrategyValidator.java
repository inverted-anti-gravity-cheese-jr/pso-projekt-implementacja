package shared;

public class DutchAuctionBiddingStrategyValidator implements BiddingStrategyValidator {
    @Override
    public boolean validate(Item item, double bid) {
        return item.getCurrentBid() > bid;
    }
}
