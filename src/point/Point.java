package point;

public class Point {
	protected double x;
	protected double y;
	
	public Point(double xPos,  double  yPos) {
		x = xPos;
		y = yPos;
	}
	
	public void setTo(double xPos,  double  yPos) {
		x = xPos;
		y = yPos;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public boolean ifEqual(Point comp) {
		if(x == comp.getX() && y == comp.getY()) {
			return true;
		}else {
			return  false;
		}
	}
	
	public double calculerDistance(Point autrePoint) {
		double res = Math.sqrt((autrePoint.getX()-x)*(autrePoint.getX()-x) + (autrePoint.getY()-y)*(autrePoint.getY()-y));
		return res;
	}
	
	public void print() {
		System.out.println("" + x + " : " + y);
	}
	
}
