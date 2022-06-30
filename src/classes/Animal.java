package classes;



import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class Animal {
	
	
	
	
	private AnimalGenes genes;
	Points position;
	int direction;
	int currentEnergy;
	private int moveCost;
	private int age = 0;
	private int amountOfKids = 0;
	private State state;
	private Pane map;
	private Rectangle rectangle;
	private int width;
	private int height;
	
	public static double size = 1;
	
	public Animal( int energy, Pane map, int moveCost,int width,int height) {
		super();  
		this.moveCost = moveCost;
		this.genes = new AnimalGenes();
		this.position = new Points( width, height);
		this.direction = 0;
		this.currentEnergy = energy;
		this.state = new State(currentEnergy);
		this.map = map;
		this.rectangle = new Rectangle();
		this.width = width;
		this.height = height;
		rectangle.setX(position.getX());
		rectangle.setY(position.getY());
		  
		//rectangle.setStroke(Color.RED);
		map.getChildren().add(rectangle);
	} 
	 
	void addToEnergy(int cheange) {
		this.currentEnergy = currentEnergy + cheange; 
		if(currentEnergy < 0) {
			this.currentEnergy = 0;
		}
		//System.out.println(currentEnergy);
	}
	 
	
	public boolean checkIfDead() {
		if(currentEnergy <=0) {
			//System.out.println("[*]" + currentEnergy);
			return true;
		
		}
			
		else 
			return false;
		
         
    }
	

    public void remove() {
        map.getChildren().remove(rectangle);
    }
	
	
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
 
	public void updateRotation(int direction) {
		int rotation;
		rotation = this.genes.getDirectionValue();
		if(direction+rotation>7 )
		{
			setDirection(direction+rotation-7);
		}
		else setDirection(direction + rotation);
		
	}

	public void move() {
		
		addToEnergy(-moveCost);
		setAge(getAge() + 1);
		this.rectangle.setFill(this.state.checkState(this.currentEnergy));
		updateRotation(this.direction);
		switch(direction) {
		case 0:
			this.position.updatePoints(0,1, width, height); //Move N
			break;

		case 1:
			this.position.updatePoints(1,1, width, height); //Move NE
			break;

		case 2:
			this.position.updatePoints(1,0, width, height); //Move W
			break;

		case 3:
			this.position.updatePoints(1,-1, width, height); //Move SE
			break;

		case 4:
			this.position.updatePoints(0,-1, width, height); //Move S
			break;

		case 5:
			this.position.updatePoints(-1,-1, width, height); //Move SW
			break;

		case 6:
			this.position.updatePoints(-1,0, width, height); //Move W
			break;

		case 7:
			this.position.updatePoints(-1,1, width, height); //Move NW
			break;
		
		} 
		// Sprawdzanie poprawnosci przemieszczenia sie 
		this.position.validatePoints(width,height,position.getX(),position.getY(),rectangle);
		// usuwanie zwierzatka 
		
		
	}
 
	 public void draw() {
		 	
		    rectangle.setWidth(size);
		    rectangle.setHeight(size);
		    
		    rectangle.setX(position.getX());
		    rectangle.setY(position.getY());
			
	    }
 
	public int getEnergy() {
		// TODO Auto-generated method stub
		return currentEnergy;
	}
	 
	// Funkcja opisujaca rozmnazanie sie zwierzat
	
	public Animal multiply( Animal secondParent,int xSpawnPoint,int ySpawnPoint) {
		
        int childEnergy = (int) (0.25 * secondParent.currentEnergy) + (int) (0.25 * this.currentEnergy);
        secondParent.addToEnergy((int) - (0.25 * secondParent.currentEnergy)); 
        this.addToEnergy((int) - (0.25 * this.currentEnergy));

        Animal child = new Animal( childEnergy,this.map, this.moveCost,this.width,this.height);
        child.genes = new AnimalGenes(this.genes, secondParent.genes);
        child.position.setX(xSpawnPoint+1);
        child.position.setY(ySpawnPoint+1);
        
        System.out.println("New Animal !! " + child.currentEnergy);
        
        this.setAmountOfKids(this.getAmountOfKids() + 1);
        secondParent.setAmountOfKids(secondParent.getAmountOfKids() + 1);
        
        return child;
        
        
    }

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAmountOfKids() {
		return amountOfKids;
	}

	public void setAmountOfKids(int amountOfKids) {
		this.amountOfKids = amountOfKids;
	}
	
	
}
