package com.training.exam.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProxyServerServlet
 */
@WebServlet("/ProxyServerServlet")
public class ProxyServerServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String targetUrl = request.getParameter("url");
		if(targetUrl==null) {
			targetUrl = "home.jsp";
		}
		targetUrl = "http://localhost:8080/MyApp/" + targetUrl;
		URL url = new URL(targetUrl);
		System.out.println(url);
		HttpURLConnection conn =(HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		System.out.println("Working");
		
		response.setStatus(conn.getResponseCode());
		conn.getHeaderFields().forEach((key,values)->{
			if(key!=null) {
				for(String str: values) {
					response.addHeader(key, str);
				}
			}
		});
		
		
		try(InputStream in = conn.getInputStream();
				OutputStream out = response.getOutputStream();
				) {
			byte[] buffer = new byte[8192];
			int bytesRead;
			while((bytesRead = in.read(buffer))!=-1) {
				out.write(buffer,0, bytesRead);
			}
		} catch(Exception e) {
			e.getMessage();
		}
	}
}
