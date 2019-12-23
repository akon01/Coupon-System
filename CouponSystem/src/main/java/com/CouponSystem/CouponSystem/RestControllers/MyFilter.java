package com.CouponSystem.CouponSystem.RestControllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@WebFilter(urlPatterns = "/*")
@Component
public class MyFilter implements Filter {
	/**
	 * A function that will filter each of the requests from the client, first it is
	 * checking if the request is a login or logout request, if not it checks for a
	 * session with an id and connected user type.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String requestUrl = httpRequest.getRequestURI();
		String[] seperatedUrl = requestUrl.split("/");
		String requestType = seperatedUrl[2];
		// check for login or logout request
		switch (seperatedUrl[2]) {
		case "login":
			chain.doFilter(httpRequest, httpResponse);
			break;
		case "logout":
			HttpSession session = httpRequest.getSession(false);
			session.invalidate();
			break;
		// if the request isn't a login or logout check for session
		default:
			// check for session
			if (httpRequest.getSession(false) == null) {
				httpResponse.sendRedirect("localhost:8080/login.html");
				// if a session exists check for user type and id.
			} else {
				String type = httpRequest.getSession().getAttribute("type").toString();
				switch (requestType) {
				case "admin":
					if (type.equalsIgnoreCase(requestType)) {
						chain.doFilter(request, response);
					}
					break;
				default:
					long id = (long) httpRequest.getSession().getAttribute("id");
					if (type.equalsIgnoreCase(requestType) && id != -1) {
						chain.doFilter(request, response);
					}
					break;
				}
			}
			break;
		}
	}
}
