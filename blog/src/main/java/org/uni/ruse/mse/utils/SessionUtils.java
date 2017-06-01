package org.uni.ruse.mse.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.uni.ruse.mse.exceptions.BlogSecurityException;
import org.uni.ruse.mse.models.User;

/**
 * @author sinan
 */
public class SessionUtils {

    public static final String AUTHENTICATED_KEY = "authenticated";

    public static HttpSession getSession() {
	return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static boolean isAuthenticated() {
	return getSession().getAttribute(AUTHENTICATED_KEY) != null;
    }

    public static boolean isAuthenticated(HttpSession session) {
	if (session != null) {
	    return session.getAttribute(AUTHENTICATED_KEY) != null;
	}
	return getSession().getAttribute(AUTHENTICATED_KEY) != null;
    }

    public static User getAuthenticatedUser() {
	Object authenticated = getSession().getAttribute(AUTHENTICATED_KEY);
	if (authenticated instanceof User) {
	    return (User) authenticated;
	}
	throw new BlogSecurityException("Missing authenticated user session");
    }

}
