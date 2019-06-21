package OD_PS_Automation.RestAssured_SOAP;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


/**
 * Hello world!
 *
 */
public class Authentication 
{
/*    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }*/
	
	@Test
	public void AuthenticateTest() throws Exception {


		XmlPath xmlPath;
		Response response;
		
		Properties prop = new Properties();
		FileInputStream fileinput = new FileInputStream("C:\\Users\\riju.george\\eclipse-workspace\\RestAssured-SAOP\\src\\main\\java\\global.properties");
		prop.load(fileinput);
		
		String URI = prop.getProperty("automation.api.authenticateURL", "https://webservices.sandbox.orderdynamics.net");
		String servicePath = prop.getProperty("automation.api.authenticateServicePath", "/AuthenticationService.svc");
		String storeID = prop.getProperty("automation.api.storeId", "Test123");
		String accessKey = prop.getProperty("automation.api.accessKey", "Test123");

        String xml_text = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">" + 
        		"   <soapenv:Header/><soapenv:Body><tem:Authenticate><tem:storeId>"+ storeID + 
        		   "</tem:storeId><tem:accessKey>" + accessKey + 
        		   "</tem:accessKey></tem:Authenticate></soapenv:Body></soapenv:Envelope>";
        
		RestAssured.baseURI = URI;
		response = 
		                given()
		                    .request().body(xml_text).headers("SOAPAction", "http://tempuri.org/IAuthenticationService/Authenticate", "Content-Type", "text/xml;charset=UTF-8" ).
		                when()
		                    .post(servicePath).
		                then()
		                    .assertThat()
		                        .statusCode(200).extract().response();
		//System.out.println("Response Message : " + response.asString());
		xmlPath = response.xmlPath();
		//System.out.println("XMLPath : " + xmlPath.toString());
		String nodeValue= xmlPath.getString("Envelope.Body.AuthenticateResponse.AuthenticateResult.Result");
		
     	if (!nodeValue.isEmpty()) {
     		System.out.println("Succesfully Generated the API token : " + nodeValue);
     		Assert.assertTrue(true);
     	}
     	else {
     		Assert.assertTrue(false);
     	}

	}
}
