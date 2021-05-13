// 53 / 53 test cases passed.
//Status: Accepted
//Runtime: 7 ms
//Memory Usage: 39.2 MB

class Solution {
    public int[] twoSum(int[] nums, int target) {
        //hardcode small size
        if(nums.length==2){
             return new int[]{ 0,1 };
        }
        
        // Initialize array to story compliments        
        int compliments = 0;
        
        
        for(int i=0;i<nums.length;i++){
            // set the current values compliment to the compliment variable
            compliments=target - nums[i];
            for(int j=0;j<i;j++){
                // back check previous values for compliment
                if(nums[j] == compliments){
                    int[] result = new int[]{ i,j }; 
                    return new int[]{ i,j };
                }
            }
            
        }
        // this line is never reached, as base case return this index
        return new int[] {0,1};
    }
}
