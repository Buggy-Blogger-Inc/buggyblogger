package com.harlau.cmad.buggyblogger.rest;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import java.security.Key;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.harlau.cmad.buggyblogger.jwtfilter.JWTTokenNeeded;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.glarimy.cmad.blog.api.Blog;
import com.harlau.cmad.buggyblogger.api.*;
import com.harlau.cmad.buggyblogger.service.HarlauBuggyBlogger;
/*
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;*/

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/buggyblogger")
public class BuggyBloggerController {
	/*
	private static Library library = new GlarimyLibrary();

	@POST
	@Path("/book")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Book book) {
		library.add(book);
		return Response.ok().build();
	}

	@GET
	@Path("/book/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("isbn") int isbn) {
		Book book = library.find(isbn);
		return Response.ok().entity(book).build();
	}
	private static BuggyBlogger buggyBlogger = new HarlauBuggyBlogger();*/
	
	private String issueToken(String login) {
		String keyString = "simplekey";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
		String jwtToken = Jwts.builder()
				.setSubject(login)
				.setIssuer("cmad")
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
		return jwtToken;
	}
	private BuggyBlogger buggyBlogger = new HarlauBuggyBlogger();

	@POST
	@Path("/user")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		buggyBlogger.registerUser(user);
		return Response.ok().build();
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN })
	//@Produces(MediaType.APPLICATION_JSON)
	public Response userLogin(UserLogin userLogin) {
		/*User user = buggyBlogger.authUser(userLogin.getUsername(),userLogin.getPassword());
		//return Response.ok().entity(user).build();
		BlogList blogs = new BlogList(); 
		blogs.setBlogList(buggyBlogger.retrieveBlogs(userLogin.getUsername()));
		return Response.ok().entity(blogs).build();
		
		String uname = user.getUsername();
		String password =  user.getPassword();*/
		User user;
		System.out.println("Recieved credentials: " + userLogin.getUsername() +" " + userLogin.getPassword());
		try {
			user = buggyBlogger.authUser(userLogin.getUsername(),userLogin.getPassword());
	
		} catch (AuthFailException afe){
			return Response.status(UNAUTHORIZED).build();
			//return Response.status(UNAUTHORIZED).header(AUTHORIZATION, "User Name / Password Donot Match").build();
		} catch (Exception e) {
			return Response.status(UNAUTHORIZED).build();
		}
		// Issue a token for the user
		String token = issueToken(user.getUsername());

		System.out.println("login success Token generated: " + token);

		return Response.ok("success").header(AUTHORIZATION, "Bearer " + token).build();

	}

	@GET
	@Path("/user/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("username") String username) {
		User user = buggyBlogger.loginUser(username);
		return Response.ok().entity(user).build();
	}
	
	@POST
	@Path("/blog/{blogId}/comment")
	@JWTTokenNeeded
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(@PathParam("blogId") String blogId, CommentRest commentRest) {
		//buggyBlogger.addComment(username, title, comment);
		Comment newComment = new Comment();
		Date date = new Date();
		newComment.setCommentDate(date);
		newComment.setUsername("anonymous");
		newComment.setBlogId(blogId);
		newComment.setCommentContent(commentRest.getCommentContent());
		String uniqueID = null;
		//List<Comment> commentExists;
		Comment commentExists;
		do {
			uniqueID = Long.toHexString(Double.doubleToLongBits(Math.random()));
			//commentExists = buggyBlogger.viewComments(title);
			commentExists = buggyBlogger.viewCommentById(uniqueID);
		}while(commentExists != null);
		newComment.setCommentId(uniqueID);
		buggyBlogger.writeComment(blogId, newComment);
		
		return Response.ok().build();
	}
	
	@POST
	@Path("/user/{username}/blog")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBlog(@PathParam("username") String username, BlogRest blogRest) {
		Blog blog = new Blog();
		blog.setUsername(username);
		//blog.setBlogId(blogId);
		String uniqueID = null;
		Blog blogExist;
		try {
			do {
				uniqueID = Long.toHexString(Double.doubleToLongBits(Math.random()));
				blogExist = buggyBlogger.viewBlog(uniqueID);
			}while(blogExist != null);
		} catch (BlogNotFoundException e) {
			blog.setBlogId(uniqueID);
			Date currDate = new Date();
			blog.setDate(currDate);
			blog.setCategory(blogRest.getCategory());
			blog.setTitle(blogRest.getTitle());
			blog.setBlogContent(blogRest.getBlogContent());						
		}
		buggyBlogger.writeBlog(username, blog);
		return Response.ok().build();
	}

	@GET
	@Path("/user/{username}/blogs")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBlogs(@PathParam("username") String username) {
		System.out.println("Get Blogs for User " + username);
		/*BlogList blogs = new BlogList(); 
		blogs.setBlogList(buggyBlogger.retrieveBlogs(username));
		return Response.ok().entity(blogs).build();*/
		List<Blog> blogs = buggyBlogger.retrieveBlogs(username);
		//Response.status(200).entity(result).build();
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
		try {
			result = mapper.writeValueAsString(blogs);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return Response.ok().entity(result).build();
	}
	
	@GET
	@Path("/blog/{category}/blogs")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBlogsByCategory(@PathParam("category") String category) {
		System.out.println("Get Blogs for User " + category);
		/*BlogList blogs = new BlogList(); 
		blogs.setBlogList(buggyBlogger.retrieveBlogs(username));
		return Response.ok().entity(blogs).build();*/
		List<Blog> blogs = buggyBlogger.retrieveBlogsByCategory(category);
		//Response.status(200).entity(result).build();
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
		try {
			result = mapper.writeValueAsString(blogs);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return Response.ok().entity(result).build();
	}
	
	@GET
	@Path("/blog/{blogId}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommentsForBlogId(@PathParam("blogId") String blogId) {
		//Blog blog = buggyBlogger.viewBlog(blogId);
		/*CommentList comments = new CommentList();
		comments.setCommentList(buggyBlogger.viewComments(blogId));
		
		return Response.ok().entity(comments).build();*/
		System.out.println("Get Comments for blogId " + blogId);
		List<Comment> comments = buggyBlogger.viewComments(blogId);
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
		try {
			result = mapper.writeValueAsString(comments);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return Response.ok().entity(result).build();
	}
	
	@GET
	@Path("/blog/{title}/blgs")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBlog(@PathParam("title") String title) {
		List<Blog> blogs = buggyBlogger.viewBlogByTitle(title);
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
		try {
			result = mapper.writeValueAsString(blogs);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return Response.ok().entity(result).build();
	}
	/*
	@GET
	@Path("/blog/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBlogByTitle(@PathParam("title") String title) {
		Blog blog = buggyBlogger.viewBlogWithTitle(title);
		return Response.ok().entity(blog).build();
	} */
	
	@GET
	@Path("/blog/{blogId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBlogById(@PathParam("blogId") String blogId) {
		Blog blog = buggyBlogger.viewBlog(blogId);
		return Response.ok().entity(blog).build();
	}

}