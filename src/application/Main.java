package application;
	
import org.json.JSONObject;

import application.json.JSONUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.image.Image;
import javafx.stage.Stage;



public class Main extends Application {
	

	
	@Override
	public void start(Stage configurationStage) {
		try {
		 
		// Podpinanie pliku fxml do sceny konfiugracji
			Parent root = FXMLLoader.load(getClass().getResource("ConfigurationStage.fxml"));
			Scene scene = new Scene(root);
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			 
			 
		// Konfiguracja sceny 
				
			
			configurationStage.setTitle("Prototype");
			configurationStage.setScene(scene);
			
			
			configurationStage.show();
			
		} catch(Exception e) { 
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		
		 
		// Parsowanie parameters.json
		JSONObject obj = JSONUtils.getJSONObjectFromFile("/parameters.json");
		String[] names = JSONObject.getNames(obj);
	System.out.println("\nJson parameters : ");	
	for(String string : names) {
			System.out.println(string + ": " + obj.get(string));
}

		launch(args);
	}
	 
	
	
	 
	// Funkcje operujące na obiektach gridowych
	
//	public static void startSimulation1() {
//		
//		
////		-umieszcza zwierzęta na środku
////		-każdemu polu losuje czy pojawia się tam roślina
////		- przechodzi do kolejnej tury
//		
//
//	}
//	public static void startSimulation2() {
////		-umieszcza zwierzęta na środku
////		-każdemu polu losuje czy pojawia się tam roślina
////		- przechodzi do kolejnej tury
//		
//		
//	}
	
	
}
 