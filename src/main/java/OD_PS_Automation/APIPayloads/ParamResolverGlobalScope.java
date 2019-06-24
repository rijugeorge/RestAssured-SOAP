package OD_PS_Automation.APIPayloads;

import java.util.Properties;

public class ParamResolverGlobalScope implements ParamResolverBase {

	private Properties prop;

	private ParamResolverGlobalScope(Properties prop) {
		this.prop = prop;
	}

	public String getResolvedParam(String paramName, String paramResolverType) {

		String returnValue = (String) prop.getOrDefault(paramName, "GLOBALNOTSET");

		if (returnValue.equals("GLOBALNOTSET")) {

			return null;

		} else {
			return returnValue;
		}

	}

	@Override
	public void setParam(String paramScope, String paramName, String paramResolverType) {
		// TODO Auto-generated method stub

	}

}
