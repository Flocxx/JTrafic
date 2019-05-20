package graphique;

import point.Intersection;
import point.Point;
import point.Segment;
import point.TypeRoute;

import java.util.ArrayList;
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
	
	private ArrayList<Circle> listeVille;
	private ArrayList<Line> listeTraitRoutes;
	private ArrayList<Segment> listeSegment;
	private ArrayList<Intersection> listeIntersection;
	private ArrayList<Rectangle> listeVoiture;
	private ArrayList<Circle> listeTraceIntersection;
	
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
	  

	   
	  
	  private Path generatePathAB(final Circle villeA, final Circle villeB) {
		  final Path path = new Path();
		  Segment trajet = findSegment(new Point(villeA.getCenterX(),villeB.getCenterY()),new Point(villeB.getCenterX(),villeB.getCenterY()));
		  TypeRoute largeurRoute = trajet.getType();
		  double decalementVoiture = determinerDecalageMax(largeurRoute);
		  double decalementVoitureX = 0;
		  double decalementVoitureY = 0;
		  if(villeA.getCenterX()<villeB.getCenterX()) {
			  decalementVoitureY = decalementVoiture;
			  //path.getElements().add(new MoveTo(villeA.getCenterX(), villeA.getCenterY()+decalementVoiture));
			  //path.getElements().add(new LineTo(villeB.getCenterX(), villeB.getCenterY()+decalementVoiture));
		  }
		   if(villeA.getCenterX()>villeB.getCenterX()) {
			  decalementVoitureY = -decalementVoiture;
			  //path.getElements().add(new MoveTo(villeA.getCenterX(), villeA.getCenterY()-decalementVoiture));
			  //path.getElements().add(new LineTo(villeB.getCenterX(), villeB.getCenterY()-decalementVoiture));
			  }
		   if(villeA.getCenterY()>villeB.getCenterY()) {
			  decalementVoitureX = decalementVoiture;
			  //path.getElements().add(new MoveTo(villeA.getCenterX()+decalementVoiture, villeA.getCenterY()));
			  //path.getElements().add(new LineTo(villeB.getCenterX()+decalementVoiture, villeB.getCenterY()));
			  }
		  if(villeA.getCenterY()<villeB.getCenterY()) {
			  decalementVoitureX = decalementVoiture;
			  //path.getElements().add(new MoveTo(villeA.getCenterX()-decalementVoiture, villeA.getCenterY()));
			  //path.getElements().add(new LineTo(villeB.getCenterX()-decalementVoiture, villeB.getCenterY()));
			  }
		  path.getElements().add(new MoveTo(villeA.getCenterX()+decalementVoitureX, villeA.getCenterY()+decalementVoitureY));
		  path.getElements().add(new LineTo(villeB.getCenterX()+decalementVoitureX, villeB.getCenterY()+decalementVoitureY));
		  
		  path.setOpacity(3);
		  return path;
	  }
	  private Line generateRouteAB(final Circle VilleA, final Circle VilleB, int width, Color couleur) {
		  Line routeAB = new Line(VilleA.getCenterX(), VilleA.getCenterY(), VilleB.getCenterX(),VilleB.getCenterY());
		  routeAB.setStrokeWidth(width);
		  routeAB.setStroke(couleur);
		  return routeAB;
		  
	  }
	  
	  private Line generateRouteBA(final Circle VilleA, final Circle VilleB, int width, Color couleur) {
		  Line routeBA = new Line(VilleA.getCenterX()+3, VilleA.getCenterY()+6, VilleB.getCenterX()+3,VilleB.getCenterY()+6);
		  routeBA.setStrokeWidth(width);
		  routeBA.setStroke(couleur);
		  return routeBA;
		  
	  }

	  private Rectangle generateCar(Path chemin) {
		final Rectangle car = new Rectangle(chemin.getLayoutX(), chemin.getLayoutY(),5.0,2.5);
		generatePathTransition(car, chemin);
		  return car;
	  }
	  
	 public boolean segmentExist(Segment segmentA) {
		 boolean res = false;
		 for(Segment i:listeSegment) {
			 if(segmentA.isEgal(i)) {
				 res = true;
			 }
		 }
		 return res;
	 }
	  
	 public void start(Stage primaryStage) {
	       Group root = new Group();
	       Scene scene = new Scene(root, 1200, 600, Color.OLDLACE);
	       primaryStage.setScene(scene);
	       primaryStage.setTitle("JTrafic");
	       primaryStage.show();
	       
	       listeVille = new ArrayList<Circle>();
	       //(X,Y,taille)
	       listeVille.add(new Circle(70,150,60, Color.MEDIUMPURPLE));
	       listeVille.add(new Circle(70,450,60, Color.DARKSEAGREEN));
	       listeVille.add(new Circle(600, 60, 60, Color.PINK));
	       listeVille.add(new Circle(600,530,60, Color.MIDNIGHTBLUE));
	       listeVille.add(new Circle(1130, 150, 60, Color.SKYBLUE));
	       listeVille.add(new Circle(1130, 450, 60, Color.INDIANRED));
	       //listeVille.add(new Circle(1000, 450, 75, Color.PURPLE));
	      // root.getChildren().addAll(listeVille);
	       System.out.println(listeVille.get(0).getCenterX());
	       
	       listeTraitRoutes = new ArrayList<Line>();
	       listeSegment = new ArrayList<Segment>();
	       int count1 = 0;
           for(Circle i:listeVille) {
        	   for(Circle j:listeVille) {
        		   if(i != j) {	//Ne pas créer  de route ayant la meme ville de depart et d'arrivee
        			  Segment tmp = new Segment(new Point(i.getCenterX(),i.getCenterY()),new Point(j.getCenterX(),j.getCenterY()));
        			  if(!segmentExist(tmp)) {
        				  if(count1 == 0) {
        					  tmp.setType(TypeRoute.AUTOROUTE);
        					  listeSegment.add(tmp);
            				  listeTraitRoutes.add(generateRouteAB(i,j,15,Color.GREY));
            			  }else if(count1 == 1){
            				  tmp.setType(TypeRoute.AUTOROUTE);
            				  listeSegment.add(tmp);
            				  listeTraitRoutes.add(generateRouteAB(i,j,10,Color.GREY));
            			  }else{
            				  tmp.setType(TypeRoute.AUTOROUTE);
            				  listeSegment.add(tmp);
            				  listeTraitRoutes.add(generateRouteAB(i,j,5,Color.GREY));
            			  }
            			  if(count1 != 3) {
            				  count1++;
            			  }else{
            				  count1 = 0;
            			  }
        			  }//TOUCHE PAS A CA PETIT CON
        			  /*else {
        				  if(count1 == 0) {
    					  listeSegment.add(tmp);
        				  listeTraitRoutes.add(generateRouteBA(i,j,15,Color.GREY));
        			  }else if(count1 == 1){
        				  listeSegment.add(tmp);
        				  listeTraitRoutes.add(generateRouteBA(i,j,10,Color.GREY));
        			  }else{
        				  listeSegment.add(tmp);
        				  listeTraitRoutes.add(generateRouteBA(i,j,5,Color.GREY));
        			  }
        			  if(count1 != 3) {
        				  count1++;
        			  }else{
        				  count1 = 0;
        			  }
        			  }*/
        		   }
        	   }
           }

           /*Line Line1=generateRouteAB(VilleA, VilleB, 5);
           Line Line2=generateRouteAB(VilleA, VilleC, 5);
           Line Line3=generateRouteAB(VilleB, VilleD, 5);
           Line Line4=generateRouteAB(VilleD, VilleE, 15);
           Line Line5=generateRouteAB(VilleE, VilleC, 15);*/
           
           
           
          Group voitures = new Group();
          listeVoiture = new ArrayList<Rectangle>(); 
         
         
          Button CarCreator = new Button("Nouvelle voiture");
          CarCreator.setLayoutX(125);
          CarCreator.setLayoutY(25);
          
          Label label = new Label("");
          Random rand = new Random();
         
          CarCreator.setOnAction(new EventHandler<ActionEvent>() {
        	  
              @Override
              public void handle(ActionEvent event) {
            	int numberStart;
            	Circle townStart = new Circle();
            	numberStart=rand.nextInt(listeVille.size());
            	townStart = listeVille.get(numberStart);
            	int numberEnd;
            	Circle townEnd = new Circle();
            	do {
            		numberEnd = rand.nextInt(listeVille.size());
            	} while (numberEnd == numberStart);	//La voiture ne doit pas posséder le meme depart et la meme arrive
            	townEnd = listeVille.get(numberEnd);
            	Rectangle voitutreTmp = generateCar(generatePathAB(townStart, townEnd));
            	listeVoiture.add(voitutreTmp);
            	voitures.getChildren().add(voitutreTmp);
              }
          });
          //Gestion des collisions
          listeIntersection = new ArrayList<Intersection>();
          listeTraceIntersection = new ArrayList<Circle>();
          /*for (Line i:listeTraitRoutes) {
        	  for(Line j:listeTraitRoutes) {
        		  addCollision(i,j,root);
        	  }
          }*/
          for(Segment i:listeSegment) {
        	  i.findAllIntersection(listeSegment);
        	  ArrayList<Intersection>tmp = i.getListeIntersection();
        	  System.out.println(tmp.size());
        	  for(Intersection j:tmp) {
        		  ajoutIntersection(j,i,listeIntersection);	//On ajoute les intersections
        	  }
          }
          traceIntersection(listeIntersection);
          System.out.println(listeIntersection.size());
          root.getChildren().addAll(listeTraitRoutes);
          root.getChildren().addAll(listeVille);
          
          root.getChildren().addAll(listeTraceIntersection);
          //fin collisions
          root.getChildren().addAll(CarCreator,label);
          root.getChildren().add(voitures);
         
         //Path DC = generatePathAB(listeVille.get(4), listeVille.get(3));
         //System.out.println(DC);
          //path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));

	 }
	 
	 public void traceIntersection(ArrayList<Intersection>listeIntersection) {
		 for(Intersection i:listeIntersection) {
			 if(i.getIsVille()) {
				 listeTraceIntersection.add(new Circle(i.getX(),i.getY(),15,Color.WHITE));
			 }else {
				 listeTraceIntersection.add(new Circle(i.getX(),i.getY(),15,Color.BLACK));
			 }
		 }
	 }
	 
	 public void ajoutIntersection(Intersection intersect, Segment routeActuel, ArrayList<Intersection>listeIntersect) {
		 boolean exist = false;
		 for (Intersection i:listeIntersect) {
			 if(intersect.isEgal(i)) {	//Si l'intersection existe deja on lui ajoute juste cette route la
				 i.ajoutRouteAdjacente(routeActuel);
				 exist = true;
			 }
		 }
		 if(!exist) {
			 System.out.println("ajout route");
			 listeIntersect.add(intersect);
		 }
	 }
	 
	 public Segment findSegment(Point villeACentre, Point villeBCentre) {
		 Segment res = new Segment();
		 for(Segment i:listeSegment) {
			 if(i.getExtremite1().ifEqual(villeACentre) && i.getExtremite2().ifEqual(villeBCentre)) {
				 res = i;
			 }else if(i.getExtremite2().ifEqual(villeACentre) && i.getExtremite1().ifEqual(villeBCentre)) {
				 res = i;
			 }
		 }
		 return res;
	 }
	 
	 public double determinerDecalageMax(TypeRoute largeurRoute) {
		 if(largeurRoute == TypeRoute.AUTOROUTE) {
			 return 7;
		 }else if(largeurRoute == TypeRoute.NATIONAL) {
			 return 5;
		 }else {
			 return 2;
		 }
	 }
	 
	 /*public boolean addCollision (Line route1, Line route2, Group root) {
		 Shape inter = Shape.intersect(route1, route2);
         System.out.println(inter.getLayoutBounds().getWidth() + ":" + inter.getLayoutBounds().getHeight());
         System.out.println(listeTraitRoutes.get(3));
         if(inter.getLayoutBounds().getHeight()<=0 || inter.getLayoutBounds().getWidth()<=0) {
             System.out.println("No intersection");
             return false;
         }
         else {
             System.out.println("intersection detected");
             System.out.println(inter.getLayoutX());
             Circle point = new Circle(inter.getBoundsInParent().getMaxX(), inter.getBoundsInParent().getMaxY(), 15, Color.BLACK);
             Point intersect = new Point(inter.getBoundsInParent().getMaxX(),inter.getBoundsInParent().getMaxY());
             boolean isIntersection = intersectionExist(intersect);	//On verifie si l'interesection a deja ete detecte et creer 
             if(!isIntersection) {
            	 listeVille.add(point);
                 listeIntersection.add(intersect);
                 System.out.println("intersection added");
             }else {
            	 System.out.println("intersection already exist");
             }
             return true;
         }
	 }*/
	 
}
