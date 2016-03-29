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

public class RectFrame extends JFrame{
	
	
	JLabel lbX, lbY, lbName, lbPoint1, lbPoint2;
	JTextField fiX, fiY, fiName, fiPointX, fiPointY;
	JButton submitbtt;
	int x, y, name;
	private JComboBox colorBox;

	public RectFrame(final CustomTextArea custom) {
		setSize(250, 210);
		setTitle("Rectangle");
		setLayout(new GridLayout(6,1));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel1 = new JPanel();
		lbName = new JLabel("Rect Name: ");
		fiName = new JTextField(10);
		panel1.add(lbName);
		panel1.add(fiName);
		
		JPanel panel2 = new JPanel();
		lbPoint1 = new JLabel("at(a,b)");
		fiPointX = new JTextField(4);
		lbPoint2 = new JLabel(",");
		fiPointY = new JTextField(4);
		panel2.add(lbPoint1);
		panel2.add(fiPointX);
		panel2.add(lbPoint2);
		panel2.add(fiPointY);
		
		JPanel panel3 = new JPanel();
		lbX = new JLabel("Rect Width: ");
		fiX = new JTextField(10);
		panel3.add(lbX);
		panel3.add(fiX);
		
		JPanel panel4 = new JPanel();
		lbY = new JLabel("Rect Height: ");
		fiY = new JTextField(10);
		panel4.add(lbY);
		panel4.add(fiY);
		
		JPanel panel5 = new JPanel();
		colorBox = new JComboBox();
		colorBox.setModel(new DefaultComboBoxModel(new String[] {"red", "blue", "green", "yellow", "black"}));
		panel5.add(colorBox);
		
		submitbtt = new JButton("Submit");
		submitbtt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(x + " " + y);
				custom.textArea.insert("rect " + fiName.getText() + " " + fiPointX.getText() + " "
				+ fiPointY.getText() + " " + fiX.getText() + " " + fiY.getText() + " " + colorBox.getSelectedItem() +" ;\n", custom.textArea.getCaretPosition());
				dispose();
			}
		});
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panel5);
		add(submitbtt);
		setVisible(true);
	}

}
