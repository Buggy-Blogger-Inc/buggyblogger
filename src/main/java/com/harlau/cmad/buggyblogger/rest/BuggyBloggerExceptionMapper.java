package com.harlau.cmad.buggyblogger.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.harlau.cmad.buggyblogger.api.AuthFailException;
import com.harlau.cmad.buggyblogger.api.DuplicateUserException;
import com.harlau.cmad.buggyblogger.api.UserNotFoundException;

@Provider
public class BuggyBloggerExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable t) {
		t.printStackTrace();
		if (t instanceof UserNotFoundException)
			return Response.status(404).build();
		if (t instanceof DuplicateUserException)
			return Response.status(405).build();
		if (t instanceof AuthFailException)
			return Response.status(406).build();
		else
			return Response.status(500).build();
	}

}
