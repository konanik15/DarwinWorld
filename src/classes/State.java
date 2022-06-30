package classes;

import javafx.scene.paint.Color;

public class State {
	int startEnergy;
	public static Color color;
	public State(int energy) {
		super();
		this.startEnergy = energy;
		
	}
	
	public State() {
		// TODO Auto-generated constructor stub
	}

	public Color checkState(int energy) {
		
		double ratio = (double) energy/ (double) startEnergy;
		

		if(energy==0) {
			color = Color.BLACK;
			return color;
			
		}
		
		if(ratio >= 1) {
			color = Color.rgb(255, 0, 0);
			return color;
		}
		if((1 > ratio) && (ratio >= 0.75)) {
			color = Color.rgb(192, 0, 0);		
			return color;
		}
		if(0.75 > (ratio) && (ratio) >= 0.5) {
			color = Color.rgb(131, 0, 0);		
			return color;
		}
		if(0.5 > (ratio) && (ratio) >= 0.25) {
			color = Color.rgb(106, 0, 0);		
			return color;
		}
		if(0.25 > (ratio) && (ratio) >= 0.00) {
			color =  Color.rgb(76, 0, 0);
			return color;
			
		}
	return color;
		
		
	//	return Color.rgb(255, 0, 0);
		
	}
	
}
