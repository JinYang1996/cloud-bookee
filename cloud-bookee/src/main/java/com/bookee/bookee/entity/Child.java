package com.bookee.bookee.entity;

public class Child extends Person {
    public String grade;
    public static void main(String[] args){
//        Person p = new Child();
//        System.out.println(p.getName());
        int[] arr = {10,1,35,61,89,36,55};
        bobbing(arr);
        for(int s : arr){
            System.out.println(s);
        }
        System.out.println(arr.toString());
    }
    public static void bobbing(int[] array){
        for(int i=0;i<array.length;i++){
            for(int j=1;j<array.length-1-i;j++){
                int temp ;
                if(array[j] > array[j+1]){
                    temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
