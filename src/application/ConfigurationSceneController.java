package application;

import java.io.IOException;

import classes.WorldParameters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfigurationSceneController  {
	
	public Stage stage;
	public Scene scene;
	private Parent root;
	 
	// Default config from json
	
	@FXML  
	 Label widthLabelConfig;
	@FXML  
	 Label heightLabelConfig; 
	@FXML  
	 Label startAnimalsLabelConfig;
	@FXML  
	 Label startEnergyLabelConfig;
	@FXML  
	 Label moveEnergyLabelConfig;
	@FXML  
	 Label plantEnergyLabelConfig;
	@FXML  
	 Label jugnleRatioLabelConfig;
	@FXML
	Button starterButton;
	@FXML
	CheckBox useDefaultCheckBox;
	@FXML
	TextField widthTextField;
	@FXML  
	TextField heightTextField;
	@FXML  
	TextField startAnimalsTextField;
	@FXML  
	TextField startEnergyTextField;
	@FXML  
	TextField moveEnergyTextField;
	@FXML  
	TextField plantEnergyTextField;
	@FXML  
	TextField jungleRatioTextField;
	
	
	public void switchToSimulationScene(ActionEvent starterButton) throws IOException {
		
		 
		WorldParameters parameters = new WorldParameters();
		widthLabelConfig.setText(parameters.getWidth().toString());
		heightLabelConfig.setText(parameters.getHeight().toString());
		startAnimalsLabelConfig.setText(parameters.getStartAnimals().toString());
		startEnergyLabelConfig.setText(parameters.getStartEnergy().toString());
		moveEnergyLabelConfig.setText(parameters.getMoveEnergy().toString());
		plantEnergyLabelConfig.setText(parameters.getPlantEnergy().toString());
		jugnleRatioLabelConfig.setText(parameters.getJungleRatio().toString()); 
		 
	 
		
		if(useDefaultCheckBox.isSelected()) {
			System.out.println("Uzywam domyslnych wartosci!");
			
		} else {  
			
			//Odczytywanie wprowadzonych danych od uzytkownika i podmiana worldParameters
			
			parameters.setWidth(Integer.valueOf(widthTextField.getText()));
			parameters.setHeight(Integer.valueOf(heightTextField.getText()));
			parameters.setStartAnimals(Integer.valueOf(startAnimalsTextField.getText()));
			parameters.setStartEnergy(Integer.valueOf(startEnergyTextField.getText()));
			parameters.setMoveEnergy(Integer.valueOf(moveEnergyTextField.getText()));
			parameters.setPlantEnergy(Integer.valueOf(plantEnergyTextField.getText()));
			parameters.setJungleRatio(Double.valueOf(jungleRatioTextField.getText()));
		}
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SimulationStage.fxml"));
		
		root = loader.load();
		
		//Wysylanie danych do podgladu kolejnej sceny
		SimulationSceneController simulationSceneController = loader.getController();
		
	   
		
		simulationSceneController.setLabels(
				parameters.getWidth().toString(),
				parameters.getHeight().toString(),
				parameters.getStartAnimals().toString(),
				parameters.getStartEnergy().toString(),
				parameters.getMoveEnergy().toString() ,
				parameters.getPlantEnergy().toString() ,
				parameters.getJungleRatio().toString());
 
		//Wyswietlenie sceny symulacji
		
		//stage = (Stage)((Node)starterButton.getSource()).getScene().getWindow();
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		
		
		
		
		
	 // Podpinanie pliku css do sceny konfiguracji
	 		String css = this.getClass().getResource("application.css").toExternalForm();
	 		scene.getStylesheets().add(css);
	 		stage.show();
	 		
	}


	
	
	public void setLabels(String width,String height, String startAnimals,String startEnergy, String moveEnergy, String plantEnergy, String jungleRatio) {
		widthLabelConfig.setText(width);
		heightLabelConfig.setText(height);
		startAnimalsLabelConfig.setText(startAnimals);
		startEnergyLabelConfig.setText(startEnergy);
		moveEnergyLabelConfig.setText(moveEnergy);
		plantEnergyLabelConfig.setText(plantEnergy);
		jugnleRatioLabelConfig.setText(jungleRatio);
	} 
	
	 
	
}
