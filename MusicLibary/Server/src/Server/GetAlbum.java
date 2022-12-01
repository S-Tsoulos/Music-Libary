//icsd13190 Tsoulos Sotirios

package Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

// klash gia album analoga me ton kalhtexnh 
public class GetAlbum {
	
	
	static FileInputStream inputstream = null;
	
	static Scanner scanner = null;
	
	// static gia na kaleite apo ton sever 
	public static String searchalbum(String artistname) throws IOException
	{
		
		String album = null;
		
		try{
		
		// diavasma arxeiou me  FileInputStream kai Scanner
		inputstream = new FileInputStream("C:\\Users\\picaflow\\Documents\\NetBeansProjects\\Server\\src\\Server\\listoula.txt");
		
		scanner = new Scanner(inputstream,"UTF-8");
		
		while(scanner.hasNext())
		{
			
			String line = scanner.nextLine();
			
			String fileelements[] = line.split("<SEP>");
			
			if(fileelements[1].equals(artistname))
			{
				album = fileelements[2];
				
				
			}
			
			
		}
		
		if(scanner.ioException() != null)
		{
			throw scanner.ioException();
		}
		
		}
		finally{
			
			if(inputstream != null)
			{
				inputstream.close();
			}
			if(scanner != null)
			{
				scanner.close();
			}
			
		}
		return album;
	}
	
	
	  

}