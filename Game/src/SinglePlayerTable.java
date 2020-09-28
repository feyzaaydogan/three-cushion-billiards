import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class SinglePlayerTable extends JPanel implements MouseMotionListener, MouseListener, ActionListener {

	Ball a = new Ball(70);//first ball
	Ball b = new Ball(70);//second ball
	Ball c = new Ball(70);// third ball
	
	Ball ballstick;//which ball is with stick
	Sticks stick;
	
	faststick fast=new faststick();
	slowstick slow=new slowstick();
	
	String lose=new String("YOU LOSE");
    
	Line2D.Double cue = new Line2D.Double();
	Ellipse2D balla = new Ellipse2D.Double();//first ball
	Ellipse2D ballb = new Ellipse2D.Double();//second ball
	Ellipse2D ballc = new Ellipse2D.Double();//third ball

	JLabel stickpowerlabel;//stick power
	String message = "";
	JLabel scr;//score
	
	 int ab=0;
	 int ba=0;
	 int ac=0;
	 int ca=0;
	 int bc=0;
	 int cb=0;
	 int wall=0;
	 int score=0;

	boolean linedisappear;//avoid stick paint
	boolean clientb = false;
	boolean serverb = false;
	boolean firstwork = true;
	boolean white=false;
	boolean yellow=false;
	boolean controlscore=true;
	int clientport;
	int serverport;
	String IPAdress;
	String cheatcode;
	boolean cheatfa=false;
	JFrame closegame;



	Timer t = new Timer(33, this);

	SinglePlayerTable(JLabel power,JLabel score2, boolean clnt, boolean svr,String IP,String cheat,JFrame close,boolean slw,boolean fst) {
		a.setcordX(270);
		a.setcordY(700);
		b.setcordX(270);
		b.setcordY(500);
		c.setcordX(600);
		c.setcordY(500);


        scr=score2;
        stickpowerlabel = power;
		clientb = clnt;
		serverb = svr;
		IPAdress=IP;
		cheatcode=cheat;
		closegame=close;
		
		if(clientb==true) {
			clientport=12000;
			serverport=12001;
			ballstick=a;//white ball
			white=true;
		}
		
		else {
			clientport=12001;
			serverport=12000;
			ballstick=c;//yellow ball
			yellow=true;
		}
		
		if(clientb==false && serverb==false) {
			white=true;
			ballstick=a;//white ball
		}

		if (serverb == true) {
			t.start();
		}
		
		switch(cheatcode) {
		case "fa":
			cheatfa=true;
			break;
		case "ba":
			scr.setText(Integer.toString(score+30));
			score=score+30;
			break;
		}
		
		if(slw==true) {
			stick=slow;
		}
		
		else if(fst==true){
			stick=fast;
			
		}


	}


	public void paint(Graphics g) {
		super.paint(g);
		
		addMouseMotionListener(this);
		addMouseListener(this);
		
		Graphics2D g2 = (Graphics2D)g;
		if (linedisappear) {
			
            g2.setColor(Color.BLACK);
			cue.setLine(stick.getCuex1(), stick.getCuey1(), stick.getCuex2(), stick.getCuey2());
			g2.setStroke(new BasicStroke(13.0f));
			g2.draw(cue);
		}

		g2.setPaint(Color.WHITE);
		balla.setFrame(a.getcordX(), a.getcordY(), a.diameter, a.diameter);
		g2.fill(balla);

		g2.setPaint(Color.RED);
		ballb.setFrame(b.getcordX(), b.getcordY(), b.diameter, b.diameter);
		g2.fill(ballb);

		g2.setPaint(Color.YELLOW);
		ballc.setFrame(c.getcordX(), c.getcordY(), c.diameter, c.diameter);
		g2.fill(ballc);

		firstwork = false;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
    
	
	stick.pressed(ballstick, e.getX(), e.getY());
	a.counter = 0;
	b.counter = 0;
	c.counter = 0;
	a.setVelocity_x(0);
	a.setVelocity_y(0);
	b.setVelocity_x(0);
	b.setVelocity_y(0);
	c.setVelocity_x(0);
	c.setVelocity_y(0);

	linedisappear = true;
	repaint();

}
		
	public void mouseDragged(MouseEvent e) {

		stick.dragged(ballstick, e.getX(), e.getY());
		stickpowerlabel.setText(Integer.toString(10 + (int)Math.sqrt((stick.getCuex1() - a.getcordX())*
			(stick.getCuex1() - a.getcordX()) -
			(stick.getCuey1() - a.getcordY())*
			(stick.getCuey1() - a.getcordY()))));
		repaint();

	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
	t.start();
	stick.release(ballstick);
    controlscore=true;
	linedisappear = false;
	repaint();
}
	
	
	
	
	public void parse(String msg){
		
		String location[]=new String[6];
		for(int i=0;i<6;i++) {
    		location[i]="";
    	}
		
    	char mes[]=msg.toCharArray();
    	int j=0;
    	
    	
    	for(int i=0;i<mes.length;i++) {
    		
    		if(mes[i]!='!') {
    			location[j]=location[j]+mes[i];
    		}
    		else {
    			j++;
    			if(j==6) {
    				break;
    			}
    		}
    	}
    	
    	

		a.setcordX(Double.parseDouble(location[0]));
		a.setcordY(Double.parseDouble(location[1]));
		b.setcordX(Double.parseDouble(location[2]));
		b.setcordY(Double.parseDouble(location[3]));
		c.setcordX(Double.parseDouble(location[4]));
		c.setcordY(Double.parseDouble(location[5]));

		repaint();

    

    	
    	
    	
    }
	
	
	public void score(String s) {
		JFrame frame=new JFrame("Score");
		JLabel info=new JLabel(s);
		JButton button=new JButton();
		frame.setSize(1200, 1200);
	    frame.getContentPane().setBackground( Color.BLACK );
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(null);
	  	
	  	
	
		Font b = new Font("Courier",Font.BOLD,50);
		Font c = new Font("Courier",Font.BOLD,30);
		info.setBounds(200, 100, 800, 200);
	    info.setFont(b);
	  	info.setForeground(Color.white);
	  	
	  	 button=new JButton("MENU");
	  	 button.setFont(c);
	  	 button.setBounds(200,400,800,200);
	  	 button.setBackground(Color.WHITE);
	  	
	  	button.addActionListener(new ActionListener(){  
	  	    public void actionPerformed(ActionEvent e){  
	  	    	Registration r=new Registration();
	  	        r.createRegistration();
	  	    }  
	  	    });
	  	
	  	frame.add(info);
	  	frame.add(button);
	  	
	  	frame.setVisible(true);
	  	
	}
	

	public void actionPerformed(ActionEvent arg0) {

		if (!firstwork) {
			
			if (a.counter <= 0) {
				a.counter = 0;
			}

			if (b.counter <= 0) {
				b.counter = 0;
			}

			if (c.counter <= 0) {
				c.counter = 0;
			}

			if (!(a.counter>0  || b.counter>0 || c.counter>0)) {
				
				if (clientb == true) {
					if(controlscore==true) {
						if(wall>=3 && ab==1 && ac==1 && bc==0 && ba==0 && ca==0 && cb==0 ) {
						 score=score+30;
						 if(score<0) {
								score=0;
							}
						 scr.setText(Integer.toString(score));
						}
						else {
							if(cheatfa==true) {
						    score=score+40;
							}
							else {
							score=score-10;	
							}
							if(score<0) {
								score=0;
							}
							scr.setText(Integer.toString(score));
						}
					controlscore=false;			
					}

					clientb = false;
					serverb = true;

					try {
						
						Thread.sleep(2000);
						message=(String)(a.getcordX()+"!" + a.getcordY()+ "!"+ 
								b.getcordX()+"!" + b.getcordY()+ "!"+
								c.getcordX()+"!" + c.getcordY());
						
						if(score>50) {
							t.stop();
							message=lose;
							closegame.setVisible(false);
                                try {
								
								Scanner sc=new Scanner(new File("multiuser.txt"));
								String z="";
								while(sc.hasNext()) {
								z=sc.next();
								}
								  
								if(Integer.parseInt(z)<score) {
								try {
									FileWriter fileWriter=new FileWriter("multiuser.txt");
									fileWriter.write(Integer.toString(score));
									sc.close();
									fileWriter.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
								
								}
								
									}
                                
							catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
							score("YOU WON:"+ score);
							
							
						}
						
						DatagramSocket clientSocket = new DatagramSocket();
						
						
						InetAddress IPAddress = InetAddress.getByName(IPAdress);
						
						byte[] sendData = new byte[1024];
						
						
						
						
						sendData = message.getBytes();
						
						clientSocket.connect(IPAddress,clientport);
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, clientport);
						
						
						int i=100;
						while(i>0) {
						clientSocket.send(sendPacket);	
						i--;
						}
						
						
						clientSocket.close();
					
					}
					catch (Exception e) {
						e.printStackTrace();
					}


				}
				else if (serverb == true) {
					
					
					
					clientb = true;
					serverb = false;
					DatagramSocket serverSocket;
					
					DatagramPacket receivePacket = null;
					try {
						serverSocket = new DatagramSocket(serverport);
						
						
						byte[] receiveData = new byte[1024];
						
						while(serverSocket.isClosed() == false) {
						receivePacket = new DatagramPacket(receiveData, receiveData.length);
						
						try {
							
							serverSocket.receive(receivePacket);
							
						} catch (Exception e) {
							e.printStackTrace();
							System.exit(0);
						}
						
						
						serverSocket.close();
						}
						
						String message = new String(receivePacket.getData());
					     
						
						parse(message);
						
						
					}
					catch (Exception e) {
						t.stop();
						closegame.setVisible(false);
						try {
							
							Scanner sc=new Scanner(new File("multiuser.txt"));
							String z="";
							while(sc.hasNext()) {
							z=sc.next();
							}
							  
							if(Integer.parseInt(z)<score) {
							try {
								FileWriter fileWriter=new FileWriter("multiuser.txt");
								fileWriter.write(Integer.toString(score));
								sc.close();
								fileWriter.close();
							} 
							catch (IOException e1) {
								e1.printStackTrace();
							}
							
							}
							
								}
							
							
							
						catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						score("YOU LOSE:"+ score);
						 
					}

				t.stop();	

				}
				
				else if(clientb==false && serverb==false) {
					if(controlscore==true) {
						if(wall>=3 && ab==1 && ac==1 && bc==0 && ba==0 && ca==0 && cb==0 ) {
						 score=score+30;
						 if(score<0) {
								score=0;
							}
						 scr.setText(Integer.toString(score));
						}
						else {
							if(cheatfa==true) {
							    score=score+40;
								}
								else {
								score=score-10;	
								}
							if(score<0) {
								score=0;
							}
							scr.setText(Integer.toString(score));
							try {
								
								Scanner sc=new Scanner(new File("oneuser.txt"));
								String z="";
								while(sc.hasNext()) {
								z=sc.next();
								}
								  
								if(Integer.parseInt(z)<score) {
								try {
									FileWriter fileWriter=new FileWriter("oneuser.txt");
									fileWriter.write(Integer.toString(score));
									sc.close();
									fileWriter.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
								
								}
								
									}
								
								
								
							catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if(score>50) {
							
							closegame.setVisible(false);
							score("YOU WON:"+ score);
							
							
						}
						
					controlscore=false;
					}
					
					t.stop();
					
				}
				
				
				

			}
			if(white==true) {
				a.action(b);
				if(a.collisionballtoball==true) {
					ab++;
				}
				if(a.collisionwall==true) {
					wall++;
				}
				repaint();
				
				
			    b.action(a);
			    if(b.collisionballtoball==true) {
					ba++;
				}
				repaint();
				
				
				a.action(c);
				if(a.collisionballtoball==true) {
					ac++;
				}
				if(a.collisionwall==true) {
					wall++;
				}
				repaint();	
				
				
			    c.action(a);
			    if(c.collisionballtoball==true) {
					ca++;
				}
			    repaint();
			    
			    
			    b.action(c);
			    if(b.collisionballtoball==true) {
					bc++;
				}
			    repaint();
			    
			    
			    c.action(b);
			    if(c.collisionballtoball==true) {
					cb++;
				}
				
			   
				repaint();
			}
			else if(yellow==true) {
				c.action(b);
				if(c.collisionballtoball==true) {
					cb++;
				}
				if(c.collisionwall==true) {
					wall++;
				}
				repaint();
				
				
			    b.action(c);
			    if(b.collisionballtoball==true) {
					bc++;
				}
				repaint();
				
				
				c.action(a);
				if(c.collisionballtoball==true) {
					ca++;
				}
				if(c.collisionwall==true) {
					wall++;
				}
				repaint();	
				
				
			    a.action(c);
			    if(a.collisionballtoball==true) {
					ac++;
				}
			    repaint();
			    
			    
			    b.action(a);
			    if(b.collisionballtoball==true) {
					ba++;
				}
			    repaint();
			    
			    
			    a.action(b);
			    if(a.collisionballtoball==true) {
					ab++;
				}
				repaint();	
			}


			
		}

	}


	@Override
		public void mouseMoved(MouseEvent e) {


	}

	@Override
		public void mouseClicked(MouseEvent arg0) {

	}

	@Override
		public void mouseEntered(MouseEvent arg0) {

	}

	@Override
		public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}









}