package classes;

import javafx.scene.shape.Rectangle;

public class Points {
	
	int x;
	int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
	
	public void updatePoints(int xMove,int yMove,int width,int height) {
		this.x = x+xMove;
		this.y = y+yMove; 
		
		
        
	}
	
	public void validatePoints(int width,int height,int x, int y,Rectangle rectangle) {
		if(x < 0 + rectangle.getWidth() ) { this.x = width - width/80; }
        if(x > width - rectangle.getWidth()) { this.x = 0 + width/80 ; }
        if(y < 0 + rectangle.getWidth() ) { this.y = height - height/80; }
        if(y > height - rectangle.getWidth() ) { this.y = 0 + height /80; } 
	}
	 
	public Points(int width,int height) {
        this.x =  (int) ((Math.random() * ( width - 10 )) + 10);
        this.y =  (int) ((Math.random() * ( height - 10 )) + 10);
        
     // System.out.println("Wylosowana pozycja to : " + this.x +" Oraz " +  this.y);
    }
	public Points() {
		// TODO Auto-generated constructor stub
	}
	public void setPoints(int x,int y) {
		this.x = x;
        this.y = y;
        
	}
	public void validatePoints(int width, int height, int x2, int y2) {
		if(x < 0  ) { this.x = width - width/80; }
        if(x > width ) { this.x = 0 + width/80 ; }
        if(y < 0  ) { this.y = height - height/80; }
        if(y > height ) { this.y = 0 + height /80; } 
		
	}
	
	
	
	
}
