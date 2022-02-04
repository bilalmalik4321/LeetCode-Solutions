package crackingTheCodeInt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
find total subarrays that add up to 9
*/

class Solution {
        // public int slidingWindow(int[] arr, int target){
        //     int left = 0, right = 0;

        //     int sum = arr[left];
        //     int totalSubArr = 0;

        //     while(left!=arr.length){
        //         if(sum <= target){
        //             if(sum == target) totalSubArr++;
        //             right++;
        //             sum += arr[right];
        //         }
        //         else {
        //             sum -= arr[left];
        //             left++;
        //         }
        //     }

        //     return totalSubArr;
        // }

        //Q1
        // public int slidingWindow(int[] arr, int size){
        //     int sum = 0;

        //     for(int i=0; i<size; i++){
        //         sum += arr[i];
        //     }

        //     int maxSum = sum;
        //     int left = 0, right = size-1;

        //     while(right++ < arr.length-1){
        //         sum -= arr[left++];
        //         sum += arr[right];

        //         if(sum > maxSum)maxSum = sum;
        //     }

        //     return maxSum;
        // }

        //Q2
        // public List<List<Integer>> slidingWindow(int[] arr, int target){
        //     int left = 0, right = 0;
        //     int sum = arr[left];
        //     List<List<Integer>> result = new ArrayList<>();
            
        //     while(left < arr.length){
        //         if(sum <= target){
        //             if(sum==right){
        //                 List<Integer> values = new ArrayList<>();
        //                 for(int i=left; i<right+1; i++)
        //                     values.add(arr[i]);
                        
        //                 result.add(values);
        //             }

        //             if(right < arr.length-1){
        //                 right++;
        //                 sum += arr[right];
        //             }
        //         }
        //         else {
        //             if(left!=right)sum -= arr[left++]; 
        //             else {
        //                 left++;
        //                 right++;
        //                 sum = arr[left];
        //             }
        //         }
        //     }
            
        //     return result;
        // }

        //Q3
        // public int slidingWindow(int[] arr, int k){
        //     int sum = 0;
        //     int encounteredZero = 0;
            
        //     int i=0;
        //     while(i<arr.length && encounteredZero <= k){
        //         if(arr[i++]==0)encounteredZero++;
        //         sum++;
        //     }
        //     sum--;

        //     int left = 0;
        //     int maxSum = sum;

        //     while(left < arr.length){
        //         left++;
        //         while(left < arr.length && arr[left++] != 0){
        //             sum--;
        //         }
        //         sum--;

        //         i++;
        //         while(i<arr.length && arr[i++]!=0){
        //             sum++;
        //         }
        //         sum++;

        //         if(sum > maxSum) maxSum = sum;
        //     }

        //     return maxSum;
        // }

        //Q4
        public String slidingWindow(String arr, String chars){
            HashSet<Character> charSet = new HashSet<>();
            String result;

            for(int i=0; i<chars.length(); i++)
                charSet.add(chars.charAt(i));
            
            int left=0;

            while( !charSet.contains(arr.charAt(left)) ){
                left++;
            }

            String currString;



            return "";
        }

}

/*
input: fa4chba4c
chars: abc


*/

