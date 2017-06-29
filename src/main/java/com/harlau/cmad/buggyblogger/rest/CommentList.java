package com.harlau.cmad.buggyblogger.rest;

import java.util.ArrayList;
import java.util.List;

import com.harlau.cmad.buggyblogger.api.Comment;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommentList {

	private List<Comment> CommentList = new ArrayList<>();

	public CommentList() {
		// TODO Auto-generated constructor stub
	}

	public List<Comment> getCommentList() {
		return CommentList;
	}

	public void setCommentList(List<Comment> commentList) {
		CommentList = commentList;
	}
}