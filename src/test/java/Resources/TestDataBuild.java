package Resources;

import java.util.ArrayList;
import java.util.List;

import POJO.GoogleAPI;
import POJO.Location;

public class TestDataBuild {
	
	public GoogleAPI addPlacePayload(String name, String language, String address)
	{
		
		GoogleAPI google = new GoogleAPI();
		Location loc=new Location();
		
		google.setAccuracy(50);
		google.setName(name);
		google.setPhone_number("(+91) 983 893 3937");
		google.setAddress(address);
		google.setWebsite("http://google.com");
		google.setLanguage(language);
		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		google.setTypes(myList);
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		google.setLocation(loc);
		
		return google;
	}
	
	
	public String deletePlacePayload(String placeId)
	{
		return "{\n"
				+ "    \"place_id\": \""+placeId+"\"\n"
				+ "}";
	}

}
