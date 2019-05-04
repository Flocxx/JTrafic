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
	      pathTransition.setCycleCount(0);
	      pathTransition.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent fade) {
				// TODO Auto-generated method stub
			shape.setOpacity(0);
			//shape.fireEvent(fade);
			}});
	      pathTransition.setAutoReverse(false);
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
	  /**private Line generateDemiRouteAB(final Line LineA, final Line LineB, int width) {
		  Line routeAB = new Line(LineA.getCenterX(), LineA.getCenterY(), LineB.getCenterX(),LineB.getCenterY());
		  routeAB.setStrokeWidth(width);
		  routeAB.setStroke(Color.PINK);
		  return routeAB;
		  
	  }*/
	  

	  private Rectangle generateCar(Path chemin) {
		final Rectangle car = new Rectangle(chemin.getLayoutX(), chemin.getLayoutY(),5.0,5.0);
		generatePathTransition(car, chemin);
		  return car;
	  }
	  
	 public void start(Stage primaryStage) {
	       Group root = new Group();
	       Scene scene = new Scene(root, 1200, 600, Color.WHITE);
	       primaryStage.setScene(scene);
	       primaryStage.setTitle("Ouais le rond");
	       primaryStage.show();
	       Circle VilleA = new Circle(200,150,75, Color.GREEN); //(X,Y,taille)
	       //CreateVille.setContour(VilleA, "red", 5);
	       Circle VilleB = new Circle(600, 150, 75, Color.RED);
	       Circle VilleC = new Circle(1000,150,75, Color.BLUE);
	       Circle VilleD = new Circle(200, 450, 75, Color.CYAN);
	       Circle VilleE = new Circle( 600, 450, 75, Color.YELLOW);
	       Circle VilleF = new Circle( 1000, 450, 75, Color.PURPLE);
	       root.getChildren().addAll(VilleB, VilleA, VilleC, VilleD, VilleE,VilleF);
	       System.out.println(VilleA.getCenterX());
	       
           

           Line Line1=generateRouteAB(VilleA, VilleB, 5);
           Line Line2=generateRouteAB(VilleA, VilleC, 5);
           Line Line3=generateRouteAB(VilleB, VilleD, 5);
           Line Line4=generateRouteAB(VilleD, VilleE, 15);
           Line Line5=generateRouteAB(VilleE, VilleC, 15);
           root.getChildren().addAll(Line1,Line2,Line3,Line4,Line5);
           
           
           Group Voitures = new Group();
           
          
          Rectangle Voiture2 = new Rectangle(VilleA.getCenterX(),VilleA.getCenterY(),5.0,5.0);
          Voitures.getChildren().addAll(Voiture2);
         
          Button CarCreator = new Button("Generer une voiture");
          
          Label label = new Label("");
          Random rand = new Random();
         
          CarCreator.setOnAction(new EventHandler<ActionEvent>() {
        	  
              @Override
              public void handle(ActionEvent event) {
            	int numberStart;
            	Circle TownStart= new Circle();
            	numberStart=rand.nextInt(5);
            	switch(numberStart) {
            	case 0:
            		TownStart = VilleA;
            		break;
            	case 1:
            		TownStart = VilleB;
            		break;
            	case 2:
            		TownStart = VilleC;
            		break;
            	case 3:
            		TownStart = VilleD;
            	break;
            	case 4:
            		TownStart = VilleE;
            		break;
            	case 5:
            		TownStart = VilleF;
            		break;
            	}
            	int numberEnd;
            	Circle TownEnd= new Circle();
            	numberEnd=rand.nextInt(5);
            	switch(numberEnd) {
            	case 0:
            		TownEnd = VilleA;
            		break;
            	case 1:
            		TownEnd = VilleB;
            		break;
            	case 2:
            		TownEnd = VilleC;
            		break;
            	case 3:
            		TownEnd = VilleD;
            	break;
            	case 4:
            		TownEnd = VilleE;
            		break;
            	case 5:
            		TownEnd = VilleF;
            		break;
            	}
            	Voitures.getChildren().add(generateCar(generatePathAB(TownStart, TownEnd)));
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
