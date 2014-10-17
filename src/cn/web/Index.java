package cn.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;

/**
 * Servlet implementation class Index
 */
@WebServlet("/servlet/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html;charset=utf-8");
	        String test2= "https://qqgroup.insight-labs.org/";
	        HttpClient client = HttpClientInstance.getInstance().client;
	        System.out.println(client);
	        byte[] bs1  = HttpClientUtil.sendHttpsGetUrl(client,test2,"text/html;charset=utf-8");
	        String testStr = IOUtils.toString(bs1, "utf-8"); 
	       // System.out.println(testStr);
	        response.getWriter().append("你大爷。。。。");
	        request.getRequestDispatcher ("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
