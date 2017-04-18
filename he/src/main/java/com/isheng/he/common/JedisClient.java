package com.isheng.he.common;

import java.util.ResourceBundle;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClient {
	
	private static JedisPool jedisPool = null;
	
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if (bundle != null) {
			 String maxActive = bundle.getString("redis.pool.maxActive");
	         String maxIdle = bundle.getString("redis.pool.maxIdle");
	         String minIdle = bundle.getString("redis.pool.minIdle");
	         String maxWait = bundle.getString("redis.pool.maxWait");
	         String testOnBorrow = bundle.getString("redis.pool.testOnBorrow");
	         String testOnReturn = bundle.getString("redis.pool.testOnReturn");
	         String ip = bundle.getString("redis.ip");
	         String port = bundle.getString("redis.port");
	         
	         JedisPoolConfig config = new JedisPoolConfig();
	         config.setMaxTotal(Integer.valueOf(maxActive));
	         config.setMaxIdle(Integer.valueOf(maxIdle));
	         config.setMinIdle(Integer.valueOf(minIdle));
	         config.setMaxWaitMillis(Long.valueOf(maxWait));
	         config.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
	         config.setTestOnReturn(Boolean.valueOf(testOnReturn));
	         
	         jedisPool = new JedisPool(config, ip, Integer.valueOf(port));
		}
	}
	
	public static Jedis getJedis(){
		if (jedisPool != null) {
			return jedisPool.getResource();
		}
		return null;
	}
	
	public static String setString(String key, int seconds, String value){
		String result = "";
		try {
			Jedis jedis = getJedis();
			try {
				RuntimeSchema<String> schema = RuntimeSchema.createFrom(String.class);
				byte[] bytes = ProtostuffIOUtil.toByteArray(value, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				result = jedis.setex(key.getBytes(), seconds, bytes);
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
