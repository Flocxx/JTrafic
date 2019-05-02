package graphique;



import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Application.Parameters;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
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

public class maingraph extends Application{
	  public static void main(String[] args) {
	        Application.launch(args);
	    }
	  
	   private PathTransition generatePathTransition(final Shape shape, final Path path)
	   {
	      final PathTransition pathTransition = new PathTransition();
	      pathTransition.setDuration(Duration.seconds(8.0));
	      pathTransition.setDelay(Duration.seconds(2.0));
	      pathTransition.setPath(path);
	      pathTransition.setNode(shape);
	      pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
	      pathTransition.setCycleCount(Timeline.INDEFINITE);
	      pathTransition.setAutoReverse(true);
	      pathTransition.play();
	      return pathTransition;
	   }
	  

	   
	  
	  private Path generatePathAB(final Circle VilleA, final Circle VilleB) {
		  final Path path = new Path();
		  path.getElements().add(new MoveTo(VilleA.getCenterX(), VilleA.getCenterY()));
		  path.getElements().add(new LineTo(VilleB.getCenterX(), VilleB.getCenterY()));
		  path.setOpacity(0);
		  return path;
	  }
	  private Line generateRouteAB(final Circle VilleA, final Circle VilleB, int width) {
		  Line routeAB = new Line(VilleA.getCenterX(), VilleA.getCenterY(), VilleB.getCenterX(),VilleB.getCenterY());
		  routeAB.setStrokeWidth(width);
		  routeAB.setStroke(Color.GREY);
		  return routeAB;
		  
	  }
	  
	 public void start(Stage primaryStage) {
	       Group root = new Group();
	       Scene scene = new Scene(root, 1200, 1200, Color.WHITE);
	       primaryStage.setScene(scene);
	       primaryStage.setTitle("Ouais le rond");
	       primaryStage.show();
	       Group Villes = new Group();
	       Circle VilleA = new Circle(200.0,200.0,100.0, Color.GREEN);
	       CreateVille.setContour(VilleA, "red", 5);
	       root.getChildren().add(Villes);
	       Circle VilleB = new Circle(300, 400, 50, Color.RED);
	       Circle VilleC = new Circle(600.0,600.0,80.0, Color.BLUE);
	       Circle VilleD = new Circle(200, 700, 200, Color.CYAN);
	       Circle VilleE = new Circle( 800, 900, 100, Color.YELLOW);
	       Villes.getChildren().addAll(VilleB, VilleA, VilleC, VilleD, VilleE);
	       System.out.println(VilleA.getCenterX());
	       
           Group Line = new Group();

           Line.getChildren().add(generateRouteAB(VilleA, VilleB, 5));
           Line.getChildren().add(generateRouteAB(VilleC, VilleB, 5));
           Line.getChildren().add(generateRouteAB(VilleD, VilleB, 5));
           Line.getChildren().add(generateRouteAB(VilleE, VilleC, 5));
           Line.getChildren().add(generateRouteAB(VilleD, VilleE, 15));
           root.getChildren().add(Line);
           
           Group Voitures = new Group();
           
          Rectangle Voiture1 = new Rectangle(VilleA.getCenterX(),VilleA.getCenterY(),5.0,5.0);
          Voitures.getChildren().addAll(Voiture1);
          root.getChildren().add(Voitures);

         generatePathTransition(Voiture1,generatePathAB(VilleC, VilleB));
          //path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));

}
}
