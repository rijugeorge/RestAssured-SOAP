package OD_PS_Automation.APIPayloads;

public interface PayloadCreatorBase {
	
	public String unResolvedPayload = "";
	public String resolvedPayload = "";
	
	public void getFullyResolvedPayload();
	public void getUnResolvedPayload();

}
