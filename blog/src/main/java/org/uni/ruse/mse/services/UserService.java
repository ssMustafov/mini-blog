package org.uni.ruse.mse.services;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.uni.ruse.mse.models.User;

/**
 * @author sinan
 */
@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public User save(User user) {
	return entityManager.merge(user);
    }

    public User getUser(Long id) {
	return entityManager.find(User.class, id);
    }

    public boolean isUsernameTaken(String username) {
	TypedQuery<User> query = entityManager.createNamedQuery("getByUsername", User.class);
	query.setParameter("username", username);

	return !query.getResultList().isEmpty();
    }

    public Optional<User> getByCredentials(String username, String password) {
	TypedQuery<User> query = entityManager.createNamedQuery("getWithCredentials", User.class);
	query.setParameter("username", username);
	query.setParameter("password", password);

	List<User> foundUsers = query.getResultList();
	if (foundUsers.size() != 1) {
	    return Optional.empty();
	}
	return Optional.of(foundUsers.get(0));
    }

}
