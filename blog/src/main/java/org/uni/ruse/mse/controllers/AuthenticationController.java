package org.uni.ruse.mse.controllers;

import java.io.Serializable;
import java.util.Optional;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.uni.ruse.mse.models.User;
import org.uni.ruse.mse.services.UserService;
import org.uni.ruse.mse.utils.FacesMessageUtils;
import org.uni.ruse.mse.utils.SessionUtils;

/**
 * @author sinan
 */
@Named("auth")
@SessionScoped
public class AuthenticationController implements Serializable {

    private static final long serialVersionUID = 1666728907939115205L;

    @Inject
    private UserService userService;

    private String username;
    private String password;

    public String login() {
	Optional<User> user = userService.getByCredentials(username, password);
	if (user.isPresent()) {
	    User authenticated = user.get();
	    SessionUtils.getSession().setAttribute(SessionUtils.AUTHENTICATED_KEY, authenticated);
	    return "/posts?faces-redirect=true";
	}

	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage("login-form:login-btn",
		FacesMessageUtils.constructFacesMessage("Wrong username or password"));
	return null;
    }

    public String logout() {
	SessionUtils.getSession().invalidate();
	return "/auth/login?faces-redirect=true";
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
