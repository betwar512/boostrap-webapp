package utils;

public class Settlement {

	public String merchantId;
	public String date;
	public String purchCount;
	public Float purchAmount;
	public String terminalId;
	
	
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPurchCount() {
		return purchCount;
	}
	public void setPurchCount(String purchCount) {
		this.purchCount = purchCount;
	}
	public Float getPurchAmount() {
		return purchAmount;
	}
	public void setPurchAmount(Float purchAmount) {
		this.purchAmount = purchAmount;
	}
	
	
	
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
		//check for null 
		public boolean isValid() {
		    return merchantId != null || purchCount!=null;
		  }
}
