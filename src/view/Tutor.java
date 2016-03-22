package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Tutor extends JFrame {

	private JFrame tutorFrame;
	private JTable table;

	public Tutor(boolean status) throws IOException {
		createF(status);
	}

	public void createF(final boolean status) throws IOException {
		tutorFrame = new JFrame();
		tutorFrame.setTitle("Tutor");
		tutorFrame.setBounds(100, 100, 470, 180);
		tutorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tutorFrame.setAlwaysOnTop(true);
		tutorFrame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		tutorFrame.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblShortcutTutorial = new JLabel("Shortcut Tutorial");
		panel.add(lblShortcutTutorial);

		table = new JTable();
		table.setModel(
				new DefaultTableModel(
						new Object[][] { { "Ctrl + s", "save" }, { "Ctrl + Shift + s", "save as" },
								{ "Ctrl + t", "new tab" }, { "Ctrl + o", "open..." }, { "Ctrl + w", "close tab" }, },
						new String[] { "Key", "Action" }) {
					Class[] columnTypes = new Class[] { String.class, Object.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		table.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		tutorFrame.getContentPane().add(table, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		tutorFrame.getContentPane().add(panel_1, BorderLayout.SOUTH);

		final JCheckBox checkBoxDontShow = new JCheckBox("don't show this again");
		checkBoxDontShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					boolean check = checkBoxDontShow.isSelected();
					setShowConfig(check);
					System.out.println("Check");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		checkBoxDontShow.setSelected(status);
		panel_1.add(checkBoxDontShow);
		tutorFrame.setVisible(!status);
		
	}

	public void setShowConfig(boolean status) throws IOException {
		File file = new File("D:/cs333-data/config.txt");
		BufferedWriter bfWriter = new BufferedWriter(new FileWriter(file));
		PrintWriter writer = new PrintWriter(file);
		String data = "Show_tutorial_shortcut," + status;
		data = data.replaceAll("(?!\\r)\\n", "\r\n");
		System.out.println("Checl");
		writer.write(data);
		writer.flush();
		bfWriter.close();
		writer.close();

	}
}
