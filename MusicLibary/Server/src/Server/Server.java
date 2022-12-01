//icsd13190 Tsoulos Sotirios

package Server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Shared.Interface;
import Shared.Connection;

//server me  RMI UnicastRemoteObject kai efarmogh interface
class AddServer extends UnicastRemoteObject implements Interface{

	protected AddServer() throws RemoteException {
		super();
		
	}

	//interface apo thn meria tou server 
	@Override
	public String sendartistname(String artistname) {
		// TODO Auto-generated method stub
		
		String result = null;
		
		//gia album an teleiwnei me 1
		if(artistname.endsWith("1"))
		{
			
			String originalartistname = artistname.substring(0, artistname.length()-1);//gia na paroume akrivws to onma tou kalhtexnh
		
		try {
			
			result = GetAlbum.searchalbum(originalartistname);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		// gia tragoudia an teleiwnei me 2
		else
		{
			
			String originalname = artistname.substring(0,artistname.length()-1);//gia na paroume akrivws to onma tou kalhtexnh
			
			try {
				
				result = GetSong.searchlistofsongs(originalname);
				
			} catch (IOException e) {
				
				
				e.printStackTrace();
				
			}
			
			
		}
		
		return result;
		
	}
	

}

public class Server{
	public static void main(String args[]) throws RemoteException, MalformedURLException, AlreadyBoundException
	{
		AddServer add = new AddServer();
		
	
		// create a Registry and bind RMI ID and AddServer object 
		Registry registry = LocateRegistry.createRegistry(Connection.RMI_PORT);
		
		registry.bind(Connection.RMI_ID, add);
		
		
	//	String result = add.sendartistname("40 Glocc2");
		
	//	System.out.println(result);
		
	//	Naming.rebind("rmi://localhost:5000/dhruv", add);
		
		System.out.println("Server up and running");
		
			
		
	}
}