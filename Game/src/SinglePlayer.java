import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SinglePlayer extends JFrame implements ActionListener {
	
	SinglePlayerTable p;//Panel
	JLabel speed;
	JLabel speed2;
	JLabel score;
	JLabel score2;
	JLabel time;
	JLabel time2;
	static int panelWidth=2000;
	static int panelHeight=1000;
    
	private Timer clock;
	private int second=0;
	private int minute=0;
	private int hour=0;
	
	private DecimalFormat fformat=new DecimalFormat("00");
	
	SinglePlayer(boolean client,boolean server,String IP,String cheatcode,JFrame closepage,Boolean slow,Boolean fast){
		ImageIcon img2=new ImageIcon("pool.jpg");
		JLabel background2=new JLabel("",img2,JLabel.CENTER);
		background2.setBounds(100,100,2300,1300);
		closepage=this;
		
		speed=new JLabel("Speed: ");
		speed2=new JLabel("0");
		
		score=new JLabel("Score: ");
		score2=new JLabel("0");
		
		time=new JLabel("Time: ");
		time2=new JLabel();
		
		p=new SinglePlayerTable(speed2,score2,client,server,IP,cheatcode,closepage,slow,fast);
		
		Font c = new Font("Snap ITC",Font.BOLD,40);	
		Color color=new Color(1,164,92);//Green
	    p.setBackground(color);
	    p.setBounds(250, 250, panelWidth, panelHeight);
	     
	    speed.setBounds(700,50, 200, 150);
	    speed2.setBounds(900,50,150,150);
	    
	    score.setBounds(250, 50, 200, 150);
	    score2.setBounds(450,50,200,150);
	    
	    
	    time.setBounds(1800, 50, 200, 150);
	    time2.setBounds(2000, 50, 500, 150);
	    
	    speed.setFont(c);
	    speed2.setFont(c);
	    score.setFont(c);
	    score2.setFont(c);
	    time.setFont(c);
	    time2.setFont(c);
	   
	    
	    speed.setForeground(Color.WHITE);
	    speed2.setForeground(Color.WHITE);
	    score.setForeground(Color.WHITE);
	    score2.setForeground(Color.WHITE);
	    time.setForeground(Color.WHITE);
	    time2.setForeground(Color.WHITE);

	    add(p);
	    add(speed);
	    add(speed2);
	    add(score);
	    add(score2);
	    add(time);
	    add(time2);
	    add(background2);
	    
	    
	    clock=new Timer(1000,this);
	    clock.start();
	    
	    
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==clock) {
			second++;
		}
		if(second==60) {
			minute++;
			second=0;
		}
		if(minute==60) {
			hour++;
			minute=0;
			second=0;
		}
		
		if(hour==24) {
			hour=0;
			minute=0;
			second=0;
		}
		time2.setText(fformat.format(hour)+":"+fformat.format(minute)+":"+fformat.format(second) );
		
	}
	
	

}