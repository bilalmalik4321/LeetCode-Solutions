package RobinhoodPrep;

import java.util.*;

public class digitAnagrams {
    // [123,321,456]
    // {123 : 2, 456: 1}
    // 1
    private static int digitAnagrams(int[] a){ // T: O(NKlgK), S: O(NK+N)
        Map<String, Integer> freqMap = new HashMap<>();
        for(int num : a){
            String numStr = String.valueOf(num);
            char[] tmpArr = numStr.toCharArray();
            
            Arrays.sort(tmpArr);

            String sorted = "";
            for(char ch : tmpArr) sorted += Character.toString(ch);

            freqMap.put(sorted, freqMap.getOrDefault(sorted,0) + 1);
        }

        System.out.println(freqMap);
        int pairs = 0;
        for(int val : freqMap.values())
            if(val > 1) pairs += ((val * (val-1)) / 2);

        return pairs;
    }

    public static void main(String[] args){
        System.out.println(digitAnagrams(new int[]{123, 321, 456})); // 1
        System.out.println(digitAnagrams(new int[]{21, 12, 44, 43, 34})); // 2
        System.out.println(digitAnagrams(new int[]{25, 35, 872, 228, 53, 278, 872})); // 4
        // 1 2 3 4 5
        // 2->1, 3->3, 4->6, 5->10
        // n(n-1)/2
    }
}
