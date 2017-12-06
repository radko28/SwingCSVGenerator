package sk.cyklosoft.swingCSVgenerator;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

public class CSVPanel extends JFrame {
	private static String BUTTON_CANCEL="Cancel";
	private static String BUTTON_OK="Start";
	private static String DATE_FORMAT="dd-MM-yyyy";
	private static String MSG_DATE_IN_FUTURE="Date must not be in future";
	private static String MSG_DATE_FROM_AFTER_TO="From-Date must be before To-Date";
	
	 
	
	public static void main( String[] args ) throws ParseException {
    	CSVPanel cp = new CSVPanel("CSV Generator");
   		cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp.setSize(600,300);
		cp.setVisible(true);
    }
	
    public CSVPanel(String title) throws ParseException {
    	super(title);
    	final Container pane = getContentPane();
    	final JPanel inputPanel = new JPanel();
    	JPanel buttonPanel = new JPanel();
    	inputPanel.setLayout(new GridBagLayout());
    	JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
		inputPanel,buttonPanel);                                   
		pane.add(splitPane);
    	final JButton jbtOk = new JButton(BUTTON_OK);
    	jbtOk.setEnabled(false);
		final JLabel jlErrorDateFrom = new JLabel();
		final JLabel jlErrorDateTo = new JLabel();
		
    	
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.anchor=GridBagConstraints.WEST;
		gbc.insets=new Insets(5,10,5,10);
//labels, textfields
		gbc.gridx=0;
		gbc.gridy=0;
		inputPanel.add(new JLabel("Invoice to ShopNr"),gbc);
		gbc.gridy++;
		inputPanel.add(new JLabel("Invoice Date from"),gbc);
		gbc.gridy++;
		inputPanel.add(new JLabel("Invoice Date to"),gbc);
		gbc.gridy++;
		inputPanel.add(new JLabel("Filename"),gbc);
		gbc.gridy++;
		inputPanel.add(new JLabel("Invoices exported"),gbc);

		gbc.gridx=1;
		gbc.gridy=0;
		
		final JTextField jtShopnr = new JTextField();
		jtShopnr.setColumns(10);
		inputPanel.add(jtShopnr,gbc);
		
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
		inputPanel.add(jxdpDateFrom,gbc);
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
				
				jbtOk.setEnabled((jxdpDateFrom.getDate().compareTo(jxdpDateTo.getDate()) != 1)&&(jxdpDateFrom.getDate().compareTo(new Date()) != 1) && (jxdpDateTo.getDate().compareTo(new Date()) != 1));
			}
			
		  });

		inputPanel.add(jxdpDateTo,gbc);
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

				jbtOk.setEnabled((jxdpDateFrom.getDate().compareTo(jxdpDateTo.getDate()) != 1)&&(jxdpDateFrom.getDate().compareTo(new Date()) != 1) && (jxdpDateTo.getDate().compareTo(new Date()) != 1));				
			}
			
		  });
		gbc.gridy++;

		JLabel jlFilename = new JLabel("filename");
		inputPanel.add(jlFilename,gbc);
		gbc.gridy++;
		JLabel jlInvoices = new JLabel("10");
		inputPanel.add(jlInvoices,gbc);
//buttons
		//gbc.gridx=1;
		//gbc.gridy=GridBagConstraints.RELATIVE;
		buttonPanel.add(Buttons(jbtOk),gbc);
		
		gbc.gridx=2;
		gbc.gridy=0;
		inputPanel.add(new JLabel("error"),gbc);
		gbc.gridy++;

		inputPanel.add(jlErrorDateFrom,gbc);
		gbc.gridy++;
		inputPanel.add(jlErrorDateTo,gbc);

		
		
		  jbtOk.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		//jxdpDateFrom.setVisible(false);
			  		//jbtOk.setEnabled(false);
			  	}
		  });			  	
	}
	protected JPanel Buttons(JButton jbtOk) {
		  JPanel panel=new JPanel();
		  panel.setLayout(new FlowLayout());
		  JButton jbtCancel=new JButton(BUTTON_CANCEL);
		  panel.add(jbtOk);
		  panel.add(jbtCancel);
		  return panel;
    }
    
    
/*	protected JPanel Buttons() {
		  JPanel panel=new JPanel();
		  panel.setLayout(new FlowLayout());
		  jbt=new JButton(OK);


		  jbt.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
	//insert into db
				try {
			        	PreparedStatement stmt=(mjdbc.getConnection()).prepareStatement(
					"insert into address set email=?,password=?,firstname=?, surname=?,mobil=?,address=?,city=?,zip=?,web=?"
					);
					stmt.setString(1,email.getText());										stmt.setString(2,password.getText());
					stmt.setString(3,firstname.getText());
					stmt.setString(4,surname.getText());
					stmt.setString(5,mobil.getText());
					stmt.setString(6,address.getText());
					stmt.setString(7,city.getText());
					stmt.setString(8,zip.getText());
					stmt.setString(9,web.getText());
					stmt.executeUpdate();
	                                stateOK = true;
				} catch(Exception exc) {
					System.out.println(exc.getMessage());
	      				exc.printStackTrace();
				}
				jdg.setVisible(false);
				jdg.dispose();
	//refresh table
				mtb.refreshTable("select * from address");

	
		  });

		  panel.add(jbt);
		  jbt=new JButton(CANCEL);
	          jbt.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
	                        stateCANCEL = true;
				jdg.setVisible(false);
				jdg.dispose();
	                }
	          });          
		  panel.add(jbt);
		  return panel;
		}
		public boolean getStateOK() {
		  return stateOK;
		}
		public boolean getStateCANCEL() {
		  return stateCANCEL;
		}

*/
}
