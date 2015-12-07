package client;

public class ClientMain {

    public static void main(String[] args) {

        // tworzenie klientów

        IAuctionListener klient1 = new AuctionListenerImpl();
        IAuctionListener klient2 = new AuctionListenerImpl();

        // TODO: wystawianie przedmiotów

    }
}
