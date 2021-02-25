package com.bookee.leetcodetest;

import java.util.HashMap;
import java.util.Map;

/**
 * 四数相加
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。

为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。

 * @author jy
 *
 */
public class FourNumAdd {
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer,Integer> map = new HashMap<>();
        int res = 0;
        for(int i:A){
        	for(int j:B){
        		map.merge(i+j, 1, (v1,v2)->v1+1);
        	}
        } 
        for(int k:C){
        	for(int l:D){
        		res += map.getOrDefault(-(k+l),0);
        	}
        }
        return res;
    }
	
	public static void main(String[] args){
		int[] A = {1,2};
		int[] B = {-2,-1};
		int[] C = {-1,2};
		int[] D = {0,2};
		FourNumAdd f = new FourNumAdd();
		System.out.println(f.fourSumCount(A,B,C,D));
	}
}
