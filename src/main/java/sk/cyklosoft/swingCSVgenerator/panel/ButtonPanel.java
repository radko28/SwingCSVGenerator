package sk.cyklosoft.swingCSVgenerator.panel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import sk.cyklosoft.swingCSVgenerator.CSVPanel;
import sk.cyklosoft.swingCSVgenerator.data.InvoiceData;
import sk.cyklosoft.swingCSVgenerator.dbmock.InvoiceDataMock;
import sk.cyklosoft.swingCSVgenerator.file.SaveFile;

public class ButtonPanel extends JPanel {
	private static String BUTTON_CANCEL="Cancel";
	private static String BUTTON_OK="Start";
	private JButton jbtOk = new JButton(BUTTON_OK);
	
	public ButtonPanel(final CSVPanel csvPanel) {
		setLayout(new FlowLayout());
		
    	jbtOk.setEnabled(false);
    	JButton jbtCancel=new JButton(BUTTON_CANCEL);
    	add(jbtOk);
		add(jbtCancel);
    	
		jbtOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
  				//search values in database
	  			List<InvoiceData> invoiceData = new InvoiceDataMock().getInvoiceDataList(csvPanel.getInputPanel().getShopData());
  				//progress status
  				//8.12
	  				
  				//save invoiceData to csv 
  				//7.12
	  			SaveFile saveFile = new SaveFile(invoiceData);
	  			saveFile.setFileName(csvPanel.getInputPanel().getShopData());
	  			saveFile.writeCSV();
  				//view invoice count invoiceData.size()
	  			csvPanel.getInputPanel().setJlInvoices(invoiceData == null?0:invoiceData.size());
			}
		});			  	
    	
		jbtCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
        });   
	}

	public JButton getJbtOk() {
		return jbtOk;
	}
}
