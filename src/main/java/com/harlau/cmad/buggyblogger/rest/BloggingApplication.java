package com.harlau.cmad.buggyblogger.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class BloggingApplication extends ResourceConfig {
	public BloggingApplication() {
		packages("com.harlau.cmad.buggyblogger.rest");
	}
}
