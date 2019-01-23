package com;

import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class BplusTree implements B, Serializable {

	/** 根节点 */
	protected Node root;
	/** 阶数,M值 */
	protected int order;
	/** 叶子节点的链表头 */
	protected Node head;

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public Object get(Comparable key) {
		return root.get(key);
	}

	@Override
	public void remove(Comparable key) {
		root.remove(key, this);
	}

	@Override
	public void insertOrUpdate(Comparable key, Object obj) {
		root.insertOrUpdate(key, obj, this);
	}

	public BplusTree(int order) {
		if (order < 3) {
			System.out.print("order must be greater than 2");
			System.exit(0);
		}
		this.order = order;
		root = new Node(true, true);
		head = root;
	}
}
