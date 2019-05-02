package graphique;



import java.util.Date;
import java.util.List;
import java.util.Random;

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
		  path.setOpacity(3);
		  return path;
	  }
	  private Line generateRouteAB(final Circle VilleA, final Circle VilleB, int width) {
		  Line routeAB = new Line(VilleA.getCenterX(), VilleA.getCenterY(), VilleB.getCenterX(),VilleB.getCenterY());
		  routeAB.setStrokeWidth(width);
		  routeAB.setStroke(Color.GREY);
		  return routeAB;
		  
	  }
	  private Rectangle generateCar(Path chemin) {
		final Rectangle car = new Rectangle(chemin.getLayoutX(), chemin.getLayoutY(),5.0,5.0);
		generatePathTransition(car, chemin);
		  return car;
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
           
          
          Rectangle Voiture2 = new Rectangle(VilleA.getCenterX(),VilleA.getCenterY(),5.0,5.0);
          Voitures.getChildren().addAll(Voiture2);
         
          Button CarCreator = new Button("Generer une voiture");
          
          Label label = new Label("");
          Random rand = new Random();
         
          CarCreator.setOnAction(new EventHandler<ActionEvent>() {
        	  
              @Override
              public void handle(ActionEvent event) {
            	int villeDepart;
            	villeDepart=rand.nextInt(4);
            	switch(villeDepart) {
            	case 0:
            		Voitures.getChildren().add(generateCar(generatePathAB(VilleA, VilleD)));
            		break;
            	case 1:
            		Voitures.getChildren().add(generateCar(generatePathAB(VilleB, VilleD)));
            		break;
            	case 2:
            		Voitures.getChildren().add(generateCar(generatePathAB(VilleC, VilleD)));
            		break;
            	case 3:
            		Voitures.getChildren().add(generateCar(generatePathAB(VilleD, VilleC)));
            	break;
            	case 4:
            		Voitures.getChildren().add(generateCar(generatePathAB(VilleE, VilleD)));
            		break;
            	}
            	
            	System.out.println(generateCar(generatePathAB(VilleE, VilleD)));
              }
          });
         
          root.getChildren().addAll(CarCreator,label);
          root.getChildren().add(Voitures);
         
         Path DC = generatePathAB(VilleD, VilleC);
         System.out.println(DC);
         generatePathTransition(Voiture2, DC);
          //path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));

}
}
