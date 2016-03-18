package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.CustomTextArea;

public class FileHandler {
	
	private File file ;
	private BufferedReader reader;
	private File dir;
	
	public FileHandler(){
	}
	
	public File openFile(JFrame frame){
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}		
		return file;
	}
	public String readFile(File file) throws IOException{
		String temp = "";
		String filePath = file.getPath();
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
		for (String i = reader.readLine(); i != null; i = reader.readLine()) {
			temp += i + "\n";
		}
		return temp;
	}
	
	public String save(String fileName, CustomTextArea custom, JFrame frame){		
		dir = new File("D:/cs333-data");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		file = new File("D:/cs333-data/"+fileName);
		if (file.exists() && file.canWrite()){
			try {
				BufferedWriter bfWriter = new BufferedWriter(new FileWriter(file));
				PrintWriter writer = new PrintWriter(file);
				String data = custom.textArea.getText();
				data = data.replaceAll("(?!\\r)\\n", "\r\n");
				writer.write(data);
				writer.flush();
				bfWriter.close();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			fileName = saveAs(custom, frame);
		}
		return fileName;
	}
	
	public String saveAs(CustomTextArea custom, JFrame frame){
		JFileChooser saver = new JFileChooser();
		dir = new File("D:/cs333-data");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		saver.setCurrentDirectory(dir);
		saver.setFileFilter(new FileNameExtensionFilter("kids files", "kids"));
		saver.setApproveButtonText("Save");
		saver.showSaveDialog(frame);
		file = new File(saver.getSelectedFile() + ".kids");
		String data = custom.textArea.getText();
		data = data.replaceAll("(?!\\r)\\n", "\r\n");
		try {
			BufferedWriter bfWriter = new BufferedWriter(new FileWriter(file));
			PrintWriter writer = new PrintWriter(file);
			writer.write(data);
			bfWriter.close();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return file.getName();
	}

}
