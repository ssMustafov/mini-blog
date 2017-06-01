package org.uni.ruse.mse.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author sinan
 */
@Entity
@NamedQueries({ @NamedQuery(name = "getPosts", query = "from Post order by lastModifiedOn desc"),
	@NamedQuery(name = "getByTitle", query = "from Post where title = :title"),
	@NamedQuery(name = "deleteById", query = "delete from Post where id = :id") })
public class Post implements Serializable {

    private static final long serialVersionUID = 1892253977775132195L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedOn;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public User getCreatedBy() {
	return createdBy;
    }

    public void setCreatedBy(User createdBy) {
	this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
	return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
	this.createdOn = createdOn;
    }

    public Date getLastModifiedOn() {
	return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
	this.lastModifiedOn = lastModifiedOn;
    }

}
