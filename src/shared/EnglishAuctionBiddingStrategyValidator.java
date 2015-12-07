package shared;

/**
 * Created by wojtek on 07.12.15.
 */
public class EnglishAuctionBiddingStrategyValidator implements BiddingStrategyValidator {
    @Override
    public boolean validate(Item item, double bid) {
        return item.getCurrentBid() < bid;
    }
}
