package utils;

import java.util.Comparator;
import java.util.Date;


public class CustomItem implements Comparator<CustomItem>{

	public String merchantId;
	public String terminalId;
	public Date dateTime;
	public String cardNumber;
	public String status;
	public String purchaseAmount;
	public Float totalAmount;
	public String cardType;

	
	//sort by date 
	
	@Override
    public int compare(CustomItem o1, CustomItem o2) {
        // write comparison logic here like below , it's just a sample
        return o1.getDateTime().compareTo(o2.getDateTime());
    }
	
	
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(String purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	public Float getTotalAmount() {
		return totalAmount;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	//check for null 
	public boolean isValid() {
	    return cardNumber != null && dateTime != null && status != null && totalAmount!=null;
	  }
	

	
	
}
