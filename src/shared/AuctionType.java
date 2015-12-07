package shared;

public enum AuctionType {

    ENGLISH_AUCTION("English"),
    DUTCH_AUCTION("Dutch");

    private String type;

    AuctionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
