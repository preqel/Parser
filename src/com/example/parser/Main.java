package com.example.parser;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("e:\\a.txt");
		 InputStream in = new FileInputStream(file);
		 ByteArrayOutputStream bos = new ByteArrayOutputStream();  
		 byte[] buffer=new byte[1024];  
		 int length=-1;  
		 while( (length = in.read(buffer)) != -1)  {  
				               bos.write(buffer,0,length);  
				               // .write���� SDK �Ľ����� Writes count bytes from the byte array buffer starting at offset index to this stream.  
			               //  �����ر��Ժ�������Ȼ����  
			             }  
				               bos.close();  
				  
				               in.close();  
				              String result =  bos.toString();
				              System.out.println(result); 

	}
	
 
	

}
