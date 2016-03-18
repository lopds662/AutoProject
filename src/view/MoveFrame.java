package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MoveFrame extends JFrame {
	
	
	JLabel lbX, lbY, lbName;
	JTextField fiX, fiY, fiName;
	JButton submitbtt;
	int x, y, name;

	public MoveFrame(final CustomTextArea custom) {
		setSize(200, 200);
		setTitle("Move");
		setLayout(new GridLayout(4,1));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel1 = new JPanel();
		lbName = new JLabel("Name: ");
		fiName = new JTextField(10);
		panel1.add(lbName);
		panel1.add(fiName);
		
		JPanel panel2 = new JPanel();
		lbX = new JLabel("Width: ");
		fiX = new JTextField(10);
		panel2.add(lbX);
		panel2.add(fiX);
		
		JPanel panel3 = new JPanel();
		lbY = new JLabel("Height: ");
		fiY = new JTextField(10);
		panel3.add(lbY);
		panel3.add(fiY);

		submitbtt = new JButton("Submit");
		submitbtt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(x + " " + y);
				custom.textArea.insert("move " + fiName.getText() + " " + fiX.getText() + " " + fiY.getText() + " ;\n", custom.textArea.getCaretPosition());
				dispose();
			}
		});
		add(panel1);
		add(panel2);
		add(panel3);
		add(submitbtt);
		setVisible(true);
	}

}
