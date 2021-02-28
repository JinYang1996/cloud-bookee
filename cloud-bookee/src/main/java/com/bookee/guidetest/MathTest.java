package com.bookee.guidetest;

public class MathTest {

    public static void main(String[] args) {
        int[] arr = {45,58,12,72,77,88,11,16,85,42,111,23,24};
        //quickSort(arr,0,arr.length-1);
        bobbing(arr);
        for(int i:arr){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println(binary(arr,0,arr.length-1,42));
    }

    /**
     * 二分法查找元素下标
     * @param arr
     * @param left
     * @param right
     * @param target
     * @return
     */
    public static int binary(int[] arr,int left,int right,int target){
        int mid = (left+right)/2;
        if(mid>right || mid<left || left>right)
            return -1;
        if(arr[mid] == target)
            return mid;
        else if(arr[mid]>target)
            return binary(arr,left,mid-1,target);
        else
            return binary(arr,mid+1,right,target);
    }

    /**
     * 快排法
     * @param arr
     * @param start
     * @param end
     */
    public static void quickSort(int[] arr,int start,int end){
        int i,j,index;
        if(start>end){
            return;
        }
        i=start;
        j=end;
        index=arr[i];
        while(i<j){
            while(i<j && arr[j]>=index)
                j--;
            if(i<j)
                arr[i] = arr[j];
            while(i<j && arr[i] <index)
                i++;
            if(i<j)
                arr[j] = arr[i];
        }
        arr[i] = index;
        quickSort(arr,start,i-1);
        quickSort(arr,i+1,end);
    }

    /**
     * 冒泡法
     * @param arr
     */
    public static void bobbing(int[] arr){
        if(arr == null || arr.length <2){
            return;
        }
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1-i;j++){
                int temp ;
                if(arr[j] > arr[j+1]){
                    temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
