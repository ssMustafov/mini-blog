package org.uni.ruse.mse.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.uni.ruse.mse.models.Post;

/**
 * @author sinan
 */
@Stateless
public class PostService {

    private static final int PAGE_SIZE = 5;

    @PersistenceContext
    private EntityManager entityManager;

    public Post save(Post post) {
	post.setContent(post.getContent().trim());
	post.setTitle(post.getTitle().trim());
	return entityManager.merge(post);
    }

    public Post getPost(Long id) {
	return entityManager.find(Post.class, id);
    }

    public List<Post> getPosts() {
	TypedQuery<Post> query = entityManager.createNamedQuery("getPosts", Post.class);
	return query.getResultList();
    }

    public List<Post> getPosts(int page) {
	TypedQuery<Post> query = entityManager.createNamedQuery("getPosts", Post.class);
	query.setFirstResult((page - 1) * PAGE_SIZE);
	query.setMaxResults(PAGE_SIZE);
	return query.getResultList();
    }

    public int getPageCount() {
	TypedQuery<Long> query = entityManager.createNamedQuery("count", Long.class);
	long count = query.getSingleResult();

	int pages = (int) count / PAGE_SIZE;

	if (count % PAGE_SIZE > 0) {
	    pages++;
	}

	return pages;
    }

    public boolean isTitleTaken(Long id, String title) {
	TypedQuery<Post> query = entityManager.createNamedQuery("getByTitle", Post.class);
	query.setParameter("title", title);

	List<Post> resultList = query.getResultList();
	return !resultList.isEmpty() && resultList.get(0).getId() != id;
    }

    public void delete(Long id) {
	entityManager.remove(getPost(id));
    }

}
