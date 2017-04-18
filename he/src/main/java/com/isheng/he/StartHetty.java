package com.isheng.he;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hetty.core.Hetty;

public class StartHetty {
	
	private static final Logger logger = LoggerFactory.getLogger(StartHetty.class);
	
	public static void main(String[] args) {
		try {
			new Hetty("hetty.properties").start();
			logger.info("Hetty Rpc服务启动成功.......");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
