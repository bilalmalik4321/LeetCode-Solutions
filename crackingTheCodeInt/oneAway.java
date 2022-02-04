package crackingTheCodeInt;
/*
There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character.
Given two strings, write a function to check if they are one edit (or zero edits) away.

eg.
pale, ple -> true
pales, pale -> true
pale, bale -> true
pale, bake -> false
*/
class Solution {
    public boolean oneAway(String from, String to){
        if(from.equals(to)) return true;

        boolean fixed = false;

        int i=0, j=0;

        while(i < from.length() || j < to.length()){
            if(j>=to.length()){
                if(!fixed)fixed = true;
                else return false;
            }

            if(i>=from.length()){
                if(!fixed)fixed = true;
                else return false;
            }

            if(from.charAt(i)!=to.charAt(j)){
                if(fixed)return false;
                fixed = true;

                //ignore if replace
                if(i+1 < from.length() && j+1 < to.length() && from.charAt(i+1)!=to.charAt(j+1)){
                    //removing
                    if(i+1 < from.length() && from.charAt(i+1) == to.charAt(j)){
                        i++;
                    }
                    else if(j+1 < to.length() && from.charAt(i) == to.charAt(j+1)){
                        j++;
                    }
                    //adding
                }
            }

            i++;
            j++;
        }

        return true;
    }
}

/*
from = "pale"
to = "pako"

fixed = true
i = 2
j = 2

*/

