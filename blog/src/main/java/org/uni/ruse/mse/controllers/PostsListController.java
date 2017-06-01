package org.uni.ruse.mse.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.uni.ruse.mse.models.Post;
import org.uni.ruse.mse.services.PostService;

/**
 * @author sinan
 */
@Named
@ViewScoped
public class PostsListController implements Serializable {

    private static final long serialVersionUID = -7611285438403441249L;

    @Inject
    private PostService postService;

    private List<Post> posts;

    public void loadPosts() {
	posts = postService.getPosts();
    }

    public List<Post> getPosts() {
	return posts;
    }

    public void setPosts(List<Post> posts) {
	this.posts = posts;
    }

}
