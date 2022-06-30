package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.layout.StackPane;

public class Tile extends StackPane {
	
	
	
	
	List<Animal> AnimalList= new ArrayList<Animal>();
	boolean plant;
	boolean jungle;
	
	private Random random = new Random();

	public void checkPlantLife(float ProbabilityOfPlantLife){
		if(plant == false) {
			plant = (random.nextFloat() < ProbabilityOfPlantLife);
		}
	}
	
	public int getAmountOfAnimals() {
		return AnimalList.size();
	}
}
