package com.example.parser;

import java.io.Reader;
import java.io.StringReader;


/**
 *    
 *�Զ��������
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
				System.out.println(ch + " ---- ");
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
	    	                if (data != null) {
	    	                	System.out.println(new String(data));
	    	                }
				//
	    	                break;
	    	            } else if ('<' == ch) {
	    	            	//������ �� �ȸ����ǩ�� ѭ�������� < Ϊֹ�� �������ʱ���ַ��������
	    	            	
	    	                this.ungetChar();
	    	                char data[] = this.makeString(start, pos);
	    	                if (data != null) {
	    	                	System.out.println(new String(data));
					// 	                     
	    	                }
	    	                break;
	    	            }
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
     * λ�ú���
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
     * ��ȡ�̶������ַ�����
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
//        if (text.trim().equals("") && this.whiteignore) {
        if (text.trim().equals("")  ) {
            data = null;
        }
        System.out.println("[" + start + "," + end + "]Text: " + new String(data));
        return data;
         
    }
    
    /**
     * ����һ��������ǩ
     * @param start
     * @throws Exception
     */
    public void parseEndTag(int start) throws Exception {
        StringBuffer hsml = new StringBuffer();
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
                hsml.append(ch);
            }
        }
        //
        System.out.println(hsml+" )");
    }
    
    
    
}
