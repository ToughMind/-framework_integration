package lq.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lq.web.controller.UserAction;

public class A {
	public static void main(String[] args) {
		System.setProperty("spring.profiles.active","dev");
		ApplicationContext ac = new ClassPathXmlApplicationContext("/applicationContext.xml");
		
		System.out.println(((UserAction)ac.getBean("userAction")).list());
	}
}
