package com.isheng.he.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.isheng.he.common.JedisClient;
import com.isheng.he.common.ObjectUtil;

import redis.clients.jedis.Jedis;

public class RedisDao <T>{
	
	private final Logger logger =  LoggerFactory.getLogger(this.getClass());
	private final RuntimeSchema<T> schema;
	
	public RedisDao(Class<T> entityClass) {
		this.schema = RuntimeSchema.createFrom(entityClass);
	}

	//从redis中获取，并反序列化成java对象
	public T get(String key) {
		if (ObjectUtil.objNotNull(key)) {
			try {
				Jedis jedis = JedisClient.getJedis();
				try{
					byte[] bytes = jedis.get(key.getBytes());
					if (bytes != null) {
						T t = schema.newMessage();//空对象
						ProtostuffIOUtil.mergeFrom(bytes, t, schema);//反序列化到空对象中
						return t;
					}
				} finally {
					if (jedis != null) {
						jedis.close();
					}
				}
			} catch(Exception e) {
				logger.error(e.getMessage(), e);
			} 
		}
		return null;
	}
	
	//将对象序列化并存储到redis中
	public String set(String key, T t, int seconds) {
		String result = "";
		try {
			Jedis jedis = JedisClient.getJedis();
			try {
				byte[] bytes = ProtostuffIOUtil.toByteArray(t, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				result = jedis.setex(key.getBytes(), seconds, bytes);
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}


}
