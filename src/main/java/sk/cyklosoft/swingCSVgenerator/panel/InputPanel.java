package sk.cyklosoft.swingCSVgenerator.panel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import sk.cyklosoft.swingCSVgenerator.CSVPanel;
import sk.cyklosoft.swingCSVgenerator.data.ShopData;

public class InputPanel extends JPanel {
	private static String MSG_DATE_IN_FUTURE="Date must not be in future";
	private static String MSG_DATE_FROM_AFTER_TO="From-Date must be before To-Date";
	private static String MSG_SHOP_NR="0 is not allowed,range:1-9999999";
	private static String DATE_FORMAT="dd-MM-yyyy";
	final private JLabel jlErrorDateFrom = new JLabel();
	final private  JLabel jlErrorDateTo = new JLabel();
	final private JLabel jlErrorShopNr = new JLabel(MSG_SHOP_NR);
	final private JTextField jtShopnr = new JTextField();
	private JLabel jlInvoices = new JLabel();
	final private JXDatePicker jxdpDateTo = new JXDatePicker();
	final private JXDatePicker jxdpDateFrom = new JXDatePicker();
	private boolean jxdpDateFromValid = true;
	private boolean jxdpDateToValid = true;
	private boolean jtShopnrValid = false;
	
	public InputPanel(final CSVPanel csvPanel) {
		setLayout(new GridBagLayout());
    	
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.anchor=GridBagConstraints.WEST;
		gbc.insets=new Insets(5,10,5,10);
//labels, textfields
		gbc.gridx=0;
		gbc.gridy=0;
		add(new JLabel("Invoice to ShopNr"),gbc);
		gbc.gridy++;
		add(new JLabel("Invoice Date from"),gbc);
		gbc.gridy++;
		add(new JLabel("Invoice Date to"),gbc);
		gbc.gridy++;
		add(new JLabel("Filename"),gbc);
		gbc.gridy++;
		add(new JLabel("Invoices exported"),gbc);

		gbc.gridx=1;
		gbc.gridy=0;
		jtShopnr.setColumns(10);
		add(jtShopnr,gbc);
		
		jtShopnr.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(jtShopnr.getText().length() == 0 || jtShopnr.getText().length() >= 1 && jtShopnr.getText().charAt(0) == '0') {
					jlErrorShopNr.setForeground(Color.RED);
					jtShopnrValid = false;
				} else {
					jlErrorShopNr.setForeground(Color.BLACK);
					jtShopnrValid = true;
				}
				csvPanel.getButtonPanel().getJbtOk().setEnabled(jxdpDateFromValid && jxdpDateToValid && jtShopnrValid);
			}

			@Override
			public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
	            if(jtShopnr.getText().length() > 8) {
	            	e.consume();
	            }
	            if (!((c >= '0') && (c <= '9') ||
	               (c == KeyEvent.VK_BACK_SPACE) ||
	               (c == KeyEvent.VK_DELETE))) {
	              e.consume();
	            }
	        }
		});
		gbc.gridy++;
	
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf= new SimpleDateFormat(DATE_FORMAT);
		c.add(Calendar.DATE, -180);
		jxdpDateFrom.setDate(c.getTime());
		jxdpDateFrom.setFormats(sdf);
		add(jxdpDateFrom,gbc);
		gbc.gridy++;
		
		Calendar cTo = Calendar.getInstance();
		jxdpDateTo.setDate(cTo.getTime());
		jxdpDateTo.setFormats(sdf);
		
		jxdpDateFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jxdpDateFrom.getDate().compareTo(jxdpDateTo.getDate()) == 1 || jxdpDateFrom.getDate().compareTo(new Date()) == 1) {
					if(jxdpDateFrom.getDate().compareTo(new Date()) == 1) {
						jlErrorDateFrom.setText(MSG_DATE_IN_FUTURE);
					} else {
						jlErrorDateFrom.setText(MSG_DATE_FROM_AFTER_TO);
					}
					jlErrorDateFrom.setForeground(Color.RED);
					jxdpDateFromValid = false;
				} else {
					jlErrorDateFrom.setText("");
					jlErrorDateFrom.setForeground(Color.WHITE);
					jxdpDateFromValid = true;
				}
				csvPanel.getButtonPanel().getJbtOk().setEnabled(jxdpDateFromValid && jxdpDateToValid && jtShopnrValid);
			}
		  });

		add(jxdpDateTo,gbc);
		jxdpDateTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jxdpDateFrom.getDate().compareTo(jxdpDateTo.getDate()) == 1 || jxdpDateTo.getDate().compareTo(new Date()) == 1) {
					if(jxdpDateTo.getDate().compareTo(new Date()) == 1) {
						jlErrorDateTo.setText(MSG_DATE_IN_FUTURE);
					} else {
						if(jxdpDateFrom.getDate().compareTo(new Date()) != 1) {
							jlErrorDateFrom.setText(MSG_DATE_FROM_AFTER_TO);
						}
						jlErrorDateTo.setText("");
					}
					jlErrorDateTo.setForeground(Color.RED);
					jxdpDateToValid = false;
				} else {
					jlErrorDateTo.setText("");
					jlErrorDateTo.setForeground(Color.WHITE);	
					jlErrorDateFrom.setText("");
					jlErrorDateFrom.setForeground(Color.WHITE);
					jxdpDateToValid = true;
				}
				csvPanel.getButtonPanel().getJbtOk().setEnabled(jxdpDateFromValid && jxdpDateToValid && jtShopnrValid);
			}
		  });
		gbc.gridy++;

		JLabel jlFilename = new JLabel("Filename");
		add(jlFilename,gbc);
		gbc.gridy++;
		add(jlInvoices,gbc);

		gbc.gridx=2;
		gbc.gridy=0;
		add(jlErrorShopNr,gbc);
		gbc.gridy++;

		add(jlErrorDateFrom,gbc);
		gbc.gridy++;
		add(jlErrorDateTo,gbc);
	}

	public ShopData getShopData() {
		ShopData shopData = new ShopData();
		shopData.setDateFrom(jxdpDateFrom.getDate());
		shopData.setDateTo(jxdpDateTo.getDate());
		shopData.setShopnr(Integer.valueOf(jtShopnr.getText()));
		return shopData;
	}

	public void setJlInvoices(int invoicesSize) {
		jlInvoices.setText(String.valueOf(invoicesSize));
	}
}


