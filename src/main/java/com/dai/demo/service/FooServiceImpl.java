package com.dai.demo.service;

import org.springframework.stereotype.Service;

@Service
public class FooServiceImpl implements FooService {
	public String get() {
		return "asdf";
	}
}
