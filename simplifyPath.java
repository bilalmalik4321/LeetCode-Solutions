import java.util.Stack;

class Solution {
    public String simplifyPath(String path) {
        Stack<String> stackOfFileNames = new Stack<>();
        String[] fileNames = path.split("/");
        
        for(int i=0;i<fileNames.length;i++){
            if(fileNames[i].equals(".")){
                continue;
            }
            else if(fileNames[i].equals("..")){
                if(!stackOfFileNames.isEmpty()){
                    stackOfFileNames.pop();
                }
            }
            else if(fileNames[i].length()!=0){
                stackOfFileNames.push(fileNames[i]);
            }
        }
        
        if(stackOfFileNames.isEmpty())return "/";
        
        String result = "";
        
        while(!stackOfFileNames.isEmpty()){
            result = "/"+stackOfFileNames.pop()+result;
        }
        
        return result;
    }
}

