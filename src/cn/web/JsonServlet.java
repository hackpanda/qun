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
 * Servlet implementation class JsonServlet
 */
@WebServlet("/servlet/JsonServlet")
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JsonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  response.setContentType("application/Json");
	        String type = request.getParameter("type");
	        String qstr = request.getParameter("qstr");
	        HttpClient client = HttpClientInstance.getInstance().client;
	        System.out.println(client);
	        String captcha = request.getParameter("captcha");
	        String test2= "https://qqgroup.insight-labs.org/qqd3.php?type="+type+"&qstr="+qstr+"&captcha="+captcha;
	        byte[] bs1  = HttpClientUtil.sendHttpsGetUrl(client,test2,"application/Json");
	        System.out.println(IOUtils.toString(bs1, "utf-8"));
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
