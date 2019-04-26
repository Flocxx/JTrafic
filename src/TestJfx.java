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
 
public class TestJfx extends Application {
		 
	    public static void main(String[] args) {
	        launch(args);
	    }
	    
	    @Override
	    public void start(Stage primaryStage) {
	        Group root = new Group();
	        Scene scene = new Scene(root, 800, 600, Color.WHITE);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Ouais le rond");
	        primaryStage.show();
	        Group circles = new Group();
	           Circle villeA = new Circle(30, Color.web("blue", 0.25));
	           villeA.setCenterX(150); 
	           villeA.setCenterY(100); 
	           villeA.setStrokeType(StrokeType.OUTSIDE);
	           villeA.setStroke(Color.web("black", 0.16));
	           villeA.setStrokeWidth(4);
	           circles.getChildren().add(villeA);
	           Circle villeB = new Circle(50, Color.web("red", 0.25));
	           villeB.setCenterX(700); 
	           villeB.setCenterY(180); 
	           villeB.setStrokeType(StrokeType.OUTSIDE);
	           villeB.setStroke(Color.web("black", 0.16));
	           villeB.setStrokeWidth(4);
	           circles.getChildren().add(villeB);
	           root.getChildren().add(circles);
	           
	           Group Line = new Group();
	           Line routeAB = new Line(150.0, 100.0, 700.0,180.0);
	           routeAB.setStrokeWidth(5);
	           Line.getChildren().add(routeAB);
	           root.getChildren().add(Line);
	    }
	
}
