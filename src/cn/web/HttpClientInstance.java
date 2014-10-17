package cn.web;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientInstance {
    public final   HttpClient client = new DefaultHttpClient() ;
    private static HttpClientInstance httpClientInstance = new HttpClientInstance();
    private HttpClientInstance(){
        
    } 
    public static HttpClientInstance getInstance(){
            return httpClientInstance;
    }
}
