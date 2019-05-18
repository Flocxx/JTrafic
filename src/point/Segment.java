package point;

import java.util.ArrayList;

public class Segment {
	private Point extremite1;
	private Point extremite2;
	private double x;
	private double y;
	
	private ArrayList<Point>listeIntersection = new ArrayList<Point>();
	
	public Segment(Point point1, Point point2) {
		extremite1 = point1;
		extremite2 = point2;
		x = point2.getX() - point1.getX();
		y = point2.getY() - point1.getY();
	}
	
	public void findAllIntersection(ArrayList<Segment>autreRoutes){
		for(Segment i:autreRoutes) {
			//Si l'autre route ne croise pas notre route a son extremite (ville)
			if(!extremiteIsIntersection(i)) {
				findOneIntersection(i);
				System.out.println("Interection presente");
			}
		}
	}
	
	public boolean extremiteIsIntersection(Segment autreSegment) {
		Point a = autreSegment.getExtremite1();
		Point b = autreSegment.getExtremite2();
		if(extremite1.ifEqual(a)) {	//Intersection aux extremite => ville
			listeIntersection.add(a);
			System.out.println("Intersection a l'extremite");
			return true;
		}else if(extremite2.ifEqual(b)) {
			listeIntersection.add(b);
			System.out.println("Intersection a l'extremite");
			return true;
		}else {
			return false;
		}
	}
	
	public void findOneIntersection(Segment autreSegment) {
		//On determine les deux extremites des routes
		Point a1 = this.getExtremite1();
		Point a2 = this.getExtremite2();
		Point b1 = autreSegment.getExtremite1();
		Point b2 = autreSegment.getExtremite2();
		double a = this.vecto(new Segment(a1,b1))/this.norme();
		double b = this.vecto(new Segment(a1,b2))/this.norme();
		double c = autreSegment.norme() + (autreSegment.norme()*b)/(a-b);
		double vraiRapport = c/autreSegment.norme();
		double resX = b1.getX() + (autreSegment.getX()*vraiRapport);
		double resY = b1.getY() + (autreSegment.getY()*vraiRapport);
		listeIntersection.add(new Point(resX,resY));
	}
	
	public double norme() {
		double res = Math.sqrt((x*x)+(y*y));
		return res;
	}
	
	public double vecto(Segment autreSegment) {
		double res = (x*autreSegment.getY()) - (y*autreSegment.getX());
		return res;
	}
	
	public boolean isEgal(Segment autreSegment) {
		//On ne tiens pas compte du sens ici
		if(extremite1.ifEqual(autreSegment.getExtremite1()) && extremite2.ifEqual(autreSegment.getExtremite2())) {
			return true;
		}else if(extremite1.ifEqual(autreSegment.getExtremite2()) && extremite2.ifEqual(autreSegment.getExtremite1())) {
			return true;
		}else {
			return false;
		}
	}
	
	public ArrayList<Point>getListeIntersection(){
		return listeIntersection;
	}
	
	public Point getExtremite1() {
		return extremite1;
	}
	
	public Point getExtremite2() {
		return extremite2;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public static void main(String[] args) {
		Segment a = new Segment(new Point(4,5), new Point(2,8));
		Segment b = new Segment(new Point(2,8), new Point(4,5));
		Segment c = new Segment(new Point(2,6), new Point(6,9));
		Segment d = new Segment(new Point(4,5), new Point(7,9));
		System.out.println(a.isEgal(a));
		System.out.println(a.isEgal(b));
		System.out.println(a.isEgal(c));
		a.findOneIntersection(c);
		a.extremiteIsIntersection(d);
		for(Point i:a.getListeIntersection()) {
			i.print();
		}
	}

}