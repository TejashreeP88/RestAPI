package Resources;

//Enum - its a special class in java which has collection of constants and methods

public enum APIResources {
	
	
	//These are methods
	AddPlaceAPI("/maps/api/place/add/json"), 
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	private String resource;

	//Constructor
	APIResources(String resource) {
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}

}
