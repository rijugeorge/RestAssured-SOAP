package OD_PS_Automation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AutomationLab {
	final Logger logger = Logger.getLogger("OD_PS_Automation.AutomationLab");

	public static void main(final String[] args) throws IOException {

		final AutomationLab aL = new AutomationLab();
	}

	public AutomationLab() throws IOException {

		final Properties prop = new Properties();
		final FileInputStream fileinput = new FileInputStream(
				"C:\\Users\\riju.george\\eclipse-workspace\\RestAssured-SOAP\\src\\main\\java\\global.properties");
		prop.load(fileinput);
		validateGlobalProperty(prop);
	}

	private void validateGlobalProperty(final Properties prop) {

		final Enumeration<String> enums = (Enumeration<String>) prop.propertyNames();
		while (enums.hasMoreElements()) {
			final String key = enums.nextElement();
			if (key.trim().startsWith("//")) {
				prop.remove(key);
			} else {
				final String value = prop.getProperty(key);
				if (logger.isDebugEnabled()) {
					logger.debug("Config Parameters { \"" + key.trim() + "\": \"" + value.trim() + "\"}");
				}
			}
		}

		if ((prop.getProperty("api.test")).trim().toLowerCase().equals("true")) {

			if ((prop.getProperty("api.authenticateURL") == null)
					|| (prop.getProperty("api.authenticateServicePath") == null)
					|| (prop.getProperty("api.storeId") == null) || (prop.getProperty("api.accessKey") == null)) {
				logger.error(
						"Missing one or more global configurations \"api.authenticateURL\" OR  \"api.authenticateServicePath\" OR \"api.storeId\" OR \"api.accessKey\"");
			}

		} else {
			logger.info("Not executing any WebService steps ");
		}

	}
}
