package graphique;



import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class maingraph extends Application{
	  public static void main(String[] args) {
	        Application.launch(args);
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
	       
           Group Line = new Group();
           Line routeAB = new Line(VilleA.getCenterX(), VilleA.getCenterY(), VilleB.getCenterX(),VilleB.getCenterY());
           routeAB.setStroke(Color.YELLOW);
           routeAB.setStrokeWidth(5);
           Line.getChildren().add(routeAB);
           root.getChildren().add(Line);
           
           Group Voitures = new Group();
           
          Rectangle Voiture1 = new Rectangle(routeAB.getStartX(),routeAB.getStartY(),5.0,5.0);
          
         Group chemin = new Group();
          
          Path path = new Path();
          path.getElements().add(new MoveTo(routeAB.getStartX(), routeAB.getStartY()));
          path.getElements().add(new LineTo(routeAB.getEndX(),routeAB.getEndY()));
          path.setOpacity(0);
          //path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
          final PathTransition pathTransition = new PathTransition();
          pathTransition.setDuration(Duration.seconds(8.0));
          pathTransition.setDelay(Duration.seconds(2.0));
          pathTransition.setPath(path);
          pathTransition.setNode(Voiture1);
          pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
          pathTransition.setCycleCount(Timeline.INDEFINITE);
          pathTransition.setAutoReverse(true);
          root.getChildren().add(path);
          pathTransition.play();
          System.out.println(pathTransition);
          root.getChildren().add(chemin);
          chemin.getChildren().add(path);
          Voitures.getChildren().addAll(Voiture1);
          root.getChildren().add(Voitures);

}
}
