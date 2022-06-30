package classes;

import org.json.JSONObject;

import application.json.JSONUtils;

 public class WorldParameters {
	
	
	Integer width;
	private Integer height;
	private Integer startAnimals;
	private Integer startEnergy;
	private Integer moveEnergy;
	private Integer plantEnergy;
	private Double jungleRatio;
	 
	public WorldParameters() {
		super();
		JSONObject obj = JSONUtils.getJSONObjectFromFile("/parameters.json");
	
//		for(String string : names) {
//			System.out.println(string + ": " + obj.get(string));
//		}
		this.width = obj.getInt("width");
		this.height = obj.getInt("height");
		this.startAnimals = obj.getInt("startAnimals");
		this.startEnergy = obj.getInt("startEnergy");
		this.moveEnergy = obj.getInt("moveEnergy");
		this.plantEnergy = obj.getInt("plantEnergy");
		this.jungleRatio = obj.getDouble("jungleRatio");
	}
	
	public WorldParameters(int width, int height, int startAnimals, int startEnergy, int moveEnergy, int plantEnergy,
			double jungleRatio) {
		super();
		this.width = width;
		this.height = height;
		this.startAnimals = startAnimals;
		this.startEnergy = startEnergy;
		this.moveEnergy = moveEnergy;
		this.plantEnergy = plantEnergy;
		this.jungleRatio = jungleRatio;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getStartAnimals() {
		return startAnimals;
	}
 
	public void setStartAnimals(Integer startAnimals) {
		this.startAnimals = startAnimals;
	}

	public Integer getStartEnergy() {
		return startEnergy;
	}

	public void setStartEnergy(Integer startEnergy) {
		this.startEnergy = startEnergy;
	}

	public Integer getMoveEnergy() {
		return moveEnergy;
	}

	public void setMoveEnergy(Integer moveEnergy) {
		this.moveEnergy = moveEnergy;
	}

	public Integer getPlantEnergy() {
		return plantEnergy;
	}

	public void setPlantEnergy(Integer plantEnergy) {
		this.plantEnergy = plantEnergy;
	}

	public Double getJungleRatio() {
		return jungleRatio;
	}

	public void setJungleRatio(Double jungleRatio) {
		this.jungleRatio = jungleRatio;
	}
	
	
	
	
}
	

