package graphique;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;


public class CreateVille{
private double centreX;
private double centreY;
private double rayon;
private String color;
protected static String nom;
private Circle cercle;


public double getCentreX() {
	return centreX;
}


public void setCentreX(double centreX) {
	this.centreX = centreX;
}


public double getCentreY() {
	return centreY;
}


public void setCentreY(double centreY) {
	this.centreY = centreY;
}


public double getRayon() {
	return rayon;
}


public void setRayon(double rayon) {
	this.rayon = rayon;
}


public void setCenter(double centreX, double centreY, Circle nom) {
	nom.setCenterX(centreX);
	nom.setCenterY(centreY);
}
 
public static void setContour(Circle ville, String color, int epaisseur) {
    ville.setStrokeType(StrokeType.OUTSIDE);
    ville.setStroke(Color.web(color, 0.16));
    ville.setStrokeWidth(epaisseur);
}

public String getColor() {
	return color;
}

public void setColor(String color) {
	this.color = color;
}

static String getNom() {
	return nom;
}

public void setNom(String nom) {
	CreateVille.nom = nom;
}

public Circle getCercle() {
	return cercle;
}

public void setCercle(Circle cercle) {
	this.cercle = cercle;
}
}
