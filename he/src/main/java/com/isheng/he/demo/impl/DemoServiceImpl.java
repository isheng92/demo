package com.isheng.he.demo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isheng.he.demo.DemoService;

public class DemoServiceImpl implements DemoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public String sayHello(String name) {
		logger.info("服务端被调用了={}", this.getClass());
		return new StringBuilder().append(name).append("对大家说：“Hello Dubbo”").toString();
	}

}
