package sk.cyklosoft.swingCSVgenerator;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CSVPanel extends JFrame {
	private static String BUTTON_CANCEL="Cancel";
	private static String BUTTON_OK="Start";
	
	public static void main( String[] args ) {
    	CSVPanel cp = new CSVPanel("CSV Generator");
   		cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp.setSize(800,600);
		cp.setVisible(true);
    }
	
    public CSVPanel(String title) {
    	super(title);
    	getContentPane().setLayout(new GridBagLayout());
    	
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.anchor=GridBagConstraints.WEST;
		gbc.insets=new Insets(5,10,5,10);
//labels, textfields
		gbc.gridx=0;
		gbc.gridy=0;
		getContentPane().add(new JLabel("Invoice to ShopNr"),gbc);
		gbc.gridy++;
		getContentPane().add(new JLabel("Invoice Date from"),gbc);
		gbc.gridy++;
		getContentPane().add(new JLabel("Invoice Date to"),gbc);
		gbc.gridy++;
		getContentPane().add(new JLabel("Filename"),gbc);
		gbc.gridy++;
/*		
		getContentPane().add(new JLabel("surname"),gbc);
		gbc.gridy++;
		getContentPane().add(new JLabel("mobil"),gbc);
		gbc.gridy++;
		getContentPane().add(new JLabel("address"),gbc);
		gbc.gridy++;
		getContentPane().add(new JLabel("city"),gbc);
		gbc.gridy++;
		getContentPane().add(new JLabel("zip"),gbc);
		gbc.gridy++;
		getContentPane().add(new JLabel("web"),gbc);
*/
		gbc.gridx=1;
		gbc.gridy=0;
		JTextField email=new JTextField(10);
		getContentPane().add(email,gbc);
		gbc.gridy++;
		JTextField password=new JTextField(10);
		getContentPane().add(password,gbc);
		gbc.gridy++;
		JTextField vpassword=new JTextField(10);
		getContentPane().add(vpassword,gbc);
		gbc.gridy++;
		
		JTextField firstname=new JTextField(10);
		getContentPane().add(firstname,gbc);
		gbc.gridy++;
/*		
		surname=new JTextField(10);
		jdg.getContentPane().add(surname,gbc);
		gbc.gridy++;
		mobil=new JTextField(10);
		jdg.getContentPane().add(mobil,gbc);
		gbc.gridy++;
		address=new JTextField(10);
		jdg.getContentPane().add(address,gbc);
		gbc.gridy++;
		city=new JTextField(10);
		getContentPane().add(city,gbc);
		gbc.gridy++;
		zip=new JTextField(10);
		jdg.getContentPane().add(zip,gbc);
		gbc.gridy++;
		web=new JTextField(10);
		jdg.getContentPane().add(web,gbc);
*/
//buttons
		gbc.gridx=1;
		gbc.gridy=GridBagConstraints.RELATIVE;
		getContentPane().add(Buttons(),gbc);

	//	setLocationRelativeTo(f);
	}
    protected JPanel Buttons() {
		  JPanel panel=new JPanel();
		  panel.setLayout(new FlowLayout());
		  JButton jbt=new JButton(BUTTON_OK);
		  JButton jbtCancel=new JButton(BUTTON_CANCEL);
		  panel.add(jbt);
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
