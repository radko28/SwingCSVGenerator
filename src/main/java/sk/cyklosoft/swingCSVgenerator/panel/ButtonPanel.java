package sk.cyklosoft.swingCSVgenerator.panel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import sk.cyklosoft.swingCSVgenerator.CSVPanel;
import sk.cyklosoft.swingCSVgenerator.service.DataService;

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
				csvPanel.getInputPanel().setJlFilename("");
				DataService dataService = new DataService(csvPanel);
				dataService.execute();
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
