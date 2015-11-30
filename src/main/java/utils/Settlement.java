package utils;

public class Settlement {

	
	
	public String merchantId;
	public String date;
	public String purchCount;
	public String purchAmount;
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
	public String getPurchAmount() {
		return purchAmount;
	}
	public void setPurchAmount(String purchAmount) {
		this.purchAmount = purchAmount;
	}
	//check for null 
		public boolean isValid() {
		    return merchantId != null || purchCount!=null;
		  }
}
