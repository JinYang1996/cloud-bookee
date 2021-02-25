package com.bookee.guidetest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsTest1 {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);

        System.out.println(arrayList);

        //按自然排序的升序排序
        Collections.sort(arrayList);
        System.out.println(arrayList);

        //交换两个索引位置的元素
        Collections.swap(arrayList,1,3);
        System.out.println(arrayList);

        //随机排序
        Collections.shuffle(arrayList);
        System.out.println(arrayList);

        //反转
        Collections.reverse(arrayList);
        System.out.println(arrayList);

        //旋转。当distance为正数时，将list后distance个元素整体移到前面。当distance为负数时，将 list的前distance个元素整体移到后面。
        Collections.rotate(arrayList,2);
        System.out.println(arrayList);

        //定制排序，由Comparator控制排序逻辑
        Collections.sort(arrayList,new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(arrayList);

    }
}
