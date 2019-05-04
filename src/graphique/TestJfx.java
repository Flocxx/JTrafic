package graphique;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;


public class TestJfx extends Application {


    private ArrayList<Rectangle> rectangleArrayList;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("The test");
        Group root = new Group();
        Scene scene = new Scene(root, 400, 400);

        rectangleArrayList = new ArrayList<Rectangle>();
        Rectangle rectangle1 = new Rectangle(30.0, 30.0, Color.GREEN);
        Rectangle rectangle2 = new Rectangle(30.0, 30.0, Color.GREEN);
        rectangle1.setX(50);	//Modification de la position du rectangle 
        rectangle1.setY(100);
        rectangle2.setX(100);
        rectangle2.setY(100);
        rectangleArrayList.add(rectangle1);
        rectangleArrayList.add(rectangle2);
        
        root.getChildren().addAll(rectangleArrayList);
        Path mouvement = generatePathAB(100, 200);
        generatePathTransition(rectangle1, mouvement);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //animation.setRate(2); permet de modifiet la vitesse d'une animation (ici vitesse*2)
    
	   private PathTransition generatePathTransition(final Rectangle shape, final Path path)
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
	      /*Thread mouvement = new Thread(new Runnable() {
	    	  public void run() {
	    		  while(true) {
	    		  for(Rectangle autreBlock : rectangleArrayList) {
	    	    	  System.out.println("" + shape.getX() + " : " + shape.getY());
	    	    	  if(shape.getBoundsInParent().intersects(autreBlock.getBoundsInParent())) {
	    	    		  pathTransition.pause();
	    	    	  }else {
	    	    		  pathTransition.play();
	    	    	  }
	    	      }
	    		  }
	    	  }
	      });*/
	      //mouvement.start();
	      return pathTransition;
	   }
    
    private Path generatePathAB(final double pointX, final double pointY) {
		  final Path path = new Path();
		  path.getElements().add(new MoveTo(pointX, pointY));
		  path.getElements().add(new LineTo(pointX, pointY));
		  path.setOpacity(3);
		  return path;
	  }
    
    public void checkCollision(Shape block) {
    	
    }
    
    public void setDragListeners(final Rectangle block) {
        final Delta dragDelta = new Delta();

        block.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = block.getTranslateX() - mouseEvent.getSceneX();
                dragDelta.y = block.getTranslateY() - mouseEvent.getSceneY();
                block.setCursor(Cursor.NONE);
            }
        });
        block.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                block.setCursor(Cursor.HAND);
            }
        });
        block.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                block.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);
                block.setTranslateY(mouseEvent.getSceneY() + dragDelta.y);
                checkBounds(block);

            }
        });
    }

    private void checkBounds(Rectangle block) {
        for (Rectangle static_bloc : rectangleArrayList)
            if (static_bloc != block) {
                if (block.getBoundsInParent().intersects(static_bloc.getBoundsInParent())) {
                    block.setFill(Color.BLUE);        //collision
                } else {
                    block.setFill(Color.GREEN);    //no collision
                }
            } else {
                block.setFill(Color.GREEN);    //no collision -same block
            }
    }

    class Delta {
        double x, y;
    }
}