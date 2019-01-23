package com;

public interface B {

	/**
	 * 查询
	 * @param key
	 * @return
	 */
	Object get(Comparable key); 
	/**
	 * 移除
	 * @param key
	 */
	void remove(Comparable key); 
	/**
	 * 插入或者更新,如果已经存在,就更新,否则插入
	 * @param key
	 * @param obj
	 */
	void insertOrUpdate(Comparable key, Object obj); 
}
