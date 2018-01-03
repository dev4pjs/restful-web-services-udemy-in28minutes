package com.example.demo;

import java.util.Locale;

import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import com.example.demo.controller.BaseController;

@RestController
@RequestMapping("/v1")
public class HelloController implements BaseController{

	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@Autowired
	private LocaleResolver LocalResolver;
	
	@GetMapping("/hi")
	public String hi(){
		return "Hello World!!";
	}
	
	@GetMapping("/hello-world")
	public ResponseEntity<HelloWorldBean> helloWorldBean(){
		return new ResponseEntity<HelloWorldBean>(new HelloWorldBean("Hello World!!"),getNoCacheHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/hello-world/pathvariable/{message}")
	public ResponseEntity<HelloWorldBean> helloWorldBean(@PathVariable("message") String message){
		return new ResponseEntity<HelloWorldBean>(new HelloWorldBean(String.format("Hello World, %s", message)),getNoCacheHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/hello-world-international")
	public String helloWorldInternational(@RequestHeader(name="Accept-Language", required=false) Locale locale){
		if(locale==null)
			locale = Locale.US;
		else if(locale.getLanguage().equals("")){
			System.out.println(locale.getCountry());
			locale = Locale.US;
		}
			
		return messageSource.getMessage("good.morning.message", null, locale);
	}
	
		
	
	
}
