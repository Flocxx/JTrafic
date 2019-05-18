package point;

public class Point {
	protected final double x;
	protected final double y;
	
	public Point(double xPos,  double  yPos) {
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
	
	public void print() {
		System.out.println("" + x + " : " + y);
	}
	
}
