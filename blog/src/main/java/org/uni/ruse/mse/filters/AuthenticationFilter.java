package org.uni.ruse.mse.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.uni.ruse.mse.utils.SessionUtils;

/**
 * Filter which assures there is authenticated user, if not then redirects to
 * login page.
 *
 * @author sinan
 */
@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	// nothing to add
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	HttpServletRequest httpRequest = (HttpServletRequest) request;
	HttpServletResponse httpResponse = (HttpServletResponse) response;

	String requestURI = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
	boolean authenticated = isAuthenticated(httpRequest.getSession());

	if (authenticated && isAuthUri(requestURI)) {
	    redirectToHome(httpRequest, httpResponse);
	    return;
	}
	if (!authenticated && !isAllowedResource(requestURI) && !isAuthUri(requestURI)) {
	    redirectToLogin(httpRequest, httpResponse);
	    return;
	}

	chain.doFilter(request, response);
    }

    private static boolean isAllowedResource(String uri) {
	return uri.startsWith("/javax.faces.resource") || uri.startsWith("/css") || uri.startsWith("/js");
    }

    private static boolean isAuthUri(String uri) {
	return uri.startsWith("/auth");
    }

    private static boolean isAuthenticated(HttpSession session) {
	return SessionUtils.isAuthenticated(session);
    }

    private static void redirectToLogin(HttpServletRequest request, HttpServletResponse response) {
	try {
	    response.sendRedirect(request.getContextPath() + "/auth/login.jsf");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private static void redirectToHome(HttpServletRequest request, HttpServletResponse response) {
	try {
	    response.sendRedirect(request.getContextPath() + "/home.jsf");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void destroy() {
	// nothing to add
    }

}
