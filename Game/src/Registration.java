import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Registration extends JFrame implements ActionListener {
	
	JLabel gameName;
	
	JTextField IP;
	String IPadd;
	
	JTextField cheat;
	String cheatcode;
	
	JButton user1;//Practice mode highscore
	JButton user2;//Multiuser highscore
	
	boolean slow=false;
	boolean fast=false;
	JRadioButton slowstick;
	JRadioButton faststick;
	ButtonGroup stick;
	
	boolean serverb=false;
	boolean clientb=false;
	JRadioButton server;//yellow ball
	JRadioButton client;//white ball
	ButtonGroup bg;
	
	JButton startGame;
	
	JFrame closepage;

	
	
	public void createRegistration() {
		
	  setLayout(null);
  	  setSize(1500,1500);
  	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	  getContentPane().setBackground( Color.BLACK );
  	  setResizable(false);
  	  
  	  Font b = new Font("Snap ITC",Font.BOLD,25);
  	  
  	  ImageIcon img=new ImageIcon("billoard.jpg");
  	  JLabel background=new JLabel("",img,JLabel.CENTER);
  	  background.setBounds(0,0,1500,1500);
  	  
  	  gameName=new JLabel("THREE CUSHION BILLIARDS");
  	  gameName.setFont(new Font("Snap ITC",Font.BOLD,50));
  	  gameName.setBounds(250,50,1000,100);
  	  gameName.setForeground(Color.white);
  	  
  	  IP=new JTextField("ENTER YOUR IP");
	  IP.setBounds(480,250,500,100);
	  IP.setBackground(Color.BLACK);
	  IP.setFont(b);
	  IP.setForeground(Color.WHITE);
	  IP.setHorizontalAlignment(JTextField.CENTER);
  	  
	  cheat=new JTextField("ENTER CODE");
	  cheat.setBounds(480,400,500,100);
	  cheat.setBackground(Color.BLACK);
	  cheat.setFont(b);
	  cheat.setForeground(Color.WHITE);
	  cheat.setHorizontalAlignment(JTextField.CENTER);
	  
	  user1=new JButton("Practice");
  	  user1.setFont(b);
  	  user1.setBounds(400,550,300,100);
  	  user1.setBackground(Color.WHITE);
  	  
  	  user2=new JButton("Multiplayer");
  	  user2.setFont(b);
  	  user2.setBounds(750,550,300,100);
  	  user2.setBackground(Color.WHITE);
  	  
  	 slowstick=new JRadioButton("SLOW CUE");
	 slowstick.setBounds(500, 700, 300, 100);
	 slowstick.setBackground(Color.BLACK);
	 slowstick.setFont(b);
	 slowstick.setForeground(Color.WHITE);
	  
	 faststick=new JRadioButton("FAST CUE");
	 faststick.setBounds(800, 700, 250, 100);
	 faststick.setBackground(Color.BLACK);
	 faststick.setFont(b);
	 faststick.setForeground(Color.WHITE);
	 
	  stick=new ButtonGroup();
	  stick.add(slowstick);
	  stick.add(faststick);
	  
  	  server=new JRadioButton("YELLOW BALL");
  	  server.setBounds(500, 800, 300, 100);
  	  server.setBackground(Color.BLACK);
  	  server.setFont(b);
  	  server.setForeground(Color.YELLOW);
  	  
  	  client=new JRadioButton("WHITE BALL");
  	  client.setBounds(800, 800, 250, 100);
  	  client.setBackground(Color.BLACK);
 	  client.setFont(b);
 	  client.setForeground(Color.WHITE);
 	  
 	  bg=new ButtonGroup();
  	  bg.add(server);
  	  bg.add(client);
  	  
  	  startGame=new JButton("START GAME");
  	  startGame.setFont(b);
  	  startGame.setBounds(350,1050,800,200);
  	  startGame.setBackground(Color.WHITE);
  	  
      add(background);
      add(gameName);
      add(IP);
      add(cheat);
      add(slowstick);
      add(faststick);
      add(user1);
      add(user2);
      add(server);
      add(client);
      add(startGame);
    
     
      user1.addActionListener(this);
      user2.addActionListener(this);
      startGame.addActionListener(this);
      
      setVisible(true);
  	  
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
        if(server.isSelected()) {
			serverb=true;
		}
        else if(client.isSelected()) {
        	clientb=true;
        }
        
        IPadd=IP.getText();
        cheatcode=cheat.getText();
        
        if(slowstick.isSelected()) {
			slow=true;
		}
        else if(faststick.isSelected()) {
        	fast=true;
        }
        
        if(e.getSource()==user1) {
        	
        	try {
				File file=new File("oneuser.txt");
				Scanner sc=new Scanner(new File("oneuser.txt"));
	            JOptionPane.showMessageDialog(null, "HIGH SCORE:" +sc.nextLine());			
					}
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}    
}
        
if(e.getSource()==user2) {
        	
        	try {
				File file=new File("multiuser.txt");
				Scanner sc=new Scanner(file);
                JOptionPane.showMessageDialog(null, "HIGH SCORE:" +sc.nextLine());
        	}
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}  
}

		if(e.getSource()==startGame) {
			
			SinglePlayer s=new SinglePlayer(clientb,serverb,IPadd,cheatcode,closepage,slow,fast);//Frame
			s.setSize(2500,1500);
			s.getContentPane().setBackground( Color.BLACK );
			s.setLayout(null);
			s.setResizable(false);
			s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			s.setVisible(true);
			setVisible(false);
			
		}
		
		
		
		
		
		
	}
}