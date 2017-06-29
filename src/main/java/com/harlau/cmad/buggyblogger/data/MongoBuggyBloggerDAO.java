package com.harlau.cmad.buggyblogger.data;

import java.util.List;

import com.harlau.cmad.buggyblogger.api.Blog;
import com.harlau.cmad.buggyblogger.api.Comment;
import com.harlau.cmad.buggyblogger.api.User;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

public class MongoBuggyBloggerDAO extends BasicDAO<User, String> implements BuggyBloggerDAO {
	
	public MongoBuggyBloggerDAO(Class<User> entityClass, Datastore ds) {
		super(entityClass, ds);
	}

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
		this.save(user);
		return user;
	}
/*
	public List<Book> findByTitle(String title) {
		Query<Book> query = createQuery().field("title").contains(title).field("isbn").lessThan(1000);
		return query.asList();
	}
*/
	@Override
	public User readUser(String username) {
		// TODO Auto-generated method stub
		Query<User> query = createQuery().field("username").contains(username);
		return query.get();
		//return null;
	}
	
	@Override
	public List<Blog> readBlogByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blog createBlog(Blog blog) {
		// TODO Auto-generated method stub
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
		return null;
	}

	@Override
	public void RemoveComment(User user, Blog blog, Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) {
		this.save(user);
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
	public List<Comment> readComments(String blogTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment readCommentByID(String uniqueID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment writeComment(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blog> fetchBlogsByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

}
