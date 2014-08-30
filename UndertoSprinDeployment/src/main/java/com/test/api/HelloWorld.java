package com.test.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

	@RequestMapping(value = "/sayHello")
	public String sayHello() {
		return "Hello!";
	}
}
