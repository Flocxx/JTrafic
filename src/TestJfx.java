import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
 
public class TestJfx extends Application {
		 
	    public static void main(String[] args) {
	        launch(args);
	    }
	    
	    @Override
	    public void start(Stage primaryStage) {
	        Group root = new Group();
	        Scene scene = new Scene(root, 800, 600, Color.GREEN);
	        primaryStage.setScene(scene);

	        primaryStage.show();
	        Group circles = new Group();
	        for (int i = 0; i < 30; i++) {
	           Circle circle = new Circle(150, Color.web("white", 0.25));
	           circle.setCenterX(100.0f*i); 
	           circle.setCenterY(100.0f*i); 
	           circle.setStrokeType(StrokeType.OUTSIDE);
	           circle.setStroke(Color.web("black", 0.16));
	           circle.setStrokeWidth(4);
	           circles.getChildren().add(circle);
	        }
	        root.getChildren().add(circles);
	    }
	
}
