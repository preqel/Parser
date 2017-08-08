package com.example.parser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

	

	public static void main(String[] args) throws IOException {
		File file = new File("e:\\test.txt");	   //请将要解析的文本放到e盘下面，并重名为test.txt
		InputStream in = new FileInputStream(file);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length = -1;
		while ((length = in.read(buffer)) != -1) {
			bos.write(buffer, 0, length);
		}
		bos.close();
		in.close();
		String result = bos.toString();
		Parser parser = new Parser(result);
		try {
			parser.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
 
	

}
