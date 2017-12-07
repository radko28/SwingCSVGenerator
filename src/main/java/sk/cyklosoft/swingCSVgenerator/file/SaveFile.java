package sk.cyklosoft.swingCSVgenerator.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.CodeSource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import sk.cyklosoft.swingCSVgenerator.CSVPanel;
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
	 
	 public String setFileName(ShopData shopData) {
		 StringBuffer sb = new StringBuffer();
		 sb.append(FILE_NAME);
		 SimpleDateFormat sdf= new SimpleDateFormat(CSVPanel.DATE_FORMAT);
		 sb.append(String.valueOf(shopData.getShopnr()));
		 sb.append("_");
		 sb.append(String.valueOf(sdf.format(shopData.getDateFrom())));
		 sb.append("_");
		 sb.append(String.valueOf(sdf.format(shopData.getDateTo())));
		 sb.append(FILE_EXTENSION);
		 fileName = sb.toString();
		 return fileName;
	 }
	 
	 public void writeCSV() {
		 CSVPrinter csvPrinter = null;
		 BufferedWriter writer = null;
		 try { 
			 String currentDir = new java.io.File( "." ).getCanonicalPath();
			 writer = Files.newBufferedWriter(Paths.get(currentDir,fileName), Charset.forName("UTF-8"), StandardOpenOption.CREATE_NEW);
		 	 csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader(header));
		 	 for(InvoiceData invoiceData:invoiceDataList) {
			 		csvPrinter.printRecord("1", "Sundar Pichai", "CEO", "Google");
				 	csvPrinter.printRecord("2", "Satya Nadella", "CEO", "Microsoft");
				 	csvPrinter.printRecord("3", "Tim cook", "CEO", "Apple");
				 	csvPrinter.printRecord(Arrays.asList("4", "Mark Zuckerberg", "CEO", "Facebook"));		 		 
		 	 }
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
	


