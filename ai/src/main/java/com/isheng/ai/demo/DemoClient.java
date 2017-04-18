package com.isheng.ai.demo;

import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.isheng.he.demo.DemoService;


public class DemoClient {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:/spring/spring-*.xml"} );
		DemoService service = context.getBean("demoService", DemoService.class);
		String result = service.sayHello("何平波");
		LoggerFactory.getLogger(DemoClient.class).info("客服端运行结果={}", result);
	}

}
