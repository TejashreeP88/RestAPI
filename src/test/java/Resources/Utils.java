package Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException
	{
		if (req==null)
			
		{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
	//	RestAssured.baseURI="https://rahulshettyacademy.com";
		req=new RequestSpecBuilder().setBaseUri(getGlobalValues("baseURL")).addQueryParam("key", "qaclick123")
				// create a Log file
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		
			return req;
		
		}		
		
		return req;
	}
	
	public static String getGlobalValues(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("/Users/tejashreepatwardhan/eclipse-workspace/APIFramework/src/test/java/Resources/Global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
		
	}
	
	public String getJsonPath(Response response, String key)
	{
		String responseString=response.asString();
		System.out.println(responseString);
		
		JsonPath js = new JsonPath(responseString);
		return js.get(key).toString();
	}

}
