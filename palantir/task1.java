// 3 problems which build on top of each other. 
// You have to keep track of processes in the whole thing basically. 
// Processes are given as an array of strings having certain file names and dependencies within them. 

// First task is to identify which process is affected when a file is changed. 

import java.util.*;

class task1{

    public static void main(String[] args){
        String[] processes = {
            "f/file.txt,run.javad/",
            "f/file2.txt,run2.javad/0",
            "f/file3.txt,run3.javad/1,0"
        };

        HashMap<String,List<Integer>> fileToProcesses = new HashMap<>();

        for(int i=0; i<processes.length; i++){
            String rawProcess = processes[i];
            String[] fileAndDeps = rawProcess.split("d/");

            String[] files = fileAndDeps[0].split(",");
            //remove opening /d
            files[0] = files[0].split("f/")[1];

            for(int j=0; j<files.length; j++){

                List<Integer> processesList = fileToProcesses.getOrDefault(files[j], new ArrayList<Integer>());
                processesList.add(i);
                fileToProcesses.put(files[j],processesList);
            }
        }

        System.out.println(fileToProcesses);

    }

}

