package com.bookee.guidetest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArraysTest {

	/**
	 * Arrays常见操作
	 * sort() 排序
	 * binarySearch() 查找
	 * fill() 填充
	 * equals() 比较
	 * asList() 转列表
	 * toString() 转字符串
	 * copyOf() 复制
	 * @param args
	 */
	public static void main(String[] args) {
		//排序
		int a[]={1,4,2,6,5,8,7,23,12};
		Arrays.sort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println();

		//sort(int[] a,int fromIndex,int toIndex)按升序排列数组的指定范围
		int b[]={1,4,2,6,5,8,7,23,12};
		Arrays.sort(b,2,5);
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i]+" ");
		}
		System.out.println();
		
		int c[] = { 1, 3, 2, 7, 6, 5, 4, 9 };
		// parallelSort(int[] a) 按照数字顺序排列指定的数组(并行的)。同sort方法一样也有按范围的排序
		Arrays.parallelSort(c);
		System.out.println("Arrays.parallelSort(c)：");
		for (int i : c) {
			System.out.print(i);
		}
		// 换行
		System.out.println();

		// parallelSort给字符数组排序，sort也可以
		char d[] = { 'a', 'f', 'b', 'c', 'e', 'A', 'C', 'B' };
		Arrays.parallelSort(d);
		System.out.println("Arrays.parallelSort(d)：");
		for (char d2 : d) {
			System.out.print(d2);
		}
		// 换行
		System.out.println();
		
		//查找 binarySearch
		char[] e = { 'a', 'f', 'b', 'c', 'e', 'A', 'C', 'B' };
		Arrays.sort(e);
		System.out.println(Arrays.toString(e));
		int s = Arrays.binarySearch(e, 'c');
		System.out.println(s);
		
		// *************比较 equals****************
		char[] f = { 'a', 'f', 'b', 'c', 'e', 'A', 'C', 'B' };
		char[] g = { 'a', 'f', 'b', 'c', 'e', 'A', 'C', 'B' };
		/*
		* 元素数量相同，并且相同位置的元素相同。 另外，如果两个数组引用都是null，则它们被认为是相等的 。
		*/
		// 输出true
		System.out.println("Arrays.equals(e, f):" + Arrays.equals(f,g));
		
		// *************填充fill(批量初始化)****************
		int[] h = { 1, 2, 3, 3, 3, 3, 6, 6, 6 };
		// 数组中所有元素重新分配值
		Arrays.fill(h, 3);
		System.out.println("Arrays.fill(h, 3)：");
		// 输出结果：333333333
		for (int i : h) {
			System.out.print(i);
		}
		// 换行
		System.out.println();

		int[] q = { 1, 2, 3, 3, 3, 3, 6, 6, 6, };
		// 数组中指定范围元素重新分配值
		Arrays.fill(q, 0, 2, 9);
		System.out.println("Arrays.fill(q, 0, 2, 9);：");
		// 输出结果：993333666
		for (int i : q) {
			System.out.print(i);
		}
		// 换行
		System.out.println();
		
		//转列表 asList()
		List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");
		System.out.println(stooges);
		
		//转字符串 toString
		char[] k = { 'a', 'f', 'b', 'c', 'e', 'A', 'C', 'B' };
		System.out.println(Arrays.toString(k));
		
		// *************复制 copy****************
		// copyOf 方法实现数组复制,h为数组，6为复制的长度
		int[] w = { 1, 2, 3, 3, 3, 3, 6, 6, 6, };
		int i[] = Arrays.copyOf(w, 7);
		System.out.println("Arrays.copyOf(w, 6);：");
		// 输出结果：123333
		for (int j : i) {
			System.out.print(j);
		}
		// 换行
		System.out.println();
		// copyOfRange将指定数组的指定范围复制到新数组中
		int j[] = Arrays.copyOfRange(w, 6, 11);
		System.out.println("Arrays.copyOfRange(w, 6, 11)：");
		// 输出结果66600(h数组只有9个元素这里是从索引6到索引11复制所以不足的就为0)
		for (int j2 : j) {
			System.out.print(j2);
		}
		// 换行
		System.out.println();
		
		
		//数组转List集合
		String[] arr = {"aa","bb","dd"};
		List<String> list = Arrays.asList(arr);
		//或者使用 
		List<String> list2 = Arrays.asList("aa","bb","dd");
		System.out.println(list2);
		
		//Arrays.asList()将数组转换为集合后，底层其实还是数组，不能使用其修改集合相关方法
		//它的add/remove/clear方法都会抛出UnSupportedOperationException异常
		//Arrays.asList()体现的是适配器模式，只是转换接口，后台的数据仍是数组
		try {
			list.add("rrr");
			System.out.println(list.toString());
		} catch (UnsupportedOperationException e1) {
			System.out.println("this is UnsupportedOperationException!");
		}
		
		int[] myArray = {1,5,3,2};
		List myList = Arrays.asList(myArray);
		System.out.println(myList.get(0));
		System.out.println(myList.size());
		try {
			System.out.println(myList.get(1));
		} catch (ArrayIndexOutOfBoundsException e1) {
			System.out.println("this is ArrayIndexOutOfBoundsException!");
		}
		
		int[] array = (int[])myList.get(0);
		System.out.println(array[1]);
		
		//使用包装类型数组就可以解决问题
		Integer[] myArray1 = {1,5,3,2};
		List myList1 = Arrays.asList(myArray1);
		System.out.println(myList1.get(0));
		System.out.println(myList1.size());
		System.out.println(myList1.get(1));
		
		//实现数组转List
		//1、自己动手
		Integer[] myArr = {3,7,4,1};
		List myList2 = arrayToList(myArr);
		System.out.println(myList2.get(1));
		
		//2、最简便的方法
		List list5 = new ArrayList<>(Arrays.asList("rr","ee","vv"));
		System.out.println(list5);
		
		//3、使用java8的stream
		Integer[] myArray3 = {3,7,4,1};
		List myList3 = Arrays.stream(myArray3).collect(Collectors.toList());
		int[] myArray4 = {3,7,4,1};
		List myList4 = Arrays.stream(myArray4).boxed().collect(Collectors.toList());
		System.out.println(myList4);
		
		String [] sss = new String[]{
			    "dog", "lazy", "a", "over", "jumps", "fox", "brown", "quick", "A"
			};
		List<String> myList5 = Arrays.asList(sss);
		Collections.reverse(myList5);
		System.out.println(myList5);
//		String[] sass = myList.toArray(new String[myList.size()]);
		System.out.println(sss);
	}
	
	static <T> List<T> arrayToList(final T[] array){
		final List<T> l = new ArrayList<T>(array.length);
		for(final T s:array){
			l.add(s);
		}
		return l;
	}
}
