package com.bookee.guidetest;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionsTest2 {
	public static void main(String[] args) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(-1);
		arrayList.add(3);
		arrayList.add(3);
		arrayList.add(-5);
		arrayList.add(7);
		arrayList.add(4);
		arrayList.add(-9);
		arrayList.add(-7);
		ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
		arrayList2.add(-3);
		arrayList2.add(-5);
		arrayList2.add(7);

		Collections.sort(arrayList);
		System.out.println(arrayList);
		
		//对List进行二分查找，返回索引，List必须要是有序的
		System.out.println(Collections.binarySearch(arrayList, 4));

		// 找出最大的元素值
		System.out.println(Collections.max(arrayList));

		// 用新元素替换指定元素
		Collections.replaceAll(arrayList, 3, -3);
		System.out.println(arrayList);

		System.out.println(Collections.indexOfSubList(arrayList, arrayList2));

		// 统计元素出现的次数
		System.out.println(Collections.frequency(arrayList, -3));

		// 用指定元素替换list中所有元素
		Collections.fill(arrayList, 5);
		System.out.println(arrayList);
		
		
	}
}
