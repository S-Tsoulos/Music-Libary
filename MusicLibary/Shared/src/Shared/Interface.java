//icsd13190 Tsoulos Sotirios

package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

// interface pou kanei  extend to RMI Remote interface
public interface Interface extends Remote{
	
	public String sendartistname(String artistname) throws RemoteException;

}