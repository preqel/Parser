package com.example.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;


/**
 *    
 *一个自定义解析器
 *
 */
public class Parser {
	
	int pos  = 0;
    public static final char EOF = (char) -1;
	private Reader reader ;
	
	public Parser(String parser) {
		this.reader = new StringReader(parser);
	}
	
	public void parse() throws Exception {

		while (true) {
			char ch = this.getChar();
			if (ch == EOF)
				break;
			else if (ch == '<') {

			} else if (ch == '/') {
				this.ungetChar(2);
				this.parseEndTag(pos);
				System.out.println(ch + " //// ");
				break;
			} else {

				this.ungetChar(1);
				this.parseText(pos);
			}
		}
		return;
	}
	
	
	 public void parseText(int start) throws Exception {
	     
	    		  while (true) {
	    	            char ch = this.getChar();
	    	            if (EOF == ch) {
	    	                this.ungetChar();
	    	                char data[] = this.makeString(start, pos);
//	    	                if (data != null) {
//	    	                	System.out.println(new String(data));
//	    	                }
	    	                break;
	    	            } else if ('<' == ch) {
	    	            	//解析到 空 等各类标签后 循环解析到 < 为止， 计算进入时的字符间的内容
	    	                this.ungetChar();
	    	                char data[] = this.makeString(start, pos);
//	    	                if (data != null) {
//	    	                	System.out.println(new String(data));
//					// 	                     
//	    	                }
	    	                break;
	    	            }
	    	        }
	    }
	 
	 
	 
	 /*
	     * 打印一个标签里面的属性
	     * 具体场景可以根据
	     */
	    private void printNode(String text) throws IOException {
	    	Reader reader =new StringReader(text);
	    	char c = (char) reader.read();
	    	String temp="";
	    	boolean first = true;
			while (c != EOF) {
				if (c == ' ') {
					if(first)
						System.out.println(temp);
					else 
						System.out.println("Attribute："+temp);
					first = false;
					temp = "";
				}
				temp += c;
				c = (char) reader.read();
			} 	 
		}
	 
	 
	 
	 
	 public void ungetChar(){
		 try {
			this.ungetChar(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	
	
	public char getChar() throws Exception {
		char ch = (char) -1;
		this.reader.reset();
		if (this.pos > 0) {
			this.reader.skip(pos);
		}
		ch = (char) this.reader.read();
		pos++;
		return ch;
	}
	
	
	 /**
     * 位置后退
     * @param nm
     * @throws Exception
     */
    public void ungetChar(int nm) throws Exception {
        this.reader.reset();
        if ((pos - nm) <= 0) {
            pos = 0;
        } else {
            pos = pos - nm;
        }
    }
	
    
    /**
     * 读取固定长度字符窜组
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public char[] makeString(int start, int end) throws Exception {
        int length = end - start;
        char data[] = new char[length];
        this.reader.reset();
        this.reader.skip(start);
        this.reader.read(data);
        String text = new String(data);
//        
        if (text.trim().equals("")  ) {
            data = null;
        }
        System.out.println("[" + start + "," + end + "]Element: " + new String(data));
        printNode(text);//对于标签里面的属性，再进行解析并且打印
        return data;
         
    }
    
    
    

	/**
     * 结束一个完整标签
     * @param start
     * @throws Exception
     */
    public void parseEndTag(int start) throws Exception {
        StringBuffer temp = new StringBuffer();
        while (true) {
            char ch = this.getChar();
            if ('<' == ch) {
                ch = this.getChar();
                if ('/' != ch) {
                   // throw new ParserException("illegal tag:" + pos);
                }
            } else if (' ' == ch||'/' == ch) {
            	
            }else if ('>' == ch) {
                break;
            } else {
                temp.append(ch);
            }
        }
        //
//        System.out.println(hsml+" )");
    }
    
    
    
}
