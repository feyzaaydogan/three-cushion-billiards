import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Ball implements Balls{
	private double x;// ball x coord
	private double y;// ball y coord
	private double velocity_x;// ball direction x
	private double velocity_y;// ball direction y
	double counter; 
	boolean controlcollision=false;
	boolean control=true;
	boolean controlcollision2=false;
	int diameter;
	boolean collisionwall=false;
	boolean collisionballtoball=false;
	
	
	public double getcordX() {
		
		return x;
	}
	public void setcordX(double x) {
		this.x = x;
	}
	public double getcordY() {
		return y;
	}
	public void setcordY(double y) {

		this.y = y;
		
	}

	public double getVelocity_x() {
		return velocity_x;
	}
	public void setVelocity_x(double velocity_x) {
		this.velocity_x = velocity_x;
	}
	public double getVelocity_y() {
		return velocity_y;
	}
	public void setVelocity_y(double velocity_y) {
		this.velocity_y = velocity_y;
	}
	
	public void calculatemovement(Ball b) {
		double distancex=getcordX()-b.getcordX();
		double distancey=getcordY()-b.getcordY();
		double angle=getAngle(distancex,distancey);
		double tempvelocity_x;
		double tempvelocity_y;
		double tempx;
		double tempy;
		double tempcounter;
		     //System.out.println(angle);
		   if(angle <0 && angle>-90) {
			   
			  // System.out.println("1");
		    if(velocity_y>0) {
		    	angle=-1*angle;
		    	tempvelocity_x=velocity_x;
		    	tempvelocity_y=-1*velocity_y;
		    	rotate(angle,tempvelocity_x,tempvelocity_y);
		    	velocity_y=-1*velocity_y;
		    	velocity_x=velocity_x;
		    	
		    	tempx=Math.abs((getcordX()+diameter/2)-(b.getcordX()+b.diameter/2));
		    	tempy=Math.abs((getcordY()+diameter/2)-(b.getcordY()+b.diameter/2));
		    	
		    	b.velocity_x=-1*(tempx/(Math.sqrt(tempx*tempx+tempy*tempy)));
		    	b.velocity_y=(tempy/(Math.sqrt(tempx*tempx+tempy*tempy)));
		    	
		    	
		    	tempcounter=counter;
		    	counter=counter*0.75;
		    	b.counter=tempcounter;
		    	
		    }
		   else if(velocity_y<=0) {
			angle=angle;
		   	tempvelocity_x=velocity_x;
		   	tempvelocity_y=-1*velocity_y;
		   	rotate(angle,tempvelocity_x,tempvelocity_y);
		   	
		   	velocity_y=-1*velocity_y;
		   	velocity_x=velocity_x;
		   	
		   	tempx=Math.abs((getcordX()+diameter/2)-(b.getcordX()+b.diameter/2));
			tempy=Math.abs((getcordY()+diameter/2)-(b.getcordY()+b.diameter/2));
			
			
			b.velocity_x=-1*(tempx/(Math.sqrt(tempx*tempx+tempy*tempy)));
			b.velocity_y=(tempy/(Math.sqrt(tempx*tempx+tempy*tempy)));
			
			tempcounter=counter;
			counter=counter*0.75;
			b.counter=tempcounter;

		    }
		      
		    
		   }
		   else if(angle<-90 && angle>-180) {
			 // System.out.println("2");
			   if(velocity_y>0) {
			    	angle=-1*(180+angle);
			    	tempvelocity_x=velocity_x;
			    	tempvelocity_y=-1*velocity_y;
			    	rotate(angle,tempvelocity_x,tempvelocity_y);
			    	
			    	velocity_y=-1*velocity_y;
			    	velocity_x=velocity_x;
			    	
			    	tempx=Math.abs((getcordX()+diameter/2)-(b.getcordX()+b.diameter/2));
			    	tempy=Math.abs((getcordY()+diameter/2)-(b.getcordY()+b.diameter/2));
			    	
			    	b.velocity_x=(tempx/(Math.sqrt(tempx*tempx+tempy*tempy)));
			    	b.velocity_y=(tempy/(Math.sqrt(tempx*tempx+tempy*tempy)));
			    	
			    	
			    	tempcounter=counter;
			    	counter=counter*0.75;
			    	b.counter=tempcounter;
			    	
			    }
			   else if(velocity_y<=0) {
				angle=(180+angle);
			   	tempvelocity_x=velocity_x;
			   	tempvelocity_y=-1*velocity_y;
			   	rotate(angle,tempvelocity_x,tempvelocity_y);
			   	
			   	velocity_y=-1*velocity_y;
			   	velocity_x=velocity_x;
			   	
			   	tempx=Math.abs((getcordX()+diameter/2)-(b.getcordX()+b.diameter/2));
				tempy=Math.abs((getcordY()+diameter/2)-(b.getcordY()+b.diameter/2));
				
				
				b.velocity_x=(tempx/(Math.sqrt(tempx*tempx+tempy*tempy)));
				b.velocity_y=(tempy/(Math.sqrt(tempx*tempx+tempy*tempy)));
				
				tempcounter=counter;
				counter=counter*0.75;
				b.counter=tempcounter;

			    }  
		   }
		   
		   else if(angle< 180 && angle >90) {
			   if(velocity_y>=0) {
			    	angle=(180-angle);
			    	tempvelocity_x=velocity_x;
			    	tempvelocity_y=-1*velocity_y;
			    	rotate(angle,tempvelocity_x,tempvelocity_y);
			    	
			    	velocity_y=-1*velocity_y;
			    	velocity_x=velocity_x;
			    	
			    	tempx=Math.abs((getcordX()+diameter/2)-(b.getcordX()+b.diameter/2));
			    	tempy=Math.abs((getcordY()+diameter/2)-(b.getcordY()+b.diameter/2));
			    	
			    	b.velocity_x=(tempx/(Math.sqrt(tempx*tempx+tempy*tempy)));
			    	b.velocity_y=-1*(tempy/(Math.sqrt(tempx*tempx+tempy*tempy)));
			    	
			    	
			    	tempcounter=counter;
			    	counter=counter*0.75;
			    	b.counter=tempcounter;
			    	
			    }
			   else if(velocity_y<0) {
				angle=-1*(180+angle);
			   	tempvelocity_x=velocity_x;
			   	tempvelocity_y=-1*velocity_y;
			   	rotate(angle,tempvelocity_x,tempvelocity_y);
			   	
			   	velocity_y=-1*velocity_y;
			   	velocity_x=velocity_x;
			   	
			   	tempx=Math.abs((getcordX()+diameter/2)-(b.getcordX()+b.diameter/2));
				tempy=Math.abs((getcordY()+diameter/2)-(b.getcordY()+b.diameter/2));
				
				
				b.velocity_x=(tempx/(Math.sqrt(tempx*tempx+tempy*tempy)));
				b.velocity_y=-1*(tempy/(Math.sqrt(tempx*tempx+tempy*tempy)));
				
				tempcounter=counter;
				counter=counter*0.75;
				b.counter=tempcounter;

			    } 
			   
			 
			   
		   }  
		     else if(angle <90 && angle>0) {
		    	 if(velocity_y>=0) {
		 	    	angle=angle;
		 	    	tempvelocity_x=velocity_x;
		 	    	tempvelocity_y=-1*velocity_y;
		 	    	rotate(angle,tempvelocity_x,tempvelocity_y);
		 	    	
		 	    	velocity_y=-1*velocity_y;
		 	    	velocity_x=velocity_x;
		 	    	
		 	    	tempx=Math.abs((getcordX()+diameter/2)-(b.getcordX()+b.diameter/2));
		 	    	tempy=Math.abs((getcordY()+diameter/2)-(b.getcordY()+b.diameter/2));
		 	    	
		 	    	b.velocity_x=-1*(tempx/(Math.sqrt(tempx*tempx+tempy*tempy)));
		 	    	b.velocity_y=-1*(tempy/(Math.sqrt(tempx*tempx+tempy*tempy)));
		 	    	
		 	    	
		 	    	tempcounter=counter;
		 	    	counter=counter*0.75;
		 	    	b.counter=tempcounter;
		 	    	
		 	    }
		 	   else if(velocity_y<0) {
		 		angle=-1*angle;
		 	   	tempvelocity_x=velocity_x;
		 	   	tempvelocity_y=-1*velocity_y;
		 	   	rotate(angle,tempvelocity_x,tempvelocity_y);
		 	   	
		 	   	velocity_y=-1*velocity_y;
		 	   	velocity_x=velocity_x;
		 	   	
		 	   	tempx=Math.abs((getcordX()+diameter/2)-(b.getcordX()+b.diameter/2));
		 		tempy=Math.abs((getcordY()+diameter/2)-(b.getcordY()+b.diameter/2));
		 		
		 		
		 		b.velocity_x=-1*(tempx/(Math.sqrt(tempx*tempx+tempy*tempy)));
		 		b.velocity_y=-1*(tempy/(Math.sqrt(tempx*tempx+tempy*tempy)));
		 		
		 		tempcounter=counter;
		 		counter=counter*0.75;
		 		b.counter=tempcounter;

		 	    } 
		 
		}
		   
		     else if(distancey==0 || distancex==0) {
		    	 b.velocity_x=velocity_x;
		    	 b.velocity_y=velocity_y;
		    	 
		         tempcounter=counter;
		  		 counter=counter*0.75;
		  		b.counter=tempcounter;
		     }
	}
	    
	public void rotate(double degree,double xx,double yy) {
       

        double newX = xx*Math.cos(Math.toRadians(degree))-yy*Math.sin(Math.toRadians(degree));
        double newY = xx*Math.sin(Math.toRadians(degree))+yy*Math.cos(Math.toRadians(degree));

        velocity_x = newX;
        velocity_y = newY;
    }
	
	public double getAngle(double xx,double yy) {
		double angle=0;
		if(x==0) {
			if(y>0) {
				angle=90;
			}
			else if(y<0) {
				angle=-90;
			}
			
		}
		else if(y==0) {
			if(x>0) {
				angle=0;
			}
			else if(x<0) {
				angle=-180;
			}
			
		}
		
		else {
       angle= Math.toDegrees(Math.atan2(yy, xx));
		}
		return angle;
    }
	Ball(int diameter){
		this.diameter=diameter;
	}
	
	public double calculatedistance(Ball b) {
	  return 	((this.getcordX()+diameter/2)-(b.getcordX()+b.diameter/2))*
	            ((this.getcordX()+diameter/2)-(b.getcordX()+b.diameter/2))+
	            ((this.getcordY()+diameter/2)-(b.getcordY()+b.diameter/2))*
	            ((this.getcordY()+diameter/2)-(b.getcordY()+b.diameter/2));
	}
	
	public double calculatedistance1(Ball c,Ball b) {
		  return 	((c.getcordX()+c.diameter/2)-(b.getcordX()+b.diameter/2))*
		            ((c.getcordX()+c.diameter/2)-(b.getcordX()+b.diameter/2))+
		            ((c.getcordY()+c.diameter/2)-(b.getcordY()+b.diameter/2))*
		            ((c.getcordY()+c.diameter/2)-(b.getcordY()+b.diameter/2));
		}
	
	
	public void action(Ball b) {
		control=true;
		controlcollision=false;
		controlcollision2=false;
		collisionballtoball=false;
		collisionwall=false;
		double disttwoball;
	
        Ball nextiteration=new Ball(diameter);
        nextiteration.x=getcordX()+(counter*velocity_x);
        nextiteration.y=getcordY()+(counter*velocity_y);
        Ball step=new Ball(diameter);
        step.x=getcordX();
        step.y=getcordY();
        
     
 
while(controlcollision==false && !(calculatedistance1(step,nextiteration)<=(diameter/2+b.diameter/2)*(diameter/2+b.diameter/2) )) {
   
	step.x=step.getcordX()+velocity_x;
	step.y=step.getcordY()+velocity_y;
	
if(calculatedistance1(step,b)<(diameter/2+b.diameter/2)*(diameter/2+b.diameter/2))
	
{
collisionballtoball=true;
controlcollision=true;	
disttwoball=Math.sqrt(calculatedistance(b))-((diameter/2)+(b.diameter/2));  // topun içine geçmesin diye
x=getcordX()+(disttwoball*velocity_x);
y=getcordY()+(disttwoball*velocity_y);


  calculatemovement(b);
 
            
    
} 

 }


if(controlcollision==false) {
if(calculatedistance1(nextiteration,b)<=(diameter/2+b.diameter/2)*(diameter/2+b.diameter/2)) {
	collisionballtoball=true;
	
	controlcollision2=true;	
	disttwoball=Math.sqrt(calculatedistance(b))-((diameter/2)+(b.diameter/2));  // topun içine geçmesin diye
	x=getcordX()+(disttwoball*velocity_x);
	y=getcordY()+(disttwoball*velocity_y);


	    calculatemovement(b);
	 
	     
	    
	} 

}


if (controlcollision==false && controlcollision2==false) {
	
	
if(nextiteration.getcordX() < 0 ) {
	  collisionwall=true;
	  control=false;  // left   
	  x=0;
	  y=getcordY()+velocity_y*(Math.abs((getcordX()-0)/velocity_x));
	  velocity_x=-velocity_x;
} 

else if( nextiteration.getcordX()+diameter>SinglePlayer.panelWidth) { //right
	 collisionwall=true;
	  control=false;
	  
	  y=getcordY()+(velocity_y*(Math.abs(getcordX()-(SinglePlayer.panelWidth-diameter))/velocity_x));
	  x=SinglePlayer.panelWidth-diameter;
	  velocity_x=-velocity_x;
}

else if(nextiteration.getcordY()<0 ) {  // up
	  collisionwall=true;
	  control=false;
	  y=0;
	  x=getcordX()+velocity_x*(Math.abs(getcordY()/velocity_y));
	  velocity_y=-velocity_y;
}

else if(nextiteration.getcordY()+diameter>SinglePlayer.panelHeight) { // down
	  collisionwall=true;
	  control=false;
	  x=getcordX()+(velocity_x*(Math.abs(getcordY()-(SinglePlayer.panelHeight-diameter))/velocity_y));
	  y=SinglePlayer.panelHeight-diameter;
	 
	  velocity_y=-velocity_y;
	  
	  
	 
}
}   
             
	if(controlcollision==false && control==true && controlcollision2==false) {// collision olmayýnca bunu yapýyor
		
		x=getcordX()+(counter*velocity_x);
		y=getcordY()+(counter*velocity_y);
		
	}
	counter=counter-0.1;
	
	if(counter<=0) {
	       counter=0;
	        }
	  
	  if(b.counter<=0) {
		  b.counter=0;
	  }

  }
	
	  
	
	
	

	
	

}