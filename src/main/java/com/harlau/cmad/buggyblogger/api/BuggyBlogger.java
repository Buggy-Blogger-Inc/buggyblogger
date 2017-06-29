package com.harlau.cmad.buggyblogger.api;
import java.util.List;

import com.harlau.cmad.buggyblogger.rest.UserLogin;
public interface BuggyBlogger {
	public List<Blog> searchBlog(String username) throws UserNotFoundException, BuggyBloggerException;
	public Blog viewBlog(String uniqueID) throws BlogNotFoundException, BuggyBloggerException;
	public List<Blog> viewBlogByTitle(String title) throws BlogNotFoundException, BuggyBloggerException;
	public User registerUser(User user) throws DuplicateUserException, InvalidUserException, BuggyBloggerException;
	public User loginUser(String username) throws UserNotFoundException, AuthFailException, BuggyBloggerException;
	public void logoutUser(User user) throws UserNotFoundException, BuggyBloggerException;
	public Blog writeBlog(String username, Blog blog) throws UserNotFoundException, BuggyBloggerException;
	public Blog editBlog(User user, Blog blog) throws UserNotFoundException, BlogNotFoundException, BuggyBloggerException;
	public List<Blog> retrieveBlogs(String username);
	public void removeBlog(User user, Blog blog) throws UserNotFoundException, BlogNotFoundException, BuggyBloggerException;
	public Comment addComment(String username, String title, Comment comment) throws UserNotFoundException, BlogNotFoundException, BuggyBloggerException, CommentNotFoundException;
	public void deleteComment(User user, Blog blog, Comment comment) throws UserNotFoundException, BlogNotFoundException, CommentNotFoundException, BuggyBloggerException;
	/*public List<Comment> viewCommentsForBlog(int blogid) throws BlogNotFoundException, BuggyBloggerException;
	public List<Comment> viewCommentsForComment(int blogid) throws CommentNotFoundException, BuggyBloggerException;*/
	public Blog viewBlogWithTitle(String title);
	public List<Comment> viewComments(String blogTitle);
	public Comment viewCommentById(String uniqueID);
	public Comment writeComment(String title, Comment newComment);
	public User authUser(String username, String password);
	public List<Blog> retrieveBlogsByCategory(String category);
}