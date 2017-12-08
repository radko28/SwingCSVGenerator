package sk.cyklosoft.swingCSVgenerator.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
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
			 Path path = Paths.get(currentDir,fileName);
			 StandardOpenOption soo = Files.exists(path, LinkOption.NOFOLLOW_LINKS)?StandardOpenOption.TRUNCATE_EXISTING:StandardOpenOption.CREATE;
			 writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"), soo);
		 	 csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader(header));
		 	 for(InvoiceData invoiceData:invoiceDataList) {
		 		csvPrinter.printRecord(invoiceData.getFakthaendler(), invoiceData.getRechnungsnr(), invoiceData.getStringBelegdatum(), invoiceData.getAccessCode());
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
	


