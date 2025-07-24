package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sun.net.httpserver.HttpExchange;

public interface FileHandler {
	/*Just a function to read any file text data and return as a string*/
	static String inputStreamIntoString(HttpExchange e) throws IOException {
		/* this object receives data in bytes and encode in utf-8 chars */
		InputStreamReader input = new InputStreamReader(e.getRequestBody(), "utf-8");
		BufferedReader buffReader = new BufferedReader(input);
		StringBuilder buffer = new StringBuilder(1024);
			
		int code = buffReader.read();

		while(code != -1) {
			buffer.append((char) code);
			code = buffReader.read();
		}

		buffReader.close();
		input.close();

		return  buffer.toString();
	}
}
