package cn.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

    	private  String unicode2String(String unicodeStr){  
    		    StringBuffer sb = new StringBuffer();  
    		    String str[] = unicodeStr.toUpperCase().split("U");  
    		    for(int i=0;i<str.length;i++){  
    		      if(str[i].equals("")) continue;  
    		      char c = (char)Integer.parseInt(str[i].trim(),16);  
    		      sb.append(c);  
    		    }  
    		    return sb.toString();  
    		  }  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/Json;charset=utf8");
		byte[] bs1 = IOUtils.toByteArray(new FileInputStream(new File("D:/1/list.txt")));
		String str = new String(bs1);
		String strNew  = unicode2String(str);
		response.getOutputStream().write(bs1);
		
        response.getOutputStream().flush();
        response.getOutputStream().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
