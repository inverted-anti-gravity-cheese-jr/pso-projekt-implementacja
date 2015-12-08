package shared;

public class EnglishAuctionBiddingStrategyValidator implements BiddingStrategyValidator {
    @Override
    public boolean validate(Item item, double bid) {
        return item.getCurrentBid() < bid;
    }
}
