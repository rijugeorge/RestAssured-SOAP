package OD_PS_Automation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;

import OD_PS_Automation.APIPayloads.ParamResolverFactory;

public class AutomationLab {
	final Logger logger = Logger.getLogger("OD_PS_Automation.AutomationLab");
	Properties cloneproperty = new Properties();
	ParamResolverFactory prfactory = new ParamResolverFactory();
	public static void main(final String[] args) throws IOException {

		final AutomationLab aL = new AutomationLab();

	}

	public AutomationLab() throws IOException {

		final Properties prop = new Properties();
		final FileInputStream fileinput = new FileInputStream(
				"C:\\Users\\riju.george\\eclipse-workspace\\RestAssured-SOAP\\src\\main\\java\\global.properties");
		prop.load(fileinput);
		validateGlobalProperty(prop);
		Properties cloneproperty = createGlobalPropertyCopy(prop);
		
	}

	private Properties createGlobalPropertyCopy(Properties prop) {
		// TODO Auto-generated method stub
		
		//Properties newProps = new Properties();
		prop.forEach((key, value) -> {
			cloneproperty.setProperty((String) key, (String) value);
		});
		
		return cloneproperty;
		
	}

	private void validateGlobalProperty(final Properties prop) {

		final Enumeration<String> enums = (Enumeration<String>) prop.propertyNames();
		while (enums.hasMoreElements()) {
			final String key = enums.nextElement();
			if (key.trim().startsWith("//") || key.trim().startsWith("#")) {
				prop.remove(key);
			} else {
				final String value = prop.getProperty(key);
				if (logger.isDebugEnabled()) {
					logger.debug("Config Parameters { \"" + key.trim() + "\": \"" + value.trim() + "\"}");
				}
			}
		}

		if ((prop.getProperty("automation.api.test")).trim().toLowerCase().equals("true")) {

			if ((prop.getProperty("automation.api.authenticateURL") == null)
					|| (prop.getProperty("automation.api.authenticateServicePath") == null)
					|| (prop.getProperty("automation.api.storeId") == null) || (prop.getProperty("api.accessKey") == null)) {
				logger.error(
						"Missing one or more global configurations \"automation.api.authenticateURL\" OR  \"automation.api.authenticateServicePath\" OR \"automation.api.storeId\" OR \"automation.api.accessKey\"");
			}

		} else {
			logger.info("Not executing any WebService steps ");
		}

	}
}
