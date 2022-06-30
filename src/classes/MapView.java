package classes;

import javafx.scene.layout.Pane;

public class MapView extends Pane {
		
	
	WorldParameters parameters;
	
	//private Affine affine;
	 
	public MapView(WorldParameters parameters) {
		
		this.parameters = parameters;
		setPrefSize(parameters.getWidth(),parameters.getHeight());
		
		
		// Proba skalowania
		//this.affine = new Affine();
		//this.affine.appendScale(620/parameters.getWidth(),496/parameters.getHeight());
		
	} 
	
	
	 
} 
