package classes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Plant {
	
	public static double radius = 0.5;  // size of Plant of canvas
	
	private Pane map;
	private Circle circle;
	Points position;
	int plantEnergy ;
	
	
	
	public Plant(Pane map,int plantEnergy ,int width,int height) {
		super(); 
		this.map = map;
		this.circle = new Circle(radius, Color.PURPLE);
		this.position = new Points(width, height);
		circle.setCenterX(position.getX());
		circle.setCenterY(position.getY());
		this.plantEnergy  = plantEnergy ;
		//circle.setStroke(Color.BLACK);
	    map.getChildren().add(circle);
	} 
	
	public Plant(Pane map, int x, int y) {
		this.map = map;
		this.circle = new Circle(radius, Color.GREEN);
		this.position = new Points();
		position.setX(x); 
		position.setY(y); 
		position.validatePoints((int)map.getWidth(),(int)map.getHeight(),x,y);
		circle.setCenterX(position.getX()); 
		circle.setCenterY(position.getY());
		 
		circle.setStroke(Color.BLACK);
	    map.getChildren().add(circle);
	}
  
	public void remove() {
		map.getChildren().remove(circle);;
	}

	public void draw() {
        circle.setRadius(radius);
        circle.setCenterX(position.getX());
		circle.setCenterY(position.getY());
    }
	public void setPlantEnergy(Integer plantEnergy) {
		this.plantEnergy = plantEnergy;
	}

	public void setPlantPosition(int x, int y) {
		this.position.setPoints(x,y);
	}
}
