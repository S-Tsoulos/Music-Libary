//icsd13190 Tsoulos Sotirios

package Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public  class ModifySong extends JFrame implements ActionListener{
    
    JFrame jf;
	
	JLabel heading,artistname,Selectchoice,modifyfile;
	
	JTextField artsttextfield,selectchoicetextfield,modifyfiletextfield;
	
	
	JButton display,modify;
	
	JTextArea textArea;
        
        
    public ModifySong(){
		jf= new JFrame("Modify");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(600, 450);
		//jf.setVisible(true);
		jf.setLayout(null);
		GUI();
	}
    
    
    public void GUI()//olos o kwdikas gui
	{
		
		heading = new JLabel("Music Directory");
		heading.setBounds(250, 20,900, 40);
		jf.add(heading);
		
		artistname = new JLabel("Artist Name :");
		artistname.setBounds(100,90,250,20);
		jf.add(artistname);
		
		Selectchoice = new JLabel("Select Your Song To Modify:");
		Selectchoice.setBounds(100,160,350,20);
		jf.add(Selectchoice);
                
                modifyfile = new JLabel("How You Want To Modify It:");
		modifyfile.setBounds(100,230,350,20);
		jf.add(modifyfile);
		
		artsttextfield = new JTextField();
		artsttextfield.setBounds(270, 90, 250, 20);
		jf.add(artsttextfield);
                
                selectchoicetextfield = new JTextField();
		selectchoicetextfield.setBounds(270,160,250,20);
		jf.add(selectchoicetextfield);
                
                modifyfiletextfield = new JTextField();
		modifyfiletextfield.setBounds(270,230,250,20);
		jf.add(modifyfiletextfield);
		
                
                modify = new JButton("Modify");
		modify.setBounds(470,280, 100,30);
		jf.add(modify);
		modify.addActionListener((ActionListener) this);
		
		
	
		
		jf.setVisible(true);
		
	}
    public void actionPerformed(ActionEvent arg) {
		
		
	Object src = arg.getSource();
         
        if(src == modify){
        
        File f=new File("C:\\Users\\picaflow\\Documents\\NetBeansProjects\\Server\\src\\Server\\listoula.txt");
        
        FileInputStream fs = null;
        InputStreamReader in = null;
        BufferedReader br = null;
        
        StringBuffer sb = new StringBuffer();
        
        String textinLine;
        
        try {
             fs = new FileInputStream(f);
             in = new InputStreamReader(fs);
             br = new BufferedReader(in);
            
            
            textinLine=br.readLine();
        while(textinLine != null)
        {
         sb.append(textinLine).append("n");
         textinLine=br.readLine(); 
        }
              String textToEdit = selectchoicetextfield.getText();
              int cnt = sb.indexOf(textToEdit);
              sb.replace(cnt,cnt+textToEdit.length(),modifyfiletextfield.getText());
              
              

              fs.close();
              in.close();
              br.close();
              //System.out.print(""+"Done you changed "+selectchoicetextfield.getText() + " to " + modifyfiletextfield.getText() );
              JOptionPane.showMessageDialog(this,"Done you changed it ");
                        
            } catch (FileNotFoundException e) {
              e.printStackTrace();
            } catch (IOException e) {
              e.printStackTrace();
            }
            
            try{
                FileWriter fstream = new FileWriter(f);
                BufferedWriter outobj = new BufferedWriter(fstream);
                outobj.write(sb.toString());
                outobj.close();
                
            }catch (Exception e){
              System.err.println("Error: " + e.getMessage());
            }
        
        
        
    }
    
    }
	
}
