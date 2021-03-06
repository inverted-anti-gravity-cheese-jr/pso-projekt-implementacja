package shared;

import java.util.Calendar;

public class Item {
    protected String ownerName;
    protected String name;
    protected String description;
    protected double currentBid;
    protected String currentBiddersName;
    protected int remainingTime;
    protected BiddingStrategyValidator validator;
    protected Calendar startDate;

    public Item(String ownerName, String name, String description, double currentBid, String currentBiddersName, int remainingTime, BiddingStrategyValidator validator, Calendar startDate) {
        this.ownerName = ownerName;
        this.name = name;
        this.description = description;
        this.currentBid = currentBid;
        this.currentBiddersName = currentBiddersName;
        this.remainingTime = remainingTime;
        this.validator = validator;
        this.startDate = startDate;
    }

    public Calendar getStartDate() {
        return startDate;
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

    public String getOwnerName() {
        return ownerName;
    }

    public String getDescription() {
        return description;
    }

    public String getCurrentBiddersName() {
        return currentBiddersName;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ownerName='" + ownerName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", currentBid=" + currentBid +
                ", currentBiddersName='" + currentBiddersName + '\'' +
                ", remainingTime=" + remainingTime +
                ", validator=" + validator +
                ", startDate=" + startDate +
                '}';
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
