package com.harlau.cmad.buggyblogger.api;
import java.util.Date;
import java.util.List;
/*
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;*/

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.NotSaved;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class Comment {
	//this is comment class.
	private String blogId;
	private String username;
	private String commentContent;
	private Date commentDate;
	@Id
	private String commentId;
	//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@Reference(lazy=true)
	@NotSaved
	private List<Comment> comments;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String uniqueID) {
		this.commentId = uniqueID;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
}