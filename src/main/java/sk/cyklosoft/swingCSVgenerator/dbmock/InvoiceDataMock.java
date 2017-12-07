package sk.cyklosoft.swingCSVgenerator.dbmock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import sk.cyklosoft.swingCSVgenerator.data.FaktVersion;
import sk.cyklosoft.swingCSVgenerator.data.InvoiceData;
import sk.cyklosoft.swingCSVgenerator.data.ShopData;

public class InvoiceDataMock {
	Map<Integer, List<InvoiceData>> invoiceDataMap = new LinkedHashMap<Integer,List<InvoiceData>>();
	
	public InvoiceDataMock() {
		
		for(int fakthaendler = 1; fakthaendler < 1000; fakthaendler++) {
			List<InvoiceData> mockInvoiceDataList = new ArrayList<>();
			//add mock invoices
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -10);
			for(int i = 0; i < 1999; i++) {
				InvoiceData mockInvoiceData = new InvoiceData();
				mockInvoiceData.setAccessCode(fakthaendler%3);
				mockInvoiceData.setBelegdatum(c.getTime());
				mockInvoiceData.setFakthaendler(fakthaendler);
				mockInvoiceData.setFaktversion(FaktVersion.FAKTVERSION_AKTUELL);
				mockInvoiceData.setRechnungsnr(fakthaendler + 1);
				mockInvoiceDataList.add(mockInvoiceData);
			}
			invoiceDataMap.put(fakthaendler, mockInvoiceDataList);
		}
		
	}
	public List<InvoiceData> getInvoiceDataList(ShopData shopData) {
		List<InvoiceData> searchDataList = invoiceDataMap.get(shopData.getShopnr());
		if(searchDataList != null) {
			return getInvoiceByDate(searchDataList, shopData); 
		} else {
			return null;
		}
	}
	private List<InvoiceData> getInvoiceByDate(
			List<InvoiceData> searchDataList, ShopData shopData) {
		List<InvoiceData> invoiceDataListByDate = new ArrayList<>();
		for(InvoiceData invoiceData : searchDataList) {
			if(invoiceData.getBelegdatum().compareTo(shopData.getDateTo()) <=0 && invoiceData.getBelegdatum().compareTo(shopData.getDateFrom()) >=0 && invoiceData.getFaktversion() == FaktVersion.FAKTVERSION_AKTUELL) {
				invoiceDataListByDate.add(invoiceData);
			}
		}
		return invoiceDataListByDate;
	}

}
