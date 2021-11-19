package com.techmart.utility;

import javax.servlet.http.HttpServletRequest;

public class Utility {
	public static String getURL(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		return url.replace(request.getServletPath(),"");
	}
}
