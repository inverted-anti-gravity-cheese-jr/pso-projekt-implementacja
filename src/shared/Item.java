package shared;

import java.util.Observable;

public class Item extends Observable {
    protected String ownerName;
    protected String name;
    protected String description;
    protected double currentBid;
    protected String currentBiddersName;
    protected int remainingTime;
    protected  BiddingStrategyValidator validator;

    public Item(String ownerName, String name, String description, double currentBid, String currentBiddersName, int remainingTime, BiddingStrategyValidator validator) {
        this.ownerName = ownerName;
        this.name = name;
        this.description = description;
        this.currentBid = currentBid;
        this.currentBiddersName = currentBiddersName;
        this.remainingTime = remainingTime;
        this.validator = validator;
    }

    public String getName() {
        return name;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public void bid(String biddersName, double bid) {
        currentBid = bid;
        currentBiddersName = biddersName;
    }

    public BiddingStrategyValidator getValidator() {
        return validator;
    }

    public boolean validateBid(double bid) {
        return validator.validate(this, bid);
    }

    @Override
    public boolean equals(Object obj) {
        try {
            return ((Item) obj).name.equals(name);
        }
        catch (Exception e) {
            return false;
        }
    }
}
