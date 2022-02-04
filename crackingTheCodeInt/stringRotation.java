package crackingTheCodeInt;

/*

*/

class Solution {
    
    public boolean stringRotation(String s1, String s2){
        if(s1.length() == s2.length()){
            s2+=s2;
            return s2.contains(s1);
        }
        return false;
    }

}
