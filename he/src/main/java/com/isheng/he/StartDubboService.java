package com.isheng.he;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 启动dubbo服务端服务
 * @author Administrator
 *
 */
public class StartDubboService{
	
	private static final Logger logger = LoggerFactory.getLogger(StartDubboService.class);
	private static final String SPRING_CONFIG = "classpath:/spring/spring-*.xml";
	private static ClassPathXmlApplicationContext context ;
	
	
	
	//通过dubbo.container.Main启动服务  要在classpath目录下配置 dubbo.properties 相关配置 
//	public static void main(String[] args){
//		logger.info("开始启动dubbo服务.....");
//		com.alibaba.dubbo.container.Main.main(args);
//	}
	
	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext(SPRING_CONFIG);
		context.start();
		
		logger.info("服务启动成功......");
		
		synchronized (StartDubboService.class) {
			while (true) {
				try {
					StartDubboService.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
