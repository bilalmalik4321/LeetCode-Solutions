//Runtime: 20 ms, faster than 20.16% of Java online submissions for Longest Substring Without Repeating Characters.
// Memory Usage: 39.8 MB, less than 10.76% of Java online submissions for Longest Substring Without Repeating Characters.

class Solution {
    public int lengthOfLongestSubstring(String s) {
        String subString ="";
        int subStringLength=0;
        int longestLength=0;
        
        // walk through the list        
        for(int i=0; i<s.length(); i++){
            // if there is a letter that matches, the letter in the current index
            if(subString.indexOf(s.charAt(i))!=-1){
                // we back track from curent iteration to closest matching letter instead of directly searching which will return the first occurence
                int firstOccurence = i-1;
                while(s.charAt(firstOccurence)!=s.charAt(i)){
                    firstOccurence--;
                }
                // substring contains all strings inside index of the latest matching letter and the current index letter
                subString = ""+s.substring(firstOccurence,i);
                
                // if the length of the subString is larger the current longest, replace
                if(subStringLength > longestLength){
                    longestLength=subStringLength;
                }
                
                // the current continuous string, its length is stored into subStringLength variable
                subStringLength=subString.length();
            }
            else{  
                subString+=s.charAt(i);
                subStringLength++;
              
                // Included here as well, in case there is an else clause ending the string, and has a new longest
                if(i==s.length()-1 && subStringLength > longestLength){
                    longestLength=subStringLength;
                }
                
            }
        }
        
        // return the longest length
        return longestLength;
    }
}
