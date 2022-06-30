package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import classes.MapView;
import classes.Simulation;
import classes.WorldParameters;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class SimulationSceneController {
	 
	
	//private Parent root;
	//WorldParameters parameters;
	@FXML
	AnchorPane anchorPanelSimulation;
	@FXML  
	Label widthLabel;
	@FXML  
	Label heightLabel; 
	@FXML  
	Label startAnimalsLabel;
	@FXML  
	Label startEnergyLabel;
	@FXML  
	Label moveEnergyLabel;
	@FXML  
	Label plantEnergyLabel;
	@FXML  
	Label jugnleRatioLabel;
	@FXML
	Button stepButton;
	@FXML 
	Button buttonTurnOnMap1;
	@FXML 
	Button buttonTurnOffMap1;
	@FXML 
	Button buttonTurnOnMap2;
	@FXML 
	Button buttonTurnOffMap2;
	@FXML
	Pane pane1;
	@FXML 
	Pane pane2;
	
	@FXML
	Label labelCurrentEraMap1; 
	@FXML 
	Label amountOfPlants1;
	@FXML 
	Label amountOfAnimals1;
	@FXML
	Label dominantGenotype1;
	@FXML
	Label averageLevelOfEnergy1;
	@FXML
	Label averageLifespan1;
	@FXML
	Label averageAmountOfKids1;
	
	@FXML
	Label labelCurrentEraMap2; 
	@FXML 
	Label amountOfPlants2;
	@FXML 
	Label amountOfAnimals2;
	@FXML
	Label dominantGenotype2;
	@FXML
	Label averageLevelOfEnergy2;
	@FXML
	Label averageLifespan2;
	@FXML
	Label averageAmountOfKids2;
	
	Simulation simulation1;
	Simulation simulation2;
	
	MapView map1;
	MapView map2;
	
	Boolean map1Active = true;
	Boolean map2Active = true;
	
	// Wyjscie z aplikacji
 
	
	public void closeSimulation(ActionEvent endSimulationAndSwitchButton) throws IOException {
		System.out.println("Wracam do konfiguracji...");
		stop();
//		Parent root;
//		root = FXMLLoader.load(getClass().getResource("ConfigurationStage.fxml"));
//		stage = (Stage)((Node)endSimulationAndSwitchButton.getSource()).getScene().getWindow();
//		scene = new Scene(root);
//		// Podpinanie pliku css do sceny konfiguracji
//		String css = this.getClass().getResource("application.css").toExternalForm();
//		scene.getStylesheets().add(css);
//
//	  
//		stage.setScene(scene);
//		stage.show();
		Platform.exit();
		
	} 
	
	
	
	
	public void setLabels(String width,String height, String startAnimals,String startEnergy, String moveEnergy, String plantEnergy, String jungleRatio) {
		widthLabel.setText(width);
		heightLabel.setText(height);
		startAnimalsLabel.setText(startAnimals);
		startEnergyLabel.setText(startEnergy);
		moveEnergyLabel.setText(moveEnergy);
		plantEnergyLabel.setText(plantEnergy);
		jugnleRatioLabel.setText(jungleRatio);
	} 
	 
	
	
	public void startBothSimulations(ActionEvent startBothSimulationsButton) throws Exception {
		
		// Ładowanie parametów
		
		WorldParameters worldParameters = new WorldParameters();
		
		worldParameters.setWidth(Integer.valueOf(widthLabel.getText()));
		worldParameters.setHeight(Integer.valueOf(heightLabel.getText()));
		worldParameters.setStartAnimals(Integer.valueOf(startAnimalsLabel.getText()));
		worldParameters.setStartEnergy(Integer.valueOf(startEnergyLabel.getText()));
		worldParameters.setMoveEnergy(Integer.valueOf(moveEnergyLabel.getText()));
		worldParameters.setPlantEnergy(Integer.valueOf(plantEnergyLabel.getText()));
		worldParameters.setJungleRatio(Double.valueOf(jugnleRatioLabel.getText()));

		// Ładowanie map
		
		loadFirstMap(worldParameters);
		loadSecondMap(worldParameters);
		map1Active = true;
        map2Active = true;
				
				
				
	}
	
	//Konfiguracja mapy nr 1
	public void loadFirstMap(WorldParameters parameters) throws Exception {
		
		map1 = new MapView(parameters);
		
		// Ustawiane skali
		double scale;
		if(parameters.getHeight()>=parameters.getWidth()) 
		{ 
			scale = pane1.getPrefHeight()/parameters.getHeight();
		}
		else scale = pane1.getPrefWidth()/parameters.getWidth();
		
        map1.setId("firstMap");
        map1.setScaleX(scale);
        map1.setScaleY(scale);
        map1.setTranslateX(parameters.getWidth()/2*(scale-1));
        map1.setTranslateY(parameters.getHeight()/2*(scale-1));
        
        simulation1 = new Simulation(parameters,map1);
        pane1.getChildren().add(map1);
		
		
		
	}
	

	//Konfiguracja mapy nr 2
	public void loadSecondMap(WorldParameters parameters) {
		
		map2 = new MapView(parameters);

		double scale;
		
		
		
		if(parameters.getHeight()>=parameters.getWidth()) 
		{ 
			scale = pane2.getPrefHeight()/parameters.getHeight();
		}
		else 
		{
			scale = pane2.getPrefWidth()/parameters.getWidth();
		}
        map2.setScaleX(scale);
        map2.setScaleY(scale);
        map2.setTranslateX(parameters.getWidth()/2*(scale-1));
        map2.setTranslateY(parameters.getHeight()/2*(scale-1));
        
        simulation2 = new Simulation(parameters,map2);
        pane2.getChildren().add(map2);
		
		
	}
	
	@FXML
	public void reset() {
        stop();
        map1Active = false;
        map2Active = false;
        clock.resetTicks();
        pane1.getChildren().clear();
        pane2.getChildren().clear();

    }
	
	
	private Movement clock;

    private  class Movement extends AnimationTimer {
        private long FRAMES_PER_SEC = 30L;
        private long INTERVAL = 1_000_000_000L / FRAMES_PER_SEC;

        private long last = 0;
        private int ticks = 0;

        @Override
        public void handle(long now) {
            if(now - last > INTERVAL) {

            	if(map1Active) step(simulation1);
            	if(map2Active) step(simulation2);

	            updateLabels();
	            
                last = now;
                tick();
            }
        }

        private void updateLabels() {
        	labelCurrentEraMap1.setText(String.valueOf(simulation1.getEpoka()));
        	amountOfAnimals1.setText(String.valueOf(simulation1.animals.size()));
        	amountOfPlants1.setText(String.valueOf(simulation1.plants.size()));
        	//TODO dominant
        	dominantGenotype1.setText("NAN");
        	averageLevelOfEnergy1.setText(String.valueOf(simulation1.getAverageEnergy()));
        	averageLifespan1.setText(String.valueOf(simulation1.getAverageAge()));
        	averageAmountOfKids1.setText(String.valueOf(simulation1.getAverageAmountOfKids()));
        	
        	labelCurrentEraMap2.setText(String.valueOf(simulation2.getEpoka()));
        	amountOfAnimals2.setText(String.valueOf(simulation2.animals.size()));
        	amountOfPlants2.setText(String.valueOf(simulation2.plants.size()));
        	// TODO dominant
        	dominantGenotype2.setText("NAN");
        	averageLevelOfEnergy2.setText(String.valueOf(simulation2.getAverageEnergy()));
        	averageLifespan2.setText(String.valueOf(simulation2.getAverageAge()));
        	averageAmountOfKids2.setText(String.valueOf(simulation2.getAverageAmountOfKids()));
		}

		public void resetTicks() {
            ticks=0;
        }
        
        public void tick() {
            ticks ++;
        }
    }
    @FXML
    public void start() {

    	initialized();
    	turnOnMap1();
        turnOnMap2();
        clock.start();
        
        
    }

    @FXML
    public void stop() {
        turnOffMap1();
        turnOffMap2();
        clock.stop();
    }
    
    private void turnOffMap1() {
    	
    	map1Active = false;
		
	}
    private void turnOffMap2() {
    	map2Active = false;
		
	}
    private void turnOnMap1() {
    	//initialized();
    	map1Active = true;
    	clock.start();
		
	}
    private void turnOnMap2() {
    	//initialized();
    	map2Active = true;
    	clock.start();
		
	}





	@FXML
    public void initialized(){
        clock = new Movement();
    }
     
     
     
   
    public void step(Simulation simulation) { 
		simulation.move();
		simulation.generateNewPlants();
		simulation.removeDeadAnimals();
		simulation.drawObjects();
		simulation.setEpoka(simulation.getEpoka() + 1);
		simulation.amountOfAnimals.add(simulation.animals.size());
		simulation.amountOfPlants.add(simulation.plants.size());
		simulation.amountOfEnergy.add((int) simulation.getAverageEnergy());
	} 

    public void turnOnMap1(ActionEvent buttonTurnOnMap1) throws IOException {
	map1Active = true;
	
    }
    public void turnOffMap1(ActionEvent buttonTurnOffMap1) throws IOException {
    	map1Active = false;
    	
    }
    public void turnOnMap2(ActionEvent buttonTurnOnMap2) throws IOException {
    	map2Active = true;
    	
    }
    public void turnOffMap2(ActionEvent buttonTurnOffMap2) throws IOException {
    	map2Active = false;
    	
    }
    
    public void generateStatsMap1(ActionEvent buttonGenerateStatsMap1) throws IOException {
    	File file = new File("Stats1.txt");
    	FileWriter fw = new FileWriter(file);
    	PrintWriter pw = new PrintWriter(fw);
    	
    	pw.println("Oto statystyki mapy 1  dla : " + simulation1.getEpoka() + " epoki !");
    	
    // SREDNIA ILOSC ZWIERZAT
    	double averageAnimals = 0;
    	double sum = 0;
    	for(int i = 0; i < simulation1.amountOfAnimals.size();i++) {
    	    	sum = sum + simulation1.amountOfAnimals.get(i);
    	    	averageAnimals = sum / simulation1.getEpoka();    	
    	}
    	pw.println("Srednia ilosc zwierzat w tej symulacji : "  + averageAnimals );
    	
    // SREDNIA ILOSC ROSLIN
    	double averagePlants = 0;
    	sum = 0;
    	for(int i = 0; i < simulation1.amountOfPlants.size();i++) {
    	    	sum = sum + simulation1.amountOfPlants.get(i);
    	    	averagePlants = sum / simulation1.getEpoka();	    	
    	}
    	pw.println("Srednia ilosc roslin w tej symulacji : "  + averagePlants );
    	
    //  SREDNIA ILOSC ENERGII
    	double averageEnergy = 0;
    	sum = 0;
    	for(int i = 0; i < simulation1.amountOfEnergy.size();i++) {
    	    	sum = sum + simulation1.amountOfEnergy.get(i);
    	    	averageEnergy = sum / simulation1.getEpoka();	    	
    	}
    	pw.println("Srednia ilosc energii w tej symulacji : "  + averageEnergy );
    	
    // SREDNI WIEK MARTWYCH ZWIERZAT
    	double averageAge = 0;
    	sum = 0;
    	for(int i = 0; i < simulation1.ageOfDeadAnimals.size();i++) {
    	    	sum = sum + simulation1.ageOfDeadAnimals.get(i);
    	    	averageAge = sum / simulation1.getEpoka();	    	
    	}
    	pw.println("Sredni wiek martwych zwierzat w tej symulacji : "  + averageAge );
    	
    // TODO 
    // SREDNIA ILOSC DZIECI DLA ZYJACYCH ZWIERZAT 
    // DOMINUJACY GENOM
    	
    	pw.close();
    	
    }
    public void generateStatsMap2(ActionEvent buttonGenerateStatsMap2) throws IOException {
    	File file = new File("Stats2.txt");
    	FileWriter fw = new FileWriter(file);
    	PrintWriter pw = new PrintWriter(fw);
    	
    	pw.println("Oto statystyki mapy 2  dla : " + simulation2.getEpoka() + " epoki !");
    	
    // SREDNIA ILOSC ZWIERZAT
    	double averageAnimals = 0;
    	double sum = 0;
    	for(int i = 0; i < simulation2.amountOfAnimals.size();i++) {
    	    	sum = sum + simulation2.amountOfAnimals.get(i);
    	    	averageAnimals = sum / simulation2.getEpoka();    	
    	}
    	pw.println("Srednia ilosc zwierzat w tej symulacji : "  + averageAnimals );
    	
    // SREDNIA ILOSC ROSLIN
    	double averagePlants = 0;
    	sum = 0;
    	for(int i = 0; i < simulation2.amountOfPlants.size();i++) {
    	    	sum = sum + simulation2.amountOfPlants.get(i);
    	    	averagePlants = sum / simulation2.getEpoka();	    	
    	}
    	pw.println("Srednia ilosc roslin w tej symulacji : "  + averagePlants );
    	
    //  SREDNIA ILOSC ENERGII
    	double averageEnergy = 0;
    	sum = 0;
    	for(int i = 0; i < simulation2.amountOfEnergy.size();i++) {
    	    	sum = sum + simulation2.amountOfEnergy.get(i);
    	    	averageEnergy = sum / simulation2.getEpoka();	    	
    	}
    	pw.println("Srednia ilosc energii w tej symulacji : "  + averageEnergy );
    	
    // SREDNI WIEK MARTWYCH ZWIERZAT
//    	double averageAge = 0;
//    	sum = 0;
//    	for(int i = 0; i < simulation2.ageOfDeadAnimals.size();i++) {
//    	    	sum = sum + simulation2.ageOfDeadAnimals.get(i);
//    	    	averageAge = sum / simulation2.getEpoka();	    	
//    	}
    	pw.println("Sredni wiek martwych zwierzat w tej symulacji : "  + simulation2.getAverageAge() );
    	
    // TODO 
    // SREDNIA ILOSC DZIECI DLA ZYJACYCH ZWIERZAT 
    // DOMINUJACY GENOM
    	
    	pw.close();
    	
    }
    
    public void circleChosen(ActionEvent buttonGenerateStatsMap2) throws IOException {
    	
    	//TODO MOUSE EVENT 
    }
    
}
