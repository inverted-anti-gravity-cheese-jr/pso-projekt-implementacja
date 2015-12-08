package server;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAuctionTimer extends Remote, Serializable {

    void startTimer() throws RemoteException;
}
