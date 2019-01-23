package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

import com.BplusTree;
import com.Node;

public class Test {
	private static final String BASEPATH = "D:/B+Tree/";
	
	
	public static void main(String[] args) throws IOException {
		BplusTree tree = new BplusTree(6);
		
		addTestData(tree);
		
		Node head = findOneNode(tree);
		
		writeToFile(tree, head);
	}


	/**
	 * 将 B+ 树和叶子节点组成的链表通过序列化从内存存储到磁盘上
	 * @param tree
	 * @param next
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static void writeToFile(BplusTree tree, Node head) throws IOException, FileNotFoundException {
		int count = 0;
		while (true) {
			if (head == null)
				break;
			++count;
			List<Entry<Comparable, Object>> entries = head.getEntries();
			File file = new File(BASEPATH+String.valueOf(count) + ".txt");
			head.setFile(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
			objectOutputStream.writeObject(head);
			objectOutputStream.close();
			head = head.getNext();
		}
		File treeFile = new File(BASEPATH+"BplusTree.txt");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(treeFile));
		objectOutputStream.writeObject(tree);
		objectOutputStream.close();
	}

	/**
	 * 查询一个 B+ 树上的节点，并返回叶子节点链表的头节点
	 * @param tree
	 * @return
	 */
	private static Node findOneNode(BplusTree tree) {
		int search = 80;
		System.out.print(tree.get(search));
		Node head = tree.getHead();
		return head;
	}

	/**
	 * 添加测试数据
	 * @param tree
	 */
	private static void addTestData(BplusTree tree) {
		Random random = new Random();
		long current = System.currentTimeMillis();
		for (int j = 0; j < 100000; j++) {
			for (int i = 0; i < 100; i++) {
				int randomNumber = random.nextInt(1000);
				tree.insertOrUpdate(randomNumber, randomNumber);
			}
			for (int i = 0; i < 100; i++) {
				int randomNumber = random.nextInt(1000);
				tree.remove(randomNumber);
			}
		}
		long duration = System.currentTimeMillis() - current;
		System.out.println("time elpsed for duration: " + duration);
	}
}
