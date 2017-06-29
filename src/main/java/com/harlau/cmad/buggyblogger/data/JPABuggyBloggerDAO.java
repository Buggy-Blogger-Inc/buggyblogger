package com.harlau.cmad.buggyblogger.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.harlau.cmad.buggyblogger.api.*;

public class JPABuggyBloggerDAO implements BuggyBloggerDAO {
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.harlau.cmad.buggyblogger");
/*
	@Override
	public void create(Book book) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(book);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Book read(int isbn) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Book book = em.find(Book.class, isbn);
		em.getTransaction().commit();
		em.close();
		return book;

	}*/

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
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		return user;
	}

	@Override
	public User readUser(String username) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, username);
		em.getTransaction().commit();
		em.close();
		return user;
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
	public List<Blog> readBlogByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
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
