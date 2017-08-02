package com.example.parser;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

	public static void main(String[] args) {
		
		
		
		
		String filePath ="";
	 
	               

		String xml = "<xxml></xxml>";
		
		 
		while(true){
			char[] chars =   xml.toCharArray();
		
		}
		
//		System.out.println("hello world");
	}
	
	public static String getValueFromFile(String filePath, String key) throws IOException {
		Properties pps = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		pps.load(in);
		String value = pps.getProperty(key);
		System.out.println(key + " = " + value);
		
		
	}
	

}
