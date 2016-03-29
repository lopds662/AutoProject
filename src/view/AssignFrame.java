package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AssignFrame extends JFrame{
	JLabel lbName, lbValue;
	JTextField fiName, fiValue;
	JButton submitbtt;
	int value;
	String name;
	
	public AssignFrame(final CustomTextArea custom){
		setTitle("Assign");
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		lbName = new JLabel("Name: ");
//		lbWidth.setBounds(35, 32, 35, 14);
		
		lbValue = new JLabel("Value: ");
//		lbHeight.setBounds(35, 68, 46, 14);
		
		fiName = new JTextField(10);
//		fiWidth.setBounds(80, 29, 109, 20);
		
		fiValue = new JTextField(10);
//		fiHeight.setBounds(80, 65, 109, 20);
		
		submitbtt = new JButton("Submit");
		submitbtt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(name+" = "+value);
				custom.textArea.insert(fiName.getText()+" = "+fiValue.getText()+" ;\n", custom.textArea.getCaretPosition());
				dispose();
			}
		});
//		submitbtt.setBounds(69, 105, 89, 23);
		
		add(lbName);
		add(fiName);
		add(lbValue);
		add(fiValue);
		add(submitbtt);
		pack();
		setVisible(true);

	}
}
