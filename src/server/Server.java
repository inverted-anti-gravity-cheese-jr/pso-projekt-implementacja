package server;

import shared.Item;

import java.util.ArrayList;
import java.util.List;

public class Server {

    private static final Server instance = new Server();

    protected final List<Item> itemList = new ArrayList<>();

    public List<Item> getItemList() {
        return itemList;
    }

    public static Server getInstance() {
        return instance;
    }

}
