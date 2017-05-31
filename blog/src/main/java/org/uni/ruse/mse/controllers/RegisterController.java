package org.uni.ruse.mse.controllers;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.uni.ruse.mse.models.User;
import org.uni.ruse.mse.services.UserService;
import org.uni.ruse.mse.utils.FacesMessageUtils;

/**
 * @author sinan
 */
@Named
@ViewScoped
public class RegisterController implements Serializable {

    private static final long serialVersionUID = -4048508140471477794L;

    @Inject
    private UserService userService;

    private User user = new User();
    private String passwordConfirmation;
    private boolean registered;

    public void validateUsername(FacesContext context, UIComponent component, Object value) {
	String username = (String) value;
	if (userService.isUsernameTaken(username)) {
	    throw new ValidatorException(FacesMessageUtils.constructFacesMessage("Username already taken"));
	}
    }

    public void register() {
	if (!user.getPassword().equals(passwordConfirmation)) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    context.addMessage("register-form:password-confirm",
		    FacesMessageUtils.constructFacesMessage("Passwords do not match"));
	    return;
	}

	userService.save(user);
	registered = true;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public String getPasswordConfirmation() {
	return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
	this.passwordConfirmation = passwordConfirmation;
    }

    public boolean isRegistered() {
	return registered;
    }

    public void setRegistered(boolean registered) {
	this.registered = registered;
    }

}
