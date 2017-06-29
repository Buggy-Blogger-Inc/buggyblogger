package com.harlau.cmad.buggyblogger.data;

import java.util.List;

import com.harlau.cmad.buggyblogger.api.*;

public interface BuggyBloggerDAO {
	public List<Blog> fetchBlogs(String username);
	public Blog readBlog(int blogid);
	public User createUser(User user);
	public User readUser(String username);
	public Blog createBlog(Blog blog);
	public Blog updateBlog(User user, Blog blog);
	public void deleteBlog(User user, Blog blog);
	public Comment CreateComment(User user, Blog blog, Comment comment);
	public void RemoveComment(User user, Blog blog, Comment comment);
	public List<Blog> readBlogByTitle(String title);
	public Blog readBlogById(String blogId);
	public void updateUser(User user);
	public Blog readBlogWithTitle(String title);
	public List<Comment> readComments(String blogTitle);
	public Comment readCommentByID(String uniqueID);
	Comment writeComment(Comment comment);
	public List<Blog> fetchBlogsByCategory(String category);
}
