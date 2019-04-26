package graphique;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class maingraph extends Application{
	  public static void main(String[] args) {
	        launch(args);
	    }
	 public void start(Stage primaryStage) {
	       Group root = new Group();
	       Scene scene = new Scene(root, 800, 600, Color.WHITE);
	       primaryStage.setScene(scene);
	       primaryStage.setTitle("Ouais le rond");
	       primaryStage.show();
	       Group Villes = new Group();
	       Circle VilleA = new Circle(200.0,200.0,100.0, Color.GREEN);
	       CreateVille.setContour(VilleA, "red", 5);
	       root.getChildren().add(Villes);
	       Circle VilleB = new Circle(300, 400, 50, Color.RED);
	       Villes.getChildren().addAll(VilleB, VilleA);
	       System.out.println(VilleA.getCenterX());
	       
}
}
