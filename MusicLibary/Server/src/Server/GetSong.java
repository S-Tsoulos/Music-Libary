//icsd13190 Tsoulos Sotirios

package Server;

import static Server.GetAlbum.inputstream;
import static Server.GetAlbum.scanner;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Scanner;


//klash gia lista tragoudiwn kalhtexnh 
public class GetSong {
	
	
	
	static FileInputStream inputstream = null;
	
	static Scanner scanner = null;
	
	public static String searchlistofsongs(String artistname) throws IOException
	{
		
		String listofsongs = null;
		
		try{
		
		//diavasma arxeiou me  FileInputStream kai Scanner
		inputstream = new FileInputStream("C:\\Users\\picaflow\\Documents\\NetBeansProjects\\Server\\src\\Server\\listoula.txt");
		
		scanner = new Scanner(inputstream,"UTF-8");
		
		while(scanner.hasNext())
		{
			
			String line = scanner.nextLine();
			
			String fileelements[] = line.split("<SEP>");
			
			if(fileelements[1].equals(artistname))
			{
				listofsongs = fileelements[3];
				
				
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
		return listofsongs;
	}

}