/*
Author: Bilal Malik
Date: February 5th, 2022
Description: A function to bump semantic versions
*/


import java.util.regex.Pattern;

public class semanticVersions{

    private static String nextVersion(String version){

        //initializing resulting string after bump
        String updatedVersion = "";
        //use pattern quote as regex considers '.' for all letters (will split on everything resulting in empty array)
        String[] versionList = version.split( Pattern.quote(".") );

        //carry flag to determine if we need an additional increment on the next iteration
        boolean carry = false;

        //walk from end of array as incrementing starts at end
        for(int i=versionList.length-1; i>=0; i--){
            int update;

            update = Integer.parseInt(versionList[i]);
            
            //prior to increment if value is greater then 9 already and not first value, this is not correct input
            if(update>9 && i>=1){
                return "not a valid semantic version.";
            }

            //catch negative values like '-12' in 2.-12.0
            if(update<0){
                return "not a valid semantic version.";
            }

            //increment value, only needs to go up by one
            update++;
            //increment value can only be ten or lower as we only increment by one
            if(update>9 && i>=1){
                carry = true;
                // only time a carry occurs is if value reaches ten as only 1 digit bump, so will always be zero
                update = 0;
            }
            
            versionList[i] = Integer.toString(update);

            //if theres nothing to carry then break, no need to walk through rest of semantic version
            if(!carry)break;

            carry = false;

        }

        //build resulting updatedVersion String by appengin from beginning to end, the versions plus dot (.)
        for(int i=0; i<versionList.length; i++){
            updatedVersion += versionList[i];
            //make sure not to append dot at end of list
            if(i < versionList.length-1) updatedVersion += ".";
        }

        return updatedVersion;
    }

    // @Test
    // private void test(){

    // }

    public static void main(String[] args){

        System.out.println(nextVersion("1.2.3"));
        System.out.println(nextVersion("1000000.0"));
        System.out.println(nextVersion("10000000"));
        System.out.println(nextVersion("0.9.9.9.9.9"));
        System.out.println(nextVersion("9.9.9.9.9.9"));

    }

}

