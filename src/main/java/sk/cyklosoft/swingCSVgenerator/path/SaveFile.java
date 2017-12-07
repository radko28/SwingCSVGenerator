package sk.cyklosoft.swingCSVgenerator.path;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import sk.cyklosoft.swingCSVgenerator.data.InvoiceData;
import sk.cyklosoft.swingCSVgenerator.data.ShopData;

public class SaveFile {
	private static String FILE_NAME = "PINExport_";
	private static String FILE_EXTENSION = ".csv";
	private String fileName;
	private List<InvoiceData> invoiceDataList;
	private String[] header = {"Invoice to ShopNr","Invoice Number", "Invoice Date", "PIN"};
	
	 public SaveFile(List<InvoiceData> invoiceDataList) {
		 this.invoiceDataList = invoiceDataList;
	 }
	 
	 public void setFileName(ShopData shopData) {
		 StringBuffer sb = new StringBuffer();
		 sb.append(FILE_NAME);
		 sb.append(String.valueOf(shopData.getShopnr()));
		 sb.append("_");
		 sb.append(String.valueOf(shopData.getDateFrom()));
		 sb.append("_");
		 sb.append(String.valueOf(shopData.getDateTo()));
		 sb.append(FILE_EXTENSION);
		 fileName = sb.toString();
	 }
	 
	 public void writeCSV() {
		 CSVPrinter csvPrinter = null;
		 BufferedWriter writer = null;
		 try { 
			writer = Files.newBufferedWriter(Paths.get(fileName), null, null);
		 	csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader(header));
		 	csvPrinter.printRecord("1", "Sundar Pichai", "CEO", "Google");
		 	csvPrinter.printRecord("2", "Satya Nadella", "CEO", "Microsoft");
		 	csvPrinter.printRecord("3", "Tim cook", "CEO", "Apple");
		 	csvPrinter.printRecord(Arrays.asList("4", "Mark Zuckerberg", "CEO", "Facebook"));
		 } catch (IOException e) {
			 e.printStackTrace();
		 } finally {
			 try {
				 csvPrinter.flush();
				 csvPrinter.close();
				 writer.close();
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
		 }
	 }
}
	


