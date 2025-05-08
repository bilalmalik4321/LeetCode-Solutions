package mockMeta;

/**
 *  Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.

You must implement a solution with a linear runtime complexity and use only constant extra space.

 

Example 1:

Input: nums = [2,2,3,2]
Output: 3

Example 2:

Input: nums = [0,1,0,1,0,1,99]
Output: 99

 

Constraints:

    1 <= nums.length <= 3 * 104
    -231 <= nums[i] <= 231 - 1
    Each element in nums appears exactly three times except for one element which appears once.


    O(nlgn) O(1)
    O(N) O(N)
       p
[2,2,3,2]
    plus = false
    prd = 3

    nums = [0,1,0,1,0,1,99]
    1 xor 99

    0 1


**/


public class solution {
    // 0 XOR Y = Y
    public int findDistinct(int[] nums){
        int res = 0;

        for(int num : nums){
            res = res ^ num;
        }
        
        return res;
    }

    
}


/** 


    ==========================================================
    Problem #2

    Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome

    that can be built with those letters.

    Letters are case sensitive, for example, "Aa" is not considered a palindrome.

    Example 1:

    Input: s = "abccccdd"
    Output: 7
    Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.

{a : 1, b : 1, c:4, d: 2}
7
2 / 2 = 1
str = dccaccd

boolean oddExists = true

s = "aaabccccdd"

Example 2:

    Input: s = "a"
    Output: 1
    Explanation: The longest palindrome that can be built is "a", whose length is 1.

    Constraints:

        1 <= s.length <= 2000
        s consists of lowercase and/or uppercase English letters only.




Follow up: Return the string instead of the length

 * 
 * 
**/

public class solution {
    public longestPali(String input){
        Map<Character, Integer> freqMap = new HashMap<>();

        for(char ch : input.toCharArray()){
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        boolean hasOdd = false;
        int paliSize = 0;

        for(int val : freqMap.values()){
            if(val%2 != 0){ // odd
                hasOdd = true;
                val -= 1;
            }

            paliSize += val;
        }

        return hasOdd ? paliSize + 1 : paliSize;
    }

// "abccccdd"
/**
 * 
 * {a:1, b:1, c:4, d:2, e: 1}
 * res = "dccaccd"
 * 
 * @param input
 */
    public constructLongestPali(String input){
        Map<Character, Integer> freqMap = new HashMap<>();

        for(char ch : input.toCharArray()){
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        String res = "";
        for(Map.Entry<Character,Integer> entry : freqMap.entrySet()){
            if(entry.getValue() % 2 != 0){
                res += Character.toString(entry.getKey());
                break;
            }
        }

        
        for(Map.Entry<Character,Integer> entry : freqMap.entrySet()){
            String ch = Character.toString(entry.getKey());
            int freq = entry.getValue();
            
            if(freq%2 != 0)
                freq -= 1;
             
            while(freq > 0){
                res = ch + res + ch;
                freq -= 2;
            }
        }

        return res;
    }
    
}
