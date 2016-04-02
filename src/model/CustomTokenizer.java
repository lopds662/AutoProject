package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class CustomTokenizer {
	
	private String[] dataArr;
	private ArrayList<String> alreadyAdd = new ArrayList<String>();
	private JTable symbolTable;
	private JTable outputTable;
	private HashMap<String, String> typeMap = new CustomDriver().getTypeMap();

	public CustomTokenizer(){
		
	}
	
	public CustomTokenizer(String[] data){
		this.dataArr = data;
		for (int i =0; i<dataArr.length;i++){
			System.out.println(dataArr[i]);
		}
	}
	public JTable initOutputTable(){
		outputTable = new JTable();
		DefaultTableModel outTableModel = new DefaultTableModel(0, 0);
		String[] headerOutput = new String[] {"Type", "STR Value", "INT Value"};
		outTableModel.setColumnIdentifiers(headerOutput);
		outputTable.setModel(outTableModel);
		outTableModel.addRow(new Object[] {"Type", "STR Value", "INT Value"});
		outTableModel.addRow(new Object[] {"====","=========","========="});
		for (int i = 0; i < dataArr.length; i++){
			String[] temp = dataArr[i].split("\\+");
			outTableModel.addRow(new Object[] {temp[1], temp[2],temp[3]});
		}
		outputTable.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		return outputTable;
	}
	public JTable initSymbolTable(){
		symbolTable = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel(0, 0);
		String[] header = new String[] {"Token", "Type", "STR Value", "INT Value"};
		tableModel.setColumnIdentifiers(header);
		symbolTable.setModel(tableModel);
		tableModel.addRow(new Object[] {"Token", "Type", "STR Value", "INT Value"});
		tableModel.addRow(new Object[] {"     ","====","=========","========="});
		for (int i = 0; i < dataArr.length; i++){
			String[] temp = dataArr[i].split("\\+");
			if ((!typeMap.containsKey(temp[0])&&(!alreadyAdd.contains(temp[0])))){
				tableModel.addRow(new Object[] {temp[0], temp[1], temp[2],temp[3]});
				alreadyAdd.add(temp[0]);
			}
		}
		symbolTable.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		return symbolTable;
	}

}
