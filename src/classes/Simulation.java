package classes;



import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Simulation {

	WorldParameters parameters;
	public ArrayList<Animal> animals;
	public ArrayList<Plant> plants;
	private Pane map;
	private int epoka = 0;
	
	// Tabele do statystyk
	
	public ArrayList<Integer> amountOfAnimals = new ArrayList<Integer>();;
	public ArrayList<Integer> amountOfPlants = new ArrayList<Integer>();;
	public ArrayList<Integer> amountOfEnergy = new ArrayList<Integer>();;
	public ArrayList<Integer> ageOfDeadAnimals = new ArrayList<Integer>();;
	
	
	private Canvas canvas;
 
	static final double fertility = 0.0000002;
    static final double multiplyCost = 100;   
	
    // Uruchomienie symulacji
    // generowanie zwierzat do listy  i canvas
    // generowanie roslin do listy i canvas
    
    
	public Simulation(WorldParameters parameters,Pane map) {
		this.parameters = parameters;
		animals = new ArrayList<Animal>();
		plants = new ArrayList<Plant>();
		this.map = map;
		this.canvas = new Canvas(parameters.getWidth(),parameters.getHeight());
		
		
		
		
		map.getChildren().add(canvas);
		draw(); 
		for(int i = 0; i < this.parameters.getStartAnimals(); i++) {
	         animals.add(new Animal(
	        		 this.parameters.getStartEnergy(),
	        		 map,
	        		 parameters.getMoveEnergy(),
	        		 this.parameters.getWidth(),
	        		 this.parameters.getHeight()));
	         	
	     }
		
		for (int i = 0; i < this.parameters.getStartAnimals()*10; i++) {
            plants.add(new Plant(map,
            		this.parameters.getPlantEnergy(),
            		this.parameters.getWidth(),
            		this.parameters.getHeight()));
            		 
        }

		
	}
	 

     //Generowanie całego tła świata
	public void draw() {
		
		
		GraphicsContext g = this.canvas.getGraphicsContext2D();
		g.clearRect(0, 0, this.parameters.getWidth(), this.parameters.getHeight());
		
		// Sawanna generation
		
		g.setFill(Color.LIGHTGREEN);
		g.fillRect(0,0,this.parameters.getWidth(),this.parameters.getHeight());
		
		//Jungle Generation  
		
		g.setFill(Color.GREEN);
		g.fillRect(0,0,getJungleWidth(parameters),getJungleHeight(parameters));
		
		
	
		
	}
	
	
	
	public void move() {
		//Sprawdzenie czy zwierze jest w miejscu rosliny i zjedzenie jej
		//
		for(int i = (animals.size() - 1);i >= 0;i--) {
			animals.get(i).move();
			// check if plant is in danger
			for(int j = (plants.size() - 1);j >= 0;j--) {
				if((animals.get(i).position.getX() == plants.get(j).position.getX() && 
						animals.get(i).position.getY() == plants.get(j).position.getY()))
				{ 
					animals.get(i).addToEnergy(plants.get(j).plantEnergy);
					plants.get(j).remove();
					plants.remove(j);
				} 
			}
		}
		//Sprawdzenie czy zwierze jest w miejscu innego zwierzecia i rozmnazanie
		for(int i = 0,j = 1; i < animals.size(); i++) 
		{
			if(i!=j) { // can't multiply with it'self
				for(; j < animals.size() ; j++)
				{ 
					if(
							animals.get(i).position.getX() == animals.get(j).position.getX() &&
							animals.get(i).position.getY() == animals.get(j).position.getY() &&
							animals.get(i).currentEnergy > multiplyCost && 
							animals.get(j).currentEnergy > multiplyCost
							)
					{
						animals.add(
								animals.get(i).multiply(animals.get(j),
								animals.get(i).position.getX(),
								animals.get(i).position.getY())
								);
					}
				}
			}
		}
	}
	
	
	
	//Umiesc obiekty  z listy na mapie
	
	
	public void drawObjects() {
		for (Animal a: animals) {
            a.draw();
        }
		for(Plant p: plants) {
			p.draw();
		}
	}
	
	
	public void removeDeadAnimals() {
        for (int i = (animals.size() - 1);i >= 0;i--) {
            if( animals.get(i).checkIfDead()) {
            	System.out.println("Zwierze umarlo! Mialo " + animals.get(i).getAge() + " lat !");
            	ageOfDeadAnimals.add(animals.get(i).getAge());
            	animals.get(i).remove();
            	animals.remove(i);
            	
            }
        }
        
    }
	
	// funkcja generujaca nowe rosliny przechodzi po kazdym polu i losuje
	// jesli jest na polu z 
	
	public void generateNewPlants() { 
		for(int x = 5; x < this.parameters.getWidth() - 5 ;x++) {
			
				
			
				for(int y = 5 ;y < this.parameters.getHeight() - 5;y++)
				{
				
					if(willItBeANewLife(x,y)) {
						plants.add(new Plant(
						map,
						this.parameters.getPlantEnergy(),
						this.parameters.getWidth(),
						this.parameters.getHeight()));
						plants.get(plants.size() - 1).setPlantPosition(x,y);
					}

				}
		}
		
		
	}
	
	// Przechodzenie po kazdym polu i losowanie szansy na nowa rosline 
	
	boolean willItBeANewLife(int x,int y) {
		//Szanse na rosline w dzungli x3
		if(
			x < getJungleWidth(parameters) && 
			y < getJungleHeight(parameters)
			
		  )  
			{ 
				//System.out.println("x : " + x + " y : " + y);
				Random r = new Random();
				double randomChances = 0.0 + (1.0 - 0.0) * r.nextDouble();
				if(randomChances <= fertility*20) {
					//System.out.println("Junglowa roslina  dla x: " + x + " y : " + y); 
					return true;
			}
			
			else return false;
			}
		else {
			Random r = new Random();
			double randomChances = 0.0 + (1.0 - 0.0) * r.nextDouble();
			if(randomChances <= fertility)  {
				//System.out.println("Sawannowa roslina " + x + " y : " + y); 
				return true;
			} 
			else return false;
		}
		
	} 
	
	public int getJungleHeight(WorldParameters parameters) {
		return (int) (parameters.getHeight()*parameters.getJungleRatio());
	}
	
	public int getJungleWidth(WorldParameters parameters) {
		//System.out.println("Oto wysokosc dzungli : " + parameters.getWidth()*parameters.getJungleRatio());
		return (int) (parameters.getWidth()*parameters.getJungleRatio());
	}


	public int getEpoka() {
		return epoka;
	}


	public void setEpoka(int epoka) {
		this.epoka = epoka;
	}
	
	public double getAverageEnergy() {
		int sum = 0;
		for (int i = (animals.size() - 1);i >= 0;i--) {
            sum = sum +animals.get(i).getEnergy();
            
		}
		if(animals.size()==0) return 0;
		
		return sum/animals.size();
	}
	public double getAverageAge() {
		
		double averageAge = 0;
    	double sum = 0;
    	for(int i = 0; i < ageOfDeadAnimals.size();i++) {
    	    	sum = sum + ageOfDeadAnimals.get(i);
    	    	averageAge = sum / getEpoka();	    	  	
    	}
    	return averageAge;
	}
	public double getAverageAmountOfKids() {
		
		double averageAmountOfKids = 0;
    	double sum = 0;
    	for(int i = 0; i < animals.size();i++) {
    	    	sum = sum + animals.get(i).getAmountOfKids();
    	    	averageAmountOfKids = sum / animals.size();	    	  	
    	}
    	return averageAmountOfKids;
	}
	
	
}
 