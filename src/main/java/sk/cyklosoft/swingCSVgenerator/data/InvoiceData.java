package sk.cyklosoft.swingCSVgenerator.data;

import java.util.Date;

public class InvoiceData {
	
	private int fakthaendler;
	private int rechnungsnr;
	private Date belegdatum;
	private int accessCode;
	private FaktVersion faktversion;
	
	public int getFakthaendler() {
		return fakthaendler;
	}
	public void setFakthaendler(int fakthaendler) {
		this.fakthaendler = fakthaendler;
	}
	public int getRechnungsnr() {
		return rechnungsnr;
	}
	public void setRechnungsnr(int rechnungsnr) {
		this.rechnungsnr = rechnungsnr;
	}
	public Date getBelegdatum() {
		return belegdatum;
	}
	public void setBelegdatum(Date belegdatum) {
		this.belegdatum = belegdatum;
	}
	public int getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(int accessCode) {
		this.accessCode = accessCode;
	}
	public FaktVersion getFaktversion() {
		return faktversion;
	}
	public void setFaktversion(FaktVersion faktversion) {
		this.faktversion = faktversion;
	}
	
	

}
