
public class slowstick extends Sticks{
	
public void dragged(Ball a,int dx,int dy) {
		
		if((a.getcordX()+a.diameter/2)<cuex2 && (a.getcordY()+a.diameter/2)<cuey2) { //area IV
			if(dx>cuex2 ) {
        		if(k<=0.69999) {
				k=k+0.00001;		
        		}
			}
			else {
				if(k>=0.00001)
				k=k-0.00001;
				
				
			}
			cuex1=(a.getcordX()+a.diameter/2)+k*lineVectorx;
			cuey1=(a.getcordY()+a.diameter/2)+k*lineVectory;
			}
			
			else if((a.getcordX()+a.diameter/2)>cuex2 && (a.getcordY()+a.diameter/2)<cuey2) { // area III
				if(dx<cuex2 ) {
	        		if(k<=0.69999) {
					k=k+0.00001;
					
					
	        		}
				}
				else {
					if(k>=0.00001)
					k=k-0.00001;
					
					
				}
				cuex1=(a.getcordX()+a.diameter/2)-k*lineVectorx;
				cuey1=(a.getcordY()+a.diameter/2)+k*lineVectory;
				
			}
		        
		        else if((a.getcordX()+a.diameter/2)<cuex2 && (a.getcordY()+a.diameter/2)>cuey2) { // area I
		        	if(dx>cuex2 ) {
		        		if(k<=0.69999) {
						k=k+0.00001;
						
						
		        		}
					}
					else {
						if(k>=0.00001)
						k=k-0.00001;
						
					}
		        	cuex1=(a.getcordX()+a.diameter/2)+k*lineVectorx;
		        	cuey1=(a.getcordY()+a.diameter/2)-k*lineVectory;
				
				}
		        else if((a.getcordX()+a.diameter/2)>cuex2  && (a.getcordY()+a.diameter/2)>cuey2) {  //area II
		        	if(dx<cuex2 ) {
		        		if(k<=0.69999) {
						k=k+0.00001;
						
						
		        		}
					}
					else {
						if(k>=0.00001)
						k=k-0.00001;
						
						
					}
		        	cuex1=(a.getcordX()+a.diameter/2)-k*lineVectorx;
		        	cuey1=(a.getcordY()+a.diameter/2)-k*lineVectory;
					
				}
}

}
