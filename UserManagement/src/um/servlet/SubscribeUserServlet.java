package um.servlet;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.http.*;

import um.jdo.JsonUtil;
import um.jdo.UserAuthentication;

/**
 * 
 * This is the servlet for subscribing a user
 * 
 * @author rajatkhanna
 *
 */

@SuppressWarnings("serial")
public class SubscribeUserServlet extends HttpServlet {
	
	/**
	 * Serve a POST request
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String result = null;
		
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			UserAuthentication userAuthentication = UserAuthentication.subscribeUser(email, password);
			
			if (userAuthentication == null)
				throw new RuntimeException("Internal error during subscribe user.");
			
			result = userAuthentication.toExternalJsonString();
			
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("json");
			response.getWriter().println(result);
		} catch (Exception e) {
			//It is important to catch exception and return some message to the client.
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println(JsonUtil.exceptionToJsonString(e));;
		}
		
	}

}
