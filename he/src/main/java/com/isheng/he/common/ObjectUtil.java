package com.isheng.he.common;

import java.util.List;
import java.util.Map;

/**
 * 对象校验工具
 * @Author eping92
 * @Date 2017年4月11日
 *
 */
public class ObjectUtil {
	//对象不为空
	public static boolean objNotNull(Object obj) {
		if (obj != null && !"null".equalsIgnoreCase(obj.toString()) && !obj.toString().isEmpty()) {
			return true;
		}
		return false;
	}
	//数组是否为空
	public static <T> boolean arrayNotNull(T[] array) {
		if (array != null && array.length > 0) {
			return true;
		}
		return false;
	}
	//list集合判断
	public static <T> boolean listNotNull(List<T> list) {
		if (list != null && !list.isEmpty()) {
			return true;
		}
		return false;
	}
	//map判断
	public static <K, V> boolean mapNotNull(Map<K, V> map) {
		if (map != null && !map.isEmpty()) {
			return true;
		}
		return false;
	}

}
