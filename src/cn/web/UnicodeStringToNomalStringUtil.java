package cn.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class UnicodeStringToNomalStringUtil {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		  String path = "D:/1/list.txt";
		  File file = null;
		  BufferedReader br = null;
		  file = new File(path);
		  br = new BufferedReader(new InputStreamReader(
		    new FileInputStream(file), Charset.defaultCharset()));
		  StringBuilder sb = new StringBuilder();
		  String length = "";
		  while ((length = br.readLine()) != null) {
		   sb.append(length);
		  }
		  System.out.println(ascii2Native(sb.toString()));
		  
		 }
		 
		 //unicode转为本地
		 public static String ascii2Native(String str) {   
		        StringBuilder sb = new StringBuilder();   
		        int begin = 0;   
		        int index = str.indexOf("\\u");   
		        while (index != -1) {   
		            sb.append(str.substring(begin, index));   
		            sb.append(ascii2Char(str.substring(index, index + 6)));   
		            begin = index + 6;   
		            index = str.indexOf("\\u", begin);   
		        }   
		        sb.append(str.substring(begin));   
		        return sb.toString();   
		    }  
		 
		 private static char ascii2Char(String str) {   
		        if (str.length() != 6) {   
		            throw new IllegalArgumentException(   
		                    "Ascii string of a native character must be 6 character.");   
		        }   
		        if (!"\\u".equals(str.substring(0, 2))) {   
		            throw new IllegalArgumentException(   
		                    "Ascii string of a native character must start with \"\\u\".");   
		        }   
		        String tmp = str.substring(2, 4);   
		        int code = Integer.parseInt(tmp, 16) << 8;   
		        tmp = str.substring(4, 6);   
		        code += Integer.parseInt(tmp, 16);   
		        return (char) code;   
		    }  
}
