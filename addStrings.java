class Solution {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        boolean carry = false;
        String result = "";
        
        while(i>=0 || j>=0){
            int currSum = 0;
            
            if(i>-1){
                currSum += num1.charAt(i) - '0';
                i--;
            }
            
            if(j>-1){
                currSum += num2.charAt(j) - '0';
                j--;
            }
            
            if(carry){
                currSum++;
                carry = false;
            }
            
            if(currSum>9){
                currSum %= 10;
                carry = true;
            }
            
            result = Integer.toString(currSum) + result;
            
        }
        
        if(carry){
            result = "1"+result;
            carry = false;
        }
        
        return result;
        
    }
}

