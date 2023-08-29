// similar to https://leetcode.com/discuss/general-discussion/399344/dependency-graph ?
// and https://leetcode.com/problems/course-schedule-ii/ ?



// 3 problems which build on top of each other. 
// You have to keep track of processes in the whole thing basically. 
// Processes are given as an array of strings having certain file names and dependencies within them. 

// Second is regarding file changes for one process affecting dependant processes. 

import java.util.*;

public class task2 {
    public static void main(String[] args){
        // String[] processes = {
        //     "f/file.txt,run.javad/",
        //     "f/file2.txt,run2.javad/0",
        //     "f/file3.txt,run3.javad/1,0",
        // };

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


        HashMap<String,List<Integer>> fileToProcesses = new HashMap<>();
        HashMap<Integer,List<Integer>> processToDependencies = new HashMap<>();

        int[] inDegrees = new int[processes.length];

        for(int i=0; i<processes.length; i++){
            String rawProcess = processes[i];
            String[] fileAndDeps = rawProcess.split("d/");

            String[] files = fileAndDeps[0].split(",");
            //remove opening /d
            files[0] = files[0].split("f/")[1];

            //add to hashmap, filename mapped to processe
            for(int j=0; j<files.length; j++){

                List<Integer> processesList = fileToProcesses.getOrDefault(files[j], new ArrayList<Integer>());
                processesList.add(i);
                fileToProcesses.put(files[j],processesList);
            }

            System.out.println("here:"+fileAndDeps[0]);
            if(fileAndDeps.length > 1){
                String[] deps = fileAndDeps[1].split(",");
                //add to hashmap, filename mapped to processe
                for(int j=0; j<deps.length; j++){
    
                    List<Integer> depsList = processToDependencies.getOrDefault(i, new ArrayList<Integer>());
                    depsList.add(Integer.parseInt(deps[j]));
                    inDegrees[Integer.parseInt(deps[j])] += 1;
                    processToDependencies.put(i,depsList);
                }
            }

        }

        System.out.println(fileToProcesses);
        System.out.println(processToDependencies);

        String fileChanging = "run5.java";
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();


        for(int start : fileToProcesses.get(fileChanging))
            queue.add(start);

        while(!queue.isEmpty()){
            int node = queue.remove();
            if(!result.contains(node))
                result.add(node);


            if(processToDependencies.containsKey(node)){
                for(int dest : processToDependencies.get(node)){
                    inDegrees[dest] -= 1;
                    // if(inDegrees[dest] == 0)
                    queue.add(dest);
                }
            }
            
        }

        System.out.println("result: "+result);

    }
}



/*
PRACTICE

Given a list of packages that need to be built and the dependencies for each package, 
determine a valid order in which to build the packages.

eg.

0:
1: 0
2: 0
3: 1, 2
4: 3

0, 1, 2, 3, 4
*/


class example{
    public static void main(String[] args){
        String[][] input = {{},{"0,3"},{"4"},{"4"},{}};
    }

    public List<Integer> buildOrder(int[][] processes){
        Set<Integer> tempMarks = new HashSet<>();
        Set<Integer> permMarks = new HashSet<>();
        List<Integer> results = new LinkedList<>();

        for(int i=0; i<processes.length; i++){
            if(!permMarks.contains(i)){
                visit(i, processes, tempMarks, permMarks, results);
            }
        }

        return results;
    }

    private void visit(int process, 
                        int[][] processes, 
                        Set<Integer> tempMarks, 
                        Set<Integer> permMarks, 
                        List<Integer> results){

        if(tempMarks.contains(process)){
            //Throw error
            return;
        }

        if(!permMarks.contains(process)){
            tempMarks.add(process);
            
        }

    }

}

