package sk.cyklosoft.swingCSVgenerator;

import java.awt.Container;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import sk.cyklosoft.swingCSVgenerator.panel.ButtonPanel;
import sk.cyklosoft.swingCSVgenerator.panel.InputPanel;

public class CSVPanel extends JFrame {
	private static String TITLE="Invoice Image PIN";
	public static String DATE_FORMAT="dd-MM-yyyy";
	private InputPanel inputPanel;
	private ButtonPanel buttonPanel;
	
	public static void main( String[] args ) throws ParseException {
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	CSVPanel cp;
					try {
						cp = new CSVPanel(TITLE);
		           		cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        		cp.setSize(700,300);
		        		cp.setLocationRelativeTo(null);
		        		cp.setVisible(true);
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
	            }
		 });
    }
	
    public CSVPanel(String title) throws ParseException {
    	super(title);
    	Container pane = getContentPane();
    	buttonPanel = new ButtonPanel(this);
    	inputPanel = new InputPanel(this);
    	
    	JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
		inputPanel,buttonPanel);                                   
		pane.add(splitPane);
	}

	public InputPanel getInputPanel() {
		return inputPanel;
	}

	public void setInputPanel(InputPanel inputPanel) {
		this.inputPanel = inputPanel;
	}

	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(ButtonPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}
}
