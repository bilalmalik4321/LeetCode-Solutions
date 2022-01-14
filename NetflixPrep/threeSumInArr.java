package NetflixPrep;

import java.util.Arrays;
/*
Given an array of integers and a value, determine if there are any three integers in the array whose sum equals the given value.
*/
public class threeSumInArr {

    static boolean find_sum_of_two(int[] A, int val, int start_index) {
        for (int i = start_index, j = A.length - 1; i < j;) {
            int sum = A[i] + A[j];
            if (sum == val) {
            return true;
            }  

            if (sum < val) {
            ++i;
            } else {
            --j;
            }
        }

        return false;
    }
    
    public static Boolean find_sum_of_three_v3(int arr[], int required_sum) {

        Arrays.sort(arr);
        for (int i = 0; i < arr.length-2; ++i) {
            int remaining_sum = required_sum - arr[i];
            if (find_sum_of_two(arr, remaining_sum, i + 1)) {
                return true;
            }
        }  
        
        return false;

    }
}
