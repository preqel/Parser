package com.example.parser;

import java.io.Reader;
import java.io.StringReader;

/**
 * @author preqel
 *自定义解析器
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
			System.out.print(ch + " ");
		}

		return;
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
	
	
}
