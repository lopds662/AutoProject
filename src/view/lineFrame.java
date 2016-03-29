package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class lineFrame extends JFrame{
	JLabel lbX, lbY, lbName, lbA, lbB, lbC, lbD;
	JTextField fiName, fiPointA, fiPointB, fiPointC, fiPointD;
	JButton submitbtt;
	int x, y, name;
	private JComboBox colorBox;

	public lineFrame(final CustomTextArea custom) {
		setSize(250, 210);
		setTitle("Line");
		setLayout(new GridLayout(5,1));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel1 = new JPanel();
		lbName = new JLabel("Line Name: ");
		fiName = new JTextField(10);
		panel1.add(lbName);
		panel1.add(fiName);
		
		JPanel panel2 = new JPanel();
		lbA = new JLabel("From(a,b)");
		fiPointA = new JTextField(4);
		lbB = new JLabel(",");
		fiPointB = new JTextField(4);
		panel2.add(lbA);
		panel2.add(fiPointA);
		panel2.add(lbB);
		panel2.add(fiPointB);
		
		JPanel panel3 = new JPanel();
		lbC = new JLabel("To(c,d)");
		fiPointC = new JTextField(4);
		lbD = new JLabel(",");
		fiPointD = new JTextField(4);
		panel3.add(lbC);
		panel3.add(fiPointC);
		panel3.add(lbD);
		panel3.add(fiPointD);

		JPanel panel4 = new JPanel();
		colorBox = new JComboBox();
		colorBox.setModel(new DefaultComboBoxModel(new String[] {"red", "blue", "green", "yellow", "black"}));
		panel4.add(colorBox);
		
		submitbtt = new JButton("Submit");
		submitbtt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(x + " " + y);
				custom.textArea.insert("line " + fiName.getText() + " " + fiPointA.getText() + " "
				+ fiPointB.getText() + " " + fiPointC.getText() + " " + fiPointD.getText() + " " +colorBox.getSelectedItem() +" ;\n", custom.textArea.getCaretPosition());
				dispose();
			}
		});
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(submitbtt);
		setVisible(true);
	}
}
