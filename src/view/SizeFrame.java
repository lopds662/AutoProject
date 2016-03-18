package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SizeFrame extends JFrame{
	
	JLabel lbWidth, lbHeight;
	JTextField fiWidth, fiHeight;
	JButton submitbtt;
	int width,height;
	
	public SizeFrame(final CustomTextArea custom){
		setTitle("Size");
		setLayout(new FlowLayout());
//		setBounds(100, 100, 250, 200);
//		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
		lbWidth = new JLabel("Width: ");
//		lbWidth.setBounds(35, 32, 35, 14);
		
		lbHeight = new JLabel("Height: ");
//		lbHeight.setBounds(35, 68, 46, 14);
		
		fiWidth = new JTextField(10);
//		fiWidth.setBounds(80, 29, 109, 20);
		
		fiHeight = new JTextField(10);
//		fiHeight.setBounds(80, 65, 109, 20);
		
		submitbtt = new JButton("Submit");
		submitbtt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(width+" "+height);
				custom.textArea.insert("size "+fiWidth.getText()+" "+fiHeight.getText()+" [ \n\n\n ]\n", custom.textArea.getCaretPosition());
				dispose();
			}
		});
//		submitbtt.setBounds(69, 105, 89, 23);
		add(lbHeight);
		add(fiHeight);
		add(lbWidth);
		add(fiWidth);
		add(submitbtt);
		pack();
		setVisible(true);

	}
	
	
}
