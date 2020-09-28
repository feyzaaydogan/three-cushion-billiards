import java.awt.geom.Line2D;

abstract public class Sticks {
	
protected	double cuex1;
protected	double cuey1;
protected	double cuex2;
protected	double cuey2;
protected	double lineVectorx;
protected	double lineVectory;
protected	double k;




public double getCuex1() {
	return cuex1;
}

public void setCuex1(double cuex1) {
	this.cuex1 = cuex1;
}

public double getCuey1() {
	return cuey1;
}

public void setCuey1(double cuey1) {
	this.cuey1 = cuey1;
}

public double getCuex2() {
	return cuex2;
}

public void setCuex2(double cuex2) {
	this.cuex2 = cuex2;
}

public double getCuey2() {
	return cuey2;
}

public void setCuey2(double cuey2) {
	this.cuey2 = cuey2;
}

public double getLineVectorx() {
	return lineVectorx;
}

public void setLineVectorx(double lineVectorx) {
	this.lineVectorx = lineVectorx;
}

public double getLineVectory() {
	return lineVectory;
}

public void setLineVectory(double lineVectory) {
	this.lineVectory = lineVectory;
}

public double getK() {
	return k;
}

public void setK(double k) {
	this.k = k;
}

abstract public void dragged(Ball a,int dx,int dy);

	public void pressed(Ball a,double px,double py) {

		 k=0;	 
		 a.counter=0;
		 cuex1=a.getcordX()+a.diameter/2;
		 cuey1=a.getcordY()+a.diameter/2;
		 
		 cuex2=px;
		 cuey2=py;

		 lineVectorx=Math.abs(cuex1-cuex2);
		 lineVectory=Math.abs(cuey1-cuey2);
	}
	
	
	public void release(Ball a) {
		a.setVelocity_x((lineVectorx/(Math.sqrt(lineVectorx*lineVectorx+lineVectory*lineVectory))));//unit vectorx
		a.setVelocity_y((lineVectory/(Math.sqrt(lineVectorx*lineVectorx+lineVectory*lineVectory))));//unit vectory
		
		if((a.getcordX()+(a.diameter/2))<cuex2 && (a.getcordY()+(a.diameter/2))<cuey2) { //area IV
			a.setVelocity_x((-1)*a.getVelocity_x());
			a.setVelocity_y((-1)*a.getVelocity_y());
			
		}
		else if((a.getcordX()+(a.diameter/2))>cuex2 && (a.getcordY()+(a.diameter/2))<cuey2) { // area III
			a.setVelocity_x(a.getVelocity_x());
			a.setVelocity_y((-1)*a.getVelocity_y());
			
				
			}
		else if((a.getcordX()+(a.diameter/2))<cuex2 && (a.getcordY()+(a.diameter/2)>cuey2)) { // area I
			a.setVelocity_x((-1)*a.getVelocity_x());
			a.setVelocity_y(a.getVelocity_y());
			
		}
		
		else if((a.getcordX()+(a.diameter/2))>cuex2 && (a.getcordY()+(a.diameter/2))>cuey2) {  //area II
			a.setVelocity_x(a.getVelocity_x());
			a.setVelocity_y(a.getVelocity_y());
			
			
		}
		
		else if((a.getcordX()+(a.diameter/2))==cuex2 && (a.getcordY()+(a.diameter/2))>cuey2){//yukarý
			a.setVelocity_y(a.getVelocity_y());
		}
		
		else if((a.getcordX()+(a.diameter/2))==cuex2 && (a.getcordY()+(a.diameter/2))<cuey2){ // aþaðý
			a.setVelocity_y(-a.getVelocity_y());
		}
		
		else if((a.getcordY()+(a.diameter/2))==cuey2 && (a.getcordX()+(a.diameter/2))<cuex2) {
			a.setVelocity_x(-a.getVelocity_x());
		}
		else if((a.getcordY()+(a.diameter/2))==cuey2 && (a.getcordX()+(a.diameter/2))>cuex2) {
			a.setVelocity_x(a.getVelocity_x());
		}
		
		
		a.counter=Math.sqrt((cuex1-a.getcordX())*(cuex1-a.getcordX())+
				(cuey1-a.getcordY())*(cuey1-a.getcordY()));
		a.counter=a.counter/4;
		
	}
}