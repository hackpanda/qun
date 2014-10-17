package cn.web;

import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class HttpClientUtil {

	private static Log log = LogFactory.getLog(HttpClientUtil.class);
	/**
	 * 以POST方式发送单向 HTTPS请求
	 * @param url
	 * @param paramStr
	 * @param isSelfParam
	 *            是否需要设置参数 true时 params参数值设置到请求中
	 * @param params
	 * @return
	 */
	public static String callHttpsGetUrl(String url) {
		
		// 响应内容
		String returnContent = ""; 
		
		// 创建默认的httpClient实例
		HttpClient httpClient = new DefaultHttpClient();
		
		// 创建TrustManager
		X509TrustManager xtm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
			
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
			
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}
		};
		try {
			SSLContext ctx = SSLContext.getInstance("SSL");
			
			// 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
			ctx.init(null, new TrustManager[] { xtm }, null);
			
			SSLSocketFactory sf = new SSLSocketFactory(
					ctx,
					SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
					Scheme sch = new Scheme("https", 443, sf);
					httpClient.getConnectionManager().getSchemeRegistry().register(sch);
			// 创建HttpPost
			HttpGet httpPost = new HttpGet(url); 
			httpPost.setHeader("content-type", "image/jpeg");
		
			
			// 执行POST请求
			HttpResponse response = httpClient.execute(httpPost); 
			// 获取响应实体
			HttpEntity entity = response.getEntity();
			
//			if (null != entity) {
//				returnContent = EntityUtils.toString(entity, "UTF-8");
//				EntityUtils.consume(entity); // Consume response content
//			}
			byte[] bs = IOUtils.toByteArray(entity.getContent());
			File file  = new File("d:/1.jpeg");
			FileUtils.writeByteArrayToFile(file, bs);
		} catch (Exception e) {
			log.error("HttpClientUtil.callHttpsUrl 出错：" + e.getMessage());
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpClient.getConnectionManager().shutdown(); 
		}
		return returnContent;
	}
	
	
	/**
	 * 
	 * @param url
	 * @param contextType  "image/jpeg","application/Json"
	 * @return
	 */
	public static byte[] sendHttpsGetUrl(HttpClient httpClient1 ,String url,String contextType) {
		
		// 响应内容
		byte[] bs = null;
		
		// 创建默认的httpClient实例
//		HttpClient httpClient = new DefaultHttpClient();
		HttpClient httpClient =		httpClient1;
		
		// 创建TrustManager
		X509TrustManager xtm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
			
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
			
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}
		};
		try {
			SSLContext ctx = SSLContext.getInstance("SSL");
			
			// 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
			ctx.init(null, new TrustManager[] { xtm }, null);
			
			SSLSocketFactory sf = new SSLSocketFactory(
					ctx,
					SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Scheme sch = new Scheme("https", 443, sf);
			httpClient.getConnectionManager().getSchemeRegistry().register(sch);
			// 创建HttpPost
			HttpGet httpPost = new HttpGet(url); 
			httpPost.setHeader("content-type", contextType);
			// 执行POST请求
			HttpResponse response = httpClient.execute(httpPost); 
			// 获取响应实体
			HttpEntity entity = response.getEntity();
			 bs = IOUtils.toByteArray(entity.getContent());
			 if (null != entity) {
					EntityUtils.consume(entity); // Consume response content
				}
			return bs;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
//			httpClient.getConnectionManager().shutdown(); 
		}
		return bs;
	}
	
	public static void main(String[] args) throws IOException {
	//	https://qqgroup.insight-labs.org/qqd3.php?type=1&qstr=418926777&captcha=educand
//		String url ="https://qqgroup.insight-labs.org/captcha.php?"+System.currentTimeMillis();
//		byte[] bs  = HttpClientUtil.callHttpsGetUrl(url,"image/jpeg");
//		File file  = new File("d:/2.jpeg");
//		FileUtils.writeByteArrayToFile(file, bs);
		
//		String test2= "https://qqgroup.insight-labs.org/qqd3.php?type=1&qstr=418926777&captcha=slutch";
//		byte[] bs1  = HttpClientUtil.callHttpsGetUrl(test2,"application/Json");
//		System.out.println(IOUtils.toString(bs1,"UTF-8"));
	    HttpClient client = HttpClientInstance.getInstance().client;
	    System.out.println(client);
		String test2= "https://qqgroup.insight-labs.org";
		byte[] bs1  = HttpClientUtil.sendHttpsGetUrl(client ,test2,"text/html");
		System.out.println(IOUtils.toString(bs1,"UTF-8"));
	}
}
