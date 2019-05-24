package point;

import java.util.ArrayList;

public class Segment {
	private Point extremite1;
	private Point extremite2;
	private double x;
	private double y;
	private TypeRoute type;
	
	private ArrayList<Intersection>listeIntersection = new ArrayList<Intersection>();
	
	public Segment() {
		
	}
	
	public Segment(Point point1, Point point2) {
		extremite1 = point1;
		extremite2 = point2;
		x = point2.getX() - point1.getX();
		y = point2.getY() - point1.getY();
	}
	
	public void findAllIntersection(ArrayList<Segment>autreRoutes,boolean addExtremite){//si on veut compter les extremites
		for(Segment i:autreRoutes) {
			if(!isEgal(i)) {//On ne cherche pas d'intersection avec la meme route
				//Si l'autre route ne croise pas notre route a son extremite (ville)
				if(!extremiteIsIntersection(i,addExtremite)) {
					findOneIntersection(i);
				}
			}
		}
	}
	
	public boolean extremiteIsIntersection(Segment autreSegment,boolean addExtremite) {
		Point a = autreSegment.getExtremite1();
		Point b = autreSegment.getExtremite2();
		if(extremite1.ifEqual(a)) {	//Intersection aux extremite => ville
			if(addExtremite) {
				listeIntersection.add(new Intersection(a,this,autreSegment,true));
			}
			return true;
		}else if(extremite1.ifEqual(b)) {
			if(addExtremite) {
				listeIntersection.add(new Intersection(b,this,autreSegment,true));
			}
			return true;
		}else if(extremite2.ifEqual(a)) {
			if(addExtremite) {
				listeIntersection.add(new Intersection(b,this,autreSegment,true));
			}
			return true;
		}else if(extremite2.ifEqual(b)) {
			if(addExtremite) {
				listeIntersection.add(new Intersection(b,this,autreSegment,true));
			}
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
		if(appartientAuSegment(new Point(resX,resY))) {
			listeIntersection.add(new Intersection(resX,resY,this,autreSegment,false));
		}
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
	
	public boolean appartientAuSegment(Point testeur) {
		boolean res = false;
		if(testeur.getX() >= pointMinimumX().getX() && testeur.getX() <= pointMaximumX().getX()) {
			if(testeur.getY() >= pointMinimumY().getY() && testeur.getY() <= pointMaximumY().getY()) {
				res = true;
			}
		}
		return res;
	}
	
	public Point pointMinimumX() {
		if(extremite1.getX() < extremite2.getX()) {
			return extremite1;
		}else {
			return extremite2;
		}
	}
	
	public Point pointMinimumY() {
		if(extremite1.getY() < extremite2.getY()) {
			return extremite1;
		}else {
			return extremite2;
		}
	}
	
	public Point pointMaximumX() {
		if(extremite1.getX() > extremite2.getX()) {
			return extremite1;
		}else {
			return extremite2;
		}
	}
	
	public Point pointMaximumY() {
		if(extremite1.getY() > extremite2.getY()) {
			return extremite1;
		}else {
			return extremite2;
		}
	}
	
	public ArrayList<Intersection>getListeIntersection(){
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
	
	public void setType(TypeRoute largeurRoute) {
		type = largeurRoute;
	}
	
	public TypeRoute getType() {
		return type;
	}
	
	public void print() {
		System.out.println("" + extremite1.getX() + " : " + extremite1.getY());
		System.out.println("" + extremite2.getX() + " : " + extremite2.getY());
	}
	
	public static void main(String[] args) {
		Segment a = new Segment(new Point(4,5), new Point(2,8));
		Segment b = new Segment(new Point(2,8), new Point(4,5));
		Segment c = new Segment(new Point(2,6), new Point(6,9));
		Segment d = new Segment(new Point(4,5), new Point(7,9));
		a.findOneIntersection(c);
		a.extremiteIsIntersection(d,true);
		for(Point i:a.getListeIntersection()) {
			i.print();
		}
	}

}
