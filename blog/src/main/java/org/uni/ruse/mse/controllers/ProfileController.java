package org.uni.ruse.mse.controllers;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.uni.ruse.mse.models.User;
import org.uni.ruse.mse.services.UserService;
import org.uni.ruse.mse.utils.FacesMessageUtils;
import org.uni.ruse.mse.utils.SessionUtils;

/**
 * @author sinan
 */
@Named("profile")
@ViewScoped
public class ProfileController implements Serializable {

    private static final long serialVersionUID = -6882813546970979756L;

    @Inject
    private UserService userService;

    private User user;
    private boolean success = false;

    public void loadUser() {
	User currentUser = SessionUtils.getAuthenticatedUser();
	user = new User();
	user.setId(currentUser.getId());
	user.setUsername(currentUser.getUsername());
	user.setFullName(currentUser.getFullName());
    }

    public void save() {
	String fullName = user.getFullName().trim();
	String password = user.getPassword().trim();
	User currentUser = SessionUtils.getAuthenticatedUser();
	FacesContext context = FacesContext.getCurrentInstance();

	if (!fullName.equals(currentUser.getFullName())) {
	    if (fullName.length() > 0 && fullName.length() < 2) {
		context.addMessage("profile-form:full-name",
			FacesMessageUtils.constructFacesMessage("Minimal length is 2"));
		return;
	    }
	}
	if (!password.equals(currentUser.getPassword())) {
	    if (password.length() > 0 && password.length() < 6) {
		context.addMessage("profile-form:password",
			FacesMessageUtils.constructFacesMessage("Minimal length is 6"));
		return;
	    }
	}

	User freshUser = userService.getUser(user.getId());
	if (!fullName.isEmpty()) {
	    freshUser.setFullName(fullName);
	    currentUser.setFullName(fullName);
	}
	if (!password.isEmpty()) {
	    freshUser.setPassword(password);
	    currentUser.setPassword(password);
	}
	userService.save(freshUser);
	success = true;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public boolean isSuccess() {
	return success;
    }

    public void setSuccess(boolean success) {
	this.success = success;
    }

}
