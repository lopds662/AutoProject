package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class loopFrame extends JFrame {

	JLabel lbX, lbY, lbName, lbPoint1, lbPoint2;
	JTextField fiX, fiY, fiName, fiPointX, fiPointY;
	JButton submitbtt;
	int x, y, name;

	public loopFrame(final CustomTextArea custom) {
		setSize(250, 200);
		setTitle("Loop");
		setLayout(new GridLayout(5,1));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel1 = new JPanel();
		lbName = new JLabel("Var Name: ");
		fiName = new JTextField(10);
		panel1.add(lbName);
		panel1.add(fiName);
		
		JPanel panel2 = new JPanel();
		lbPoint1 = new JLabel("Start at: ");
		fiPointX = new JTextField(4);
		panel2.add(lbPoint1);
		panel2.add(fiPointX);
		
		JPanel panel3 = new JPanel();
		lbX = new JLabel("End at: ");
		fiX = new JTextField(10);
		panel3.add(lbX);
		panel3.add(fiX);
		
		JPanel panel4 = new JPanel();
		lbY = new JLabel("Plus: ");
		fiY = new JTextField(10);
		panel4.add(lbY);
		panel4.add(fiY);

		submitbtt = new JButton("Submit");
		submitbtt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println(x + " " + y);
				custom.textArea.insert("for " + fiName.getText() + " = " + fiPointX.getText() + " to "
				+ fiX.getText() + " by " + fiY.getText() + "\ndo\n\t\n\nend ;", custom.textArea.getCaretPosition());
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
