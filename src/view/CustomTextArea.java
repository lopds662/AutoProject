package view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister.Pack;

public class CustomTextArea extends JScrollPane{
	
	public JTextArea textArea = new JTextArea();
	
	public CustomTextArea() {
		final JTextArea lineNo = new JTextArea("1    ");
		lineNo.setBackground(Color.LIGHT_GRAY);
		lineNo.setEditable(false);
//		lineNo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		textArea.getDocument().addDocumentListener(new DocumentListener() {
			public String getText(){
				int caretPosition = textArea.getDocument().getLength();
				Element root = textArea.getDocument().getDefaultRootElement();
				String text = "1    " + "\n";//System.getProperty("line.separator");
				for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
					String space = " ";
					if (i < 10){
						space = "    ";
					}else if (i < 100){
						space = "   ";
					}else if (i < 1000){
						space = "  ";
					}
					text += i + space + "\n";//System.getProperty("line.separator");
				}
				return text;
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				lineNo.setText(getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				lineNo.setText(getText());
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				lineNo.setText(getText());
			}
		});
		getViewport().add(textArea);
		setRowHeaderView(lineNo);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}
}
