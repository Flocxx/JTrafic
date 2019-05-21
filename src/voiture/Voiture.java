package voiture;

import point.*;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Application.Parameters;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Voiture extends Rectangle{
	private Point position;
	private Segment route;
	private ArrayList<Intersection> listeCroisement = new ArrayList<Intersection>();
	
	public Voiture() {
		super();
	}
	
	public Voiture(double width, double height) {
		super(width, height);
	}
	
	public Voiture(double x, double y, double width, double height, Segment road) {
		super(x, y, width, height);
		route = road;
	}
	
	public Voiture(double width, double height, Paint fill) {
		super(width, height, fill);
	}
	
	public Segment getRoute() {
		return route;
	}
	
	public void setPosition(Point newPosition) {
		position = newPosition;
	}
	
	public void setPosition(double x, double y) {
		position = new Point(x,y);
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void findAllIntersection() {
		ArrayList<Segment> tmp = new ArrayList<Segment>();
		tmp.add(route);
		route.findAllIntersection(tmp,false);
		listeCroisement = route.getListeIntersection();
	}
	
	public boolean checkDistanceNextIntersection(Point positionActuel, double distance) {
		position = positionActuel;
		boolean res = false;
		for(Intersection i:listeCroisement) {
			if(i.calculerDistance(position) < distance) {
				res = true;
			}
		}
		return res;
	}
	
}
