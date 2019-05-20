package point;

import java.util.ArrayList;

public class Intersection extends Point{
	
	private ArrayList<Segment>routesAdjacentes= new ArrayList<Segment>();
	private final boolean isVille;
	
	public Intersection(double xPos,  double  yPos, Segment route1, Segment route2, boolean isCity) {
		super(xPos, yPos);
		routesAdjacentes.add(route1);
		routesAdjacentes.add(route2);
		isVille = isCity;
	}
	
	public Intersection(Point pos,Segment route1, Segment route2, boolean isCity) {
		super(pos.getX(),pos.getY());
		routesAdjacentes.add(route1);
		routesAdjacentes.add(route2);
		isVille = isCity;
	}
	
	public void ajoutRouteAdjacente(Segment route) {
		routesAdjacentes.add(route);
	}
	
	public boolean isEgal(Intersection intersect) {
		if(x == intersect.getX() && y == intersect.getY()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean getIsVille() {
		return isVille;
	}

}
