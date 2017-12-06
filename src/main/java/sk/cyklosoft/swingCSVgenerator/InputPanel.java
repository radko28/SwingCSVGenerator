package sk.cyklosoft.swingCSVgenerator;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

public class InputPanel extends JPanel {
	private static String MSG_DATE_IN_FUTURE="Date must not be in future";
	private static String MSG_DATE_FROM_AFTER_TO="From-Date must be before To-Date";
	private static String MSG_SHOP_NR="0 is not allowed";
	private static String DATE_FORMAT="dd-MM-yyyy";
	final JLabel jlErrorDateFrom = new JLabel();
	final JLabel jlErrorDateTo = new JLabel();
	final JLabel jlErrorShopNr = new JLabel(MSG_SHOP_NR);
	final JTextField jtShopnr = new JTextField();

	
	InputPanel() {
		super();
		setLayout(new GridBagLayout());
    	//final JButton jbtOk = new JButton(BUTTON_OK);
    	//jbtOk.setEnabled(false);

		
    	
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
		
		jtShopnr.addKeyListener(new KeyAdapter() {
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
	
		final JXDatePicker jxdpDateFrom = new JXDatePicker();
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf= new SimpleDateFormat(DATE_FORMAT);
		c.add(Calendar.DATE, -180);
		jxdpDateFrom.setDate(c.getTime());
		jxdpDateFrom.setFormats(sdf);
		add(jxdpDateFrom,gbc);
		gbc.gridy++;
		
		final JXDatePicker jxdpDateTo = new JXDatePicker();
		Calendar cTo = Calendar.getInstance();
		jxdpDateTo.setDate(cTo.getTime());
		jxdpDateTo.setFormats(sdf);
		
		jxdpDateFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jxdpDateFrom.getDate().compareTo(jxdpDateTo.getDate()) == 1) {
					jlErrorDateFrom.setText(MSG_DATE_FROM_AFTER_TO);
					jlErrorDateFrom.setForeground(Color.RED);
					jlErrorDateTo.setText(MSG_DATE_FROM_AFTER_TO);
					jlErrorDateTo.setForeground(Color.RED);					
				} else {
					jlErrorDateFrom.setText("");
					jlErrorDateFrom.setForeground(Color.WHITE);
					jlErrorDateTo.setText("");
					jlErrorDateTo.setForeground(Color.WHITE);
				}

				if(jxdpDateFrom.getDate().compareTo(new Date()) == 1) {
					jlErrorDateFrom.setText(MSG_DATE_IN_FUTURE);
					jlErrorDateFrom.setForeground(Color.RED);
				} else {
					jlErrorDateFrom.setText("");
					jlErrorDateFrom.setForeground(Color.WHITE);		
				}
				
			//	jbtOk.setEnabled((jxdpDateFrom.getDate().compareTo(jxdpDateTo.getDate()) != 1)&&(jxdpDateFrom.getDate().compareTo(new Date()) != 1) && (jxdpDateTo.getDate().compareTo(new Date()) != 1));
			}
			
		  });

		add(jxdpDateTo,gbc);
		jxdpDateTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jxdpDateFrom.getDate().compareTo(jxdpDateTo.getDate()) == 1) {
					jlErrorDateFrom.setText(MSG_DATE_FROM_AFTER_TO);
					jlErrorDateFrom.setForeground(Color.RED);
					jlErrorDateTo.setText(MSG_DATE_FROM_AFTER_TO);
					jlErrorDateTo.setForeground(Color.RED);					
				} else {
					jlErrorDateFrom.setText("");
					jlErrorDateFrom.setForeground(Color.WHITE);
					jlErrorDateTo.setText("");
					jlErrorDateTo.setForeground(Color.WHITE);
				}
				
				if(jxdpDateTo.getDate().compareTo(new Date()) == 1) {
					jlErrorDateTo.setText(MSG_DATE_IN_FUTURE);
					jlErrorDateTo.setForeground(Color.RED);
				} else {
					jlErrorDateTo.setText("");
					jlErrorDateTo.setForeground(Color.WHITE);					
				}

				//jbtOk.setEnabled((jxdpDateFrom.getDate().compareTo(jxdpDateTo.getDate()) != 1)&&(jxdpDateFrom.getDate().compareTo(new Date()) != 1) && (jxdpDateTo.getDate().compareTo(new Date()) != 1));				
			}
			
		  });
		gbc.gridy++;

		JLabel jlFilename = new JLabel("Filename");
		add(jlFilename,gbc);
		gbc.gridy++;
		JLabel jlInvoices = new JLabel();
		add(jlInvoices,gbc);

		gbc.gridx=2;
		gbc.gridy=0;
		add(jlErrorShopNr,gbc);
		gbc.gridy++;

		add(jlErrorDateFrom,gbc);
		gbc.gridy++;
		add(jlErrorDateTo,gbc);

		
		
		  /*jbtOk.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
//check jtShopnr
	  			if(jtShopnr.getText().length() > 0 && jtShopnr.getText().charAt(0) == '0') {
	  				jlErrorShopNr.setForeground(Color.RED);
	  			} else {
	  				jlErrorShopNr.setForeground(Color.BLACK);
	  			}
			  		//jxdpDateFrom.setVisible(false);
			  		//jbtOk.setEnabled(false);
			  	}
		  });*/			  	
	}


	public JLabel getJlErrorDateFrom() {
		return jlErrorDateFrom;
	}


	public JLabel getJlErrorDateTo() {
		return jlErrorDateTo;
	}


	public JLabel getJlErrorShopNr() {
		return jlErrorShopNr;
	}


	public JTextField getJtShopnr() {
		return jtShopnr;
	}

		
	}


