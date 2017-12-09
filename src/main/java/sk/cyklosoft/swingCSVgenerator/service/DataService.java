package sk.cyklosoft.swingCSVgenerator.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingWorker;

import sk.cyklosoft.swingCSVgenerator.CSVPanel;
import sk.cyklosoft.swingCSVgenerator.data.FaktVersion;
import sk.cyklosoft.swingCSVgenerator.data.InvoiceData;
import sk.cyklosoft.swingCSVgenerator.data.ShopData;
import sk.cyklosoft.swingCSVgenerator.file.SaveFile;

/**
 * 
 * creating mock data
 * save data to CSV file
 *
 */
public class DataService extends SwingWorker<Void, Integer> {
	private static String MSG_INVOICES_EXPORTED="There is no available invoice";
	private static String MSG_SEARCHING="searching...";
	private CSVPanel csvPanel;
	private Map<Integer, List<InvoiceData>> invoiceDataMap = new LinkedHashMap<Integer,List<InvoiceData>>();
	
	public DataService(CSVPanel csvPanel) {
		this.csvPanel = csvPanel;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
//mock data		
		int count = 0;
		
		for(int fakthaendler = 1; fakthaendler < 1000; fakthaendler++) {
			List<InvoiceData> mockInvoiceDataList = new ArrayList<>();
			//add mock invoices
			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			for(int i = 0; i < 19999; i++) {
				InvoiceData mockInvoiceData = new InvoiceData();
				mockInvoiceData.setAccessCode(i%40);
				if(i%200 == 0 && i > 0) {
					c.add(Calendar.DATE, -1);
				}
				mockInvoiceData.setBelegdatum(c.getTime());
				mockInvoiceData.setFakthaendler(fakthaendler);
				mockInvoiceData.setFaktversion(FaktVersion.FAKTVERSION_AKTUELL);
				mockInvoiceData.setRechnungsnr(fakthaendler + i);
				mockInvoiceDataList.add(mockInvoiceData);
			}
			invoiceDataMap.put(fakthaendler, mockInvoiceDataList);

			count+=19;
			double percentage = 100*(count / 19999.0);
        	publish((int)percentage);
		}
		return null;
	}
	
    @Override
    protected void process(List<Integer> chunks) {
 		csvPanel.getInputPanel().setJlInvoices(MSG_SEARCHING + String.valueOf(chunks.get(chunks.size() - 1) + " %"));
    }

    @Override
    protected void done() {
    	List<InvoiceData> invoiceData = getInvoiceDataList();
    	//save invoiceData to csv
    	String invoicesExported = MSG_INVOICES_EXPORTED;
    	if(invoiceData != null && invoiceData.size() > 0) {
    		SaveFile saveFile = new SaveFile(invoiceData);
    		String fileName = saveFile.setFileName(csvPanel.getInputPanel().getShopData());
    		csvPanel.getInputPanel().setJlFilename(fileName);
    		saveFile.writeCSV();
    		invoicesExported = String.valueOf(invoiceData.size());
		} 
    	csvPanel.getInputPanel().setJlInvoices(invoicesExported);
    }
    
	private List<InvoiceData> getInvoiceDataList() {
		ShopData shopData = csvPanel.getInputPanel().getShopData();
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
