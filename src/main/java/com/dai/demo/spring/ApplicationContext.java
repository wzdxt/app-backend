package com.dai.demo.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dai.demo.service.FooService;
import com.dai.demo.spring.hibernate.HibernateConfiguration;

@Configuration
@EnableJpaRepositories("com.dai.demo.**.repository")	//all repository packages
//@Import(HibernateConfiguration.class)
public class ApplicationContext {

	@Bean(name = "fooServiceImpl2")
	public FooService getFooService() {
		return new A();
	}

}

class A implements FooService {
	@Override
	public String get() {
		return "bbbbbbbbbbbbbbbbbb";
	}

}
