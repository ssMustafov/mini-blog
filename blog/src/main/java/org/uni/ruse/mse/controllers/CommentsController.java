package org.uni.ruse.mse.controllers;

import java.io.Serializable;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.uni.ruse.mse.models.Comment;
import org.uni.ruse.mse.models.Post;
import org.uni.ruse.mse.services.CommentService;
import org.uni.ruse.mse.services.PostService;
import org.uni.ruse.mse.utils.SessionUtils;

/**
 * @author sinan
 */
@Named
@ViewScoped
public class CommentsController implements Serializable {

    private static final long serialVersionUID = -3277557570331088268L;

    @Inject
    private CommentService commentService;

    @Inject
    private PostService postService;

    private Comment comment = new Comment();

    public String addComment() {
	Post post = getPost();
	comment.setCommentedBy(SessionUtils.getAuthenticatedUser());
	comment.setCreatedOn(new Date());
	comment.setPost(post);
	commentService.save(comment);

	comment = new Comment();
	return "post?id=" + post.getId() + "&faces-redirect=true";
    }

    public String deleteComment(Long id) {
	commentService.delete(id);
	return "post?id=" + getPostId() + "&faces-redirect=true";
    }

    private Post getPost() {
	return postService.getPost(getPostId());
    }

    private static Long getPostId() {
	FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes()
		.get(FaceletContext.FACELET_CONTEXT_KEY);
	return (Long) faceletContext.getAttribute("postId");
    }

    public Comment getComment() {
	return comment;
    }

    public void setComment(Comment comment) {
	this.comment = comment;
    }

}
