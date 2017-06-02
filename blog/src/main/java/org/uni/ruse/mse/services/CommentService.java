package org.uni.ruse.mse.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.uni.ruse.mse.models.Comment;

/**
 * @author sinan
 */
@Stateless
public class CommentService {

    @PersistenceContext
    private EntityManager entityManager;

    public Comment save(Comment comment) {
	comment.setContent(comment.getContent().trim());
	return entityManager.merge(comment);
    }

    public Comment getComment(Long id) {
	return entityManager.find(Comment.class, id);
    }

    public void delete(Long id) {
	Query query = entityManager.createNamedQuery("deleteComment");
	query.setParameter("id", id);
	query.executeUpdate();
    }

}
