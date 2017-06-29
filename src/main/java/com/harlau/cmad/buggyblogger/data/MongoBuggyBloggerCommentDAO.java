package com.harlau.cmad.buggyblogger.data;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.harlau.cmad.buggyblogger.api.Blog;
import com.harlau.cmad.buggyblogger.api.Comment;
import com.harlau.cmad.buggyblogger.api.User;

public class MongoBuggyBloggerCommentDAO extends BasicDAO<Comment, String> implements BuggyBloggerDAO {

	public MongoBuggyBloggerCommentDAO(Class<Comment> entityClass, Datastore ds) {
		super(entityClass, ds);
	}
	@Override
	public List<Blog> readBlogByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	public List<Book> findByTitle(String title) {
		Query<Book> query = createQuery().field("title").contains(title).field("isbn").lessThan(1000);
		return query.asList();
	}
*/
	@Override
	public List<Blog> fetchBlogs(String username) {
	// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Blog readBlog(int blogid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User readUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Blog createBlog(Blog blog) {
		//this.save(blog);
		return null;
	}
	@Override
	public Blog updateBlog(User user, Blog blog) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteBlog(User user, Blog blog) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Comment CreateComment(User user, Blog blog, Comment comment) {
		// TODO Auto-generated method stub
		this.save(comment);
		return comment;
	}
	
	@Override
	public Comment writeComment(Comment comment) {
		// TODO Auto-generated method stub
		this.save(comment);
		return comment;
	}
	
	@Override
	public void RemoveComment(User user, Blog blog, Comment comment) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Blog readBlogById(String blogId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Blog readBlogWithTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Comment> readComments(String blogId) {
		// TODO Auto-generated method stub
		Query<Comment> query = createQuery().field("blogId").contains(blogId);
		return query.asList();
	}
	/*
	@Override
	public Blog readBlogById(String blogId) {
		Query<Blog> query = createQuery().field("blogId").contains(blogId);
		// TODO Auto-generated method stub
		return query.get();
	}*/
	@Override
	public Comment readCommentByID(String commentId) {
		Query<Comment> query = createQuery().field("commentId").contains(commentId);
		// TODO Auto-generated method stub
		return query.get();
	}
	@Override
	public List<Blog> fetchBlogsByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}
}
