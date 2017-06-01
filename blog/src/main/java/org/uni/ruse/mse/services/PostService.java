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
