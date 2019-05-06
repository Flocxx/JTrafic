package graphique;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class TestJfx extends Application {

@Override
public void start(Stage stage) throws Exception {
    Pane root = new Pane();
    root.setStyle("-fx-background-color:cyan;");
    Polygon p1 = new Polygon();
    p1.getPoints().addAll(new Double[]{
            50.,50.,
            50.,100.,
            60.,100.,
            60.,80.,
            80.,70.,
            80.,100.,
            100.,100.,
            100.,50.
    });
    p1.setFill(Color.GREEN);

    Polygon p2 = new Polygon();
    p2.getPoints().addAll(new Double[]{
            65.,100.,
            65.,90.,
            75.,80.,
            100.,100.
    });
    p2.setFill(Color.RED);

    root.getChildren().addAll(p1,p2);

    stage.setScene(new Scene(root));
    stage.show();

    Shape inter = Shape.intersect(p1, p2);
    root.getChildren().add(inter);

    System.out.println(inter.getLayoutBounds().getWidth() + ":" + inter.getLayoutBounds().getHeight());
    if(inter.getLayoutBounds().getHeight()<=0 || inter.getLayoutBounds().getWidth()<=0) {
        System.out.println("No intersection");
    }
    else {
        System.out.println("intersection detected");
    }
}

public static void main(String[] args) {
    launch(args);
}
}