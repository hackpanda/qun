package cn.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.HttpClient;

/**
 * Servlet implementation class ImgServlet
 */
@WebServlet("/servlet/ImgServlet")
public class ImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("image/jpeg");
	        String test2= "https://qqgroup.insight-labs.org/captcha.php?"+System.currentTimeMillis();
	        HttpClient client = HttpClientInstance.getInstance().client;
	        System.out.println(client);
	        byte[] bs1  = HttpClientUtil.sendHttpsGetUrl(client,test2,"image/jpeg");
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
