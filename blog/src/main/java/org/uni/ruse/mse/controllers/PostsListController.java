package org.uni.ruse.mse.controllers;

import java.io.Serializable;
import java.util.ArrayList;
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
    private static final int MAX_PAGES_TO_SHOW = 5;
    private static final String NAVIGATE_TO_FIRST_PAGE = "posts?page=1&faces-redirect=true";

    @Inject
    private PostService postService;

    private List<Post> posts;

    private String page;

    private int currentPage = -1;

    private int pageCount = -1;

    public String loadPosts() {
	if (page == null) {
	    return NAVIGATE_TO_FIRST_PAGE;
	}

	try {
	    currentPage = Integer.valueOf(page);
	} catch (NumberFormatException e) {
	    return NAVIGATE_TO_FIRST_PAGE;
	}

	if (currentPage < 1) {
	    return NAVIGATE_TO_FIRST_PAGE;
	}

	posts = postService.getPosts(currentPage);
	pageCount = postService.getPageCount();
	return null;
    }

    public List<Integer> getPages() {
	int pages = Math.min(MAX_PAGES_TO_SHOW, pageCount);
	int start = currentPage - pages / 2;

	// compensation when overflows on left
	if (start < 1) {
	    start = 1;
	}

	// compensation when overflows on right
	if ((start + pages) - 1 > pageCount) {
	    start = pageCount - pages + 1;
	}

	List<Integer> result = new ArrayList<>(pages);

	for (int i = start; i < start + pages; i++) {
	    result.add(i);
	}

	return result;
    }

    public boolean isPrevPageLinkEnabled() {
	return currentPage > 1;
    }

    public boolean isNextPageLinkEnabled() {
	return currentPage < pageCount;
    }

    public List<Post> getPosts() {
	return posts;
    }

    public void setPosts(List<Post> posts) {
	this.posts = posts;
    }

    public String getPage() {
	return page;
    }

    public void setPage(String page) {
	this.page = page;
    }

    public int getCurrentPage() {
	return currentPage;
    }

    public void setCurrentPage(int currentPage) {
	this.currentPage = currentPage;
    }

    public int getPageCount() {
	return pageCount;
    }

    public void setPageCount(int pageCount) {
	this.pageCount = pageCount;
    }

}
