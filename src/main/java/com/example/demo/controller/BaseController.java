package com.example.demo.controller;

import org.springframework.http.HttpHeaders;

public interface BaseController {
	default HttpHeaders getNoCacheHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Cache-Control", "no-cache");
		return responseHeaders;
	}

}
