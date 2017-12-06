package sk.cyklosoft.swingCSVgenerator.data;

import java.util.Date;

public class ShopData {
	
	private int shopnr;
	private Date dateFrom;
	private Date dateTo;
	
	public int getShopnr() {
		return shopnr;
	}
	public void setShopnr(int shopnr) {
		this.shopnr = shopnr;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

}
