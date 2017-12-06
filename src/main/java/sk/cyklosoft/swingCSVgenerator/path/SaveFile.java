package sk.cyklosoft.swingCSVgenerator.path;

import sk.cyklosoft.swingCSVgenerator.data.ShopData;

public class SaveFile {
	private static String FILE_NAME = "PINExport_";
	private static String FILE_EXTENSION = ".csv";
	private String fileName;
	
	 public SaveFile() {
		 
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
	

}
