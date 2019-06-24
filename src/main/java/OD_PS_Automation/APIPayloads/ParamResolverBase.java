package OD_PS_Automation.APIPayloads;

public interface ParamResolverBase {
	
	public String paramName = "";
	public String paramScope = "";
	public String paramResolverType = "";
	public String paramResolvedValue = "";
	
	public String getResolvedParam( String paramName, String paramResolverType);
    public void setParam( String paramScope, String paramName, String paramResolverType);
}
