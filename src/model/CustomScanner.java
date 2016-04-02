package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomScanner {
	
	private String data, outData;
	private String[] dataArr;
	
	public String[] initArr(String data){
		this.data = data;
		this.outData = this.data.replaceAll("(?m)%.*$","");
		dataArr = this.data.split("\n");
		for (int i =0; i<dataArr.length;i++){
//			System.out.println(">>>>> "+dataArr[i]);
		}
		return dataArr;
	}
	
	public String toString(){
		return outData;
	}
}
