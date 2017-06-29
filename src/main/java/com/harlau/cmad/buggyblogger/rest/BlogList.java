package com.harlau.cmad.buggyblogger.rest;

import java.util.ArrayList;
import java.util.List;

import com.harlau.cmad.buggyblogger.api.Blog;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BlogList {

	private List<Blog> blogList = new ArrayList<>();

	public BlogList() {
		// TODO Auto-generated constructor stub
	}

	public List<Blog> getBlogList() {
		return blogList;
	}

	public void setBlogList(List<Blog> blogList) {
		this.blogList = blogList;
	}

}
