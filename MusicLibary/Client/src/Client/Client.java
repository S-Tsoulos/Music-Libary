//icsd13190 Tsoulos Sotirios

package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Shared.Connection;
import Server.ModifySong;

//  implement ActionListener interface
public class Client implements ActionListener{
	//frames textfields buttons 
	JFrame jf;
	
	JLabel heading,artistname,Selectchoice,displayresult,displayalbum;
	
	JTextField artsttextfield;
	
	JComboBox choicecombo;
	
	JButton display,modify;
	
	JTextArea textArea;
	
	JScrollPane scrollpane;
	
	public String finalartistname;
	
	

	public Client(){
		
		jf= new JFrame("RMI Client");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(600, 450);
		jf.setLayout(null);
		GUI();
	}
	
	
	// Olos o kwdikas GUI
	public void GUI()
	{
		
		heading = new JLabel("Music Directory");
		heading.setBounds(250, 20,900, 40);
		jf.add(heading);
		
		artistname = new JLabel("Artist Name :");
		artistname.setBounds(100,90,250,20);
		jf.add(artistname);
		
		Selectchoice = new JLabel("Select Your Choice :");
		Selectchoice.setBounds(100,160,350,20);
		jf.add(Selectchoice);
		
		artsttextfield = new JTextField();
		artsttextfield.setBounds(270, 90, 250, 20);
		jf.add(artsttextfield);
		
		final DefaultComboBoxModel combobox = new DefaultComboBoxModel();
		combobox.addElement("Find Album");
		combobox.addElement("Find the List of songs");
		
		choicecombo = new JComboBox(combobox);
		choicecombo.setSelectedIndex(0);
		choicecombo.setBounds(270,150, 250, 30);
		jf.add(choicecombo);
		
		
		display = new JButton("Search");
		display.setBounds(470,240, 100,30);
		jf.add(display);
		display.addActionListener(this);
                
                modify = new JButton("Modify");
		modify.setBounds(470,280, 100,30);
		jf.add(modify);
		modify.addActionListener(this);
		
		displayresult = new JLabel();
		displayresult.setBounds(100,300,400,40);
		jf.add(displayresult);
		displayresult.setVisible(false);
		
		displayalbum = new JLabel();
		displayalbum.setBounds(100, 350, 500, 20);
		jf.add(displayalbum);
		displayalbum.setVisible(false);
		
		textArea = new JTextArea(300,300);
		scrollpane = new JScrollPane(textArea);
		scrollpane.setBounds(100,350, 500, 300);
		jf.add(scrollpane);
		scrollpane.setVisible(false);
	
		
		jf.setVisible(true);
		
	}


	// Events twn buttons an tha dialeksei album tragoudh h na epeksergastei
	@Override
	public void actionPerformed(ActionEvent arg) {// TODO Auto-generated method stub
		
		Object src = arg.getSource();
		
		if(src == display)
		{
			
			displayresult.setText(null);
			
			displayalbum.setText(null);
			
			textArea.setText(null);
			
			displayresult.setVisible(false);
			
			displayalbum.setVisible(false);
			
			scrollpane.setVisible(false);
		
			String artistname = artsttextfield.getText();
			
			System.out.println(artistname);
			
			
			//  An dialeksei to album vazei ton arithmo 1 pisw apo to onoma tou kallhtexth
			if(choicecombo.getSelectedIndex() == 0)
			{
				finalartistname = artistname + "1";
			}
			//  An dialeksei to tragoudi vazei ton arithmo 2 pisw apo to onoma tou kallhtexth
			else
			{
				finalartistname = artistname + "2";
				
			}
			
			
			try {
				// methodos sendtoservercall me to artistname me
				sendtoserver(finalartistname);
			} catch (MalformedURLException | RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
		}
                
                if(src == modify){
                    ModifySong w=new ModifySong();
                }
		
		
		
		
	}
	//me thodos p emfanizei ta apotelesmata 
	public void sendtoserver(String artistname) throws MalformedURLException, RemoteException, NotBoundException
	{
		// Sundesh me localhost k portnumber
		Registry registry = LocateRegistry.getRegistry("localhost",Connection.RMI_PORT);
		
		// reference tou interface 
		Shared.Interface common = (Shared.Interface)registry.lookup(Connection.RMI_ID);
		
		
		
		//methodos interface
		String finalresult = common.sendartistname(artistname);
		
		System.out.println("Final Result :"+finalresult);
		
		// An emfanisei album
		if(artistname.endsWith("1"))
		{
			displayresult.setText("Album of Artistname :"+artistname.substring(0, artistname.length()-1));
			
			displayresult.setVisible(true);
			
			displayalbum.setText(finalresult);
			
			
			displayalbum.setVisible(true);
			
			
		}
		// an emfanisei tragoudi
		else
		{
			displayresult.setText("List Of Songs of Artistname :"+artistname.substring(0, artistname.length()-1));
			
			displayresult.setVisible(true);
			
			String songs[] = finalresult.split("<I>");
			
			for(int i=0;i<songs.length;i++)
			{
				System.out.println("songs :"+songs[i]);
				
				textArea.append(songs[i]+"\n");
			}
			
			scrollpane.setVisible(true);

		}
		
		
	}
	// kalw client
	public static void main(String args[])
	{
		
		Client c1 = new Client();
		
	}

}