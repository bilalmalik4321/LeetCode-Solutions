// similar to https://leetcode.com/problems/wildcard-matching/


// 3 problems which build on top of each other. 
// You have to keep track of processes in the whole thing basically. 
// Processes are given as an array of strings having certain file names and dependencies within them. 

// The last question involves wild card matching where you'll have to perform wild card matching to figure out which files are being changed. 

import java.util.*;

public class task3 {
    public static void main(String[] args){
        String[] processes = {
            "f/file1.txt,run1.javad/1,3",//A
            "f/file2.txt,run2.javad/4",//B
            "f/file3.txt,run3.javad/5",//C

            "f/file4.txt,run4.javad/4,5",//D
            "f/file5.txt,run5.javad/6,7",//E
            "f/file6.txt,run6.javad/6,8",//F

            "f/file7.txt,run7.javad/9,8",//G
            "f/file8.txt,run8.javad/9",//H
            "f/file9.txt,run9.javad/",//I

            "f/file10.txt,run10.javad/",//J
            "f/file11.txt,run11.javad/",//K
        };

        List<String> fileList = new ArrayList<>();
        for(int i=0; i<processes.length; i++){
            String rawProcess = processes[i];
            String[] fileAndDeps = rawProcess.split("d/");

            String[] files = fileAndDeps[0].split(",");
            //remove opening /d
            files[0] = files[0].split("f/")[1];

            //add to hashmap, filename mapped to processe
            for(int j=0; j<files.length; j++){
                fileList.add(files[j]);
            }
        }

        System.out.println(fileList);
        List<String> result = new ArrayList<>();


        String pattern = "*11.txt";

        for(int i=0; i<fileList.size(); i++){
            char[] patternVals = pattern.toCharArray();
            char[] word = fileList.get(i).toCharArray();

            int patternLength = 0;
            boolean isStart = true;
            for(int j=0; j<patternVals.length; j++){
                if(patternVals[j] == '*'){
                    if(isStart){
                        patternVals[patternLength++] = patternVals[j];
                        isStart = false;
                    }
                }
                else{
                    patternVals[patternLength++] = patternVals[j];
                    isStart = true;
                }
            }

            boolean[][] dp = new boolean[word.length+1][patternLength+1];
            if(patternLength > 0 && patternVals[0] == '*') dp[0][1] = true;

            dp[0][0] = true;

            for(int j=0; j<word.length; j++){
                for(int k=0; k<patternLength; k++){
                    if(patternVals[k]==word[j]){
                        dp[j+1][k+1] = dp[j][k];
                    }
                    else if(patternVals[k]=='*'){
                        dp[j+1][k+1] = dp[j+1][k]||dp[j][k+1];
                    }
                }
            }

            if(dp[word.length][patternLength]) result.add(fileList.get(i));
        }

        System.out.println(result);
    }
}
