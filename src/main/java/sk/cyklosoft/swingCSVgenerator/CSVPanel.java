package sk.cyklosoft.swingCSVgenerator;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import sk.cyklosoft.swingCSVgenerator.data.InvoiceData;
import sk.cyklosoft.swingCSVgenerator.dbmock.InvoiceDataMock;
import sk.cyklosoft.swingCSVgenerator.panel.InputPanel;

public class CSVPanel extends JFrame {
	private static String TITLE="CSV Generator";
	private static String BUTTON_CANCEL="Cancel";
	private static String BUTTON_OK="Start";
	
	public static void main( String[] args ) throws ParseException {
    	CSVPanel cp = new CSVPanel(TITLE);
   		cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp.setSize(600,300);
		cp.setVisible(true);
    }
	
    public CSVPanel(String title) throws ParseException {
    	super(title);
    	Container pane = getContentPane();
    	final InputPanel inputPanel = new InputPanel();
    	JPanel buttonPanel = new JPanel();
    	//inputPanel.setLayout(new GridBagLayout());
    	JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
		inputPanel,buttonPanel);                                   
		pane.add(splitPane);
		
		final JButton jbtOk = new JButton(BUTTON_OK);
    	jbtOk.setEnabled(true);
		buttonPanel.add(Buttons(jbtOk));
		
		  jbtOk.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
	  			if((inputPanel).getJtShopnr().getText().length() > 0 && inputPanel.getJtShopnr().getText().charAt(0) == '0') {
	  				inputPanel.getJlErrorShopNr().setForeground(Color.RED);
	  			} else {
	  				inputPanel.getJlErrorShopNr().setForeground(Color.BLACK);
	  				
	  				//search values in database
	  				InvoiceDataMock invoiceDataMock  = new InvoiceDataMock();
	  				List<InvoiceData> invoiceData = invoiceDataMock.getInvoiceDataList(inputPanel.getShopData());
	  				//progress status
	  				//save to csv invoiceData
	  				//view incount count invoiceData.size()
	  				inputPanel.setJlInvoices(invoiceData.size());
	  			}
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
		  jbtCancel.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		System.exit(0);
                }
          });          
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
