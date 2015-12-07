package shared;

import java.util.Observable;

public class Item extends Observable {
    protected String ownerName;
    protected String name;
    protected String description;
    protected double currentBid;
    protected String currentBiddersName;
    protected int remainingTime;
}
