package org.uni.ruse.mse.controllers;

import java.io.Serializable;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.uni.ruse.mse.models.Post;
import org.uni.ruse.mse.models.User;
import org.uni.ruse.mse.services.PostService;
import org.uni.ruse.mse.utils.FacesMessageUtils;
import org.uni.ruse.mse.utils.SessionUtils;

/**
 * @author sinan
 */
@Named
@ViewScoped
public class PostCreateController implements Serializable {

    private static final long serialVersionUID = -3277557570331088268L;

    @Inject
    private PostService postService;

    private Post post = new Post();

    public String create() {
	User user = SessionUtils.getAuthenticatedUser();
	Date currentDate = new Date();
	post.setCreatedBy(user);
	post.setCreatedOn(currentDate);
	post.setLastModifiedOn(currentDate);
	Post createdPost = postService.save(post);

	return "post?id=" + createdPost.getId() + "&faces-redirect=true";
    }

    public void validateTitle(FacesContext context, UIComponent component, Object value) {
	String title = (String) value;
	if (postService.isTitleTaken(post.getId(), title)) {
	    throw new ValidatorException(FacesMessageUtils.constructFacesMessage("Title already taken"));
	}
    }

    public Post getPost() {
	return post;
    }

    public void setPost(Post post) {
	this.post = post;
    }

}
