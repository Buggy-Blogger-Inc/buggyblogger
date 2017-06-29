package com.harlau.cmad.buggyblogger.service;

import java.util.List;

import com.harlau.cmad.buggyblogger.api.*;
import com.harlau.cmad.buggyblogger.data.JPABuggyBloggerDAO;
import com.harlau.cmad.buggyblogger.data.MongoBuggyBloggerBlogDAO;
import com.harlau.cmad.buggyblogger.data.MongoBuggyBloggerCommentDAO;
import com.harlau.cmad.buggyblogger.data.MongoBuggyBloggerDAO;
import com.mongodb.MongoClient;
import com.harlau.cmad.buggyblogger.data.BuggyBloggerDAO;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class HarlauBuggyBlogger implements BuggyBlogger {
/*	private LibraryDAO dao = new JPALibraryDAO();

	@Override
	public void add(Book book) throws InvalidBookException, DuplicateBookException, LibraryException {
		if (book == null)
			throw new InvalidBookException();
		if (dao.read(book.getIsbn()) != null)
			throw new DuplicateBookException();
		dao.create(book);
	}

	@Override
	public Book find(int isbn) throws BookNotFoundException, LibraryException {
		Book book = dao.read(isbn);
		if (book == null)
			throw new BookNotFoundException();
		return book;
	}*/
	//private BuggyBloggerDAO dao = new JPABuggyBloggerDAO();
	//MongoClient mongoClient = new MongoClient("10.128.0.5:27017");
	MongoClient mongoClient = new MongoClient("localhost:27017");
	Morphia morphia = new Morphia();
	String databaseName = "buggyblogger";
	Datastore datastore = morphia.createDatastore(mongoClient, databaseName);
	private BuggyBloggerDAO dao = new MongoBuggyBloggerDAO(User.class,datastore);
	private BuggyBloggerDAO blogDao = new MongoBuggyBloggerBlogDAO(Blog.class,datastore);
	private BuggyBloggerDAO commentDao = new MongoBuggyBloggerCommentDAO(Comment.class,datastore);
	@Override
	public List<Blog> searchBlog(String username) throws UserNotFoundException, BuggyBloggerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blog viewBlog(String blogId) throws BlogNotFoundException, BuggyBloggerException {
		// TODO Auto-generated method stub
		Blog blog = blogDao.readBlogById(blogId);
		if(blog==null)
			throw new BlogNotFoundException();		
		return blog;
	}

	@Override
	public User registerUser(User user) throws DuplicateUserException, InvalidUserException, BuggyBloggerException {
		
		if (user == null)
			throw new InvalidUserException();
		if (dao.readUser(user.getUsername()) != null)
			throw new DuplicateUserException();
		dao.createUser(user);
		
		return user;
	}

	@Override
	public User loginUser(String username) throws UserNotFoundException, AuthFailException, BuggyBloggerException {
		User user = dao.readUser(username);
		if (user == null)
			throw new UserNotFoundException();
		return user;
		
	}
	
	@Override
	public User authUser(String username, String password) throws UserNotFoundException, AuthFailException, BuggyBloggerException {
		// TODO Auto-generated method stub
		User user = dao.readUser(username);
		if (user == null)
			throw new UserNotFoundException();
		if (!password.equals(user.getPassword()))
			throw new AuthFailException();
		//generate token here ?
		return user;
		
	}

	@Override
	public void logoutUser(User user) throws UserNotFoundException, BuggyBloggerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comment addComment(String username, String title, Comment comment)
			throws UserNotFoundException, BlogNotFoundException, BuggyBloggerException, CommentNotFoundException {
		
		/*User user = dao.readUser(username);
		if (user == null)
			throw new UserNotFoundException();
		Blog blog = blogDao.readBlogWithTitle(title);
		if(blog==null)
			throw new BlogNotFoundException();
		List<Comment> comments = blog.getComments();
		comments.add(comment);
		blog.setComments(comments);
		//blogDao.updateBlog(user, blog); // updateBlog(username, blog)
		//commentDao.CreateComment(comment);
		// TODO Auto-generated method stub*/
		return null;
	}
	@Override
	public Comment writeComment(String blogId, Comment newComment)  throws BlogNotFoundException, BuggyBloggerException {
		// TODO Auto-generated method stub
		Blog blog = blogDao.readBlogById(blogId);
		if(blog==null)
			throw new BlogNotFoundException();
		commentDao.writeComment(newComment);
		return newComment;
		
	}
	
	@Override
	public Blog writeBlog(String username, Blog blog) throws UserNotFoundException, BuggyBloggerException {
		User user = dao.readUser(username);
		if (user == null)
			throw new UserNotFoundException();
		List<Blog> blogs = user.getBlogs();
		blogs.add(blog);
		user.setBlogs(blogs);
		dao.updateUser(user);
		blogDao.createBlog(blog);
		// TODO Auto-generated method stub
		return blog;
	}
	@Override
	public List<Comment> viewComments(String blogId) throws BlogNotFoundException, BuggyBloggerException {
		// TODO Auto-generated method stub
		Blog blog = blogDao.readBlogById(blogId);
		if(blog==null)
			throw new BlogNotFoundException();
		List<Comment> comment = commentDao.readComments(blogId);
		return comment;
	}
	@Override
	public Comment viewCommentById(String uniqueID) {
		Comment comment = commentDao.readCommentByID(uniqueID);
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Blog viewBlogWithTitle(String title) throws BlogNotFoundException, BuggyBloggerException {
		Blog blog = blogDao.readBlogWithTitle(title);
		if(blog==null)
			throw new BlogNotFoundException();		
		// TODO Auto-generated method stub
		return blog;
	}

	@Override
	public List<Blog> viewBlogByTitle(String title) throws BlogNotFoundException, BuggyBloggerException {
		List<Blog> blogs = blogDao.readBlogByTitle(title);
		
		return blogs;
	}

	@Override
	public List<Blog> retrieveBlogs(String username) {
		List<Blog> blogs = blogDao.fetchBlogs(username);
		return blogs;
	}
	
	@Override
	public List<Blog> retrieveBlogsByCategory(String category) {
		List<Blog> blogs = blogDao.fetchBlogsByCategory(category);
		return blogs;
	}


	@Override
	public Blog editBlog(User user, Blog blog)
			throws UserNotFoundException, BlogNotFoundException, BuggyBloggerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeBlog(User user, Blog blog)
			throws UserNotFoundException, BlogNotFoundException, BuggyBloggerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment(User user, Blog blog, Comment comment)
			throws UserNotFoundException, BlogNotFoundException, CommentNotFoundException, BuggyBloggerException {
		// TODO Auto-generated method stub
		
	}
}
