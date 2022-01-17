import java.util.HashMap;

class SparseVector {
    
    HashMap<Integer,Integer> indexToVal = new HashMap<>();
    
    SparseVector(int[] nums) {
        
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0)indexToVal.put(i,nums[i]);
        }
        
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        HashMap<Integer,Integer> indexToVal2 = vec.indexToVal;
        int dotProduct = 0;
        for(int index : indexToVal.keySet()){
            if(indexToVal2.containsKey(index)){
                dotProduct += indexToVal2.get(index)*indexToVal.get(index);
            }
        }
        return dotProduct;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
