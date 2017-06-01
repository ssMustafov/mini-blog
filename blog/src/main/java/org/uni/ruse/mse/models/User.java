package org.uni.ruse.mse.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * @author sinan
 */
@Entity
@NamedQueries({ @NamedQuery(name = "getUsers", query = "from User order by username asc"),
	@NamedQuery(name = "getWithCredentials", query = "from User where username = :username and password = :password"),
	@NamedQuery(name = "getByUsername", query = "from User where username = :username") })
public class User implements Serializable {

    private static final long serialVersionUID = 7103692529555087194L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<Post> posts;

    private String username;
    private String password;
    private String fullName;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    public List<Post> getPosts() {
	return posts;
    }

    public void setPosts(List<Post> posts) {
	this.posts = posts;
    }

}
