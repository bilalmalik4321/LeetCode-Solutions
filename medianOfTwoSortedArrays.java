//Runtime: 3 ms, faster than 38.02% of Java online submissions for Median of Two Sorted Arrays.
//Memory Usage: 40 MB, less than 79.46% of Java online submissions for Median of Two Sorted Arrays.

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // initialize counter to keep track of each num list index
        int nums1Counter=0;
        int nums2Counter=0;
        
        int combinedLengths=nums1.length + nums2.length;
        
        ArrayList<Integer> newArray= new ArrayList<Integer>(); 
        
        while(newArray.size() != combinedLengths){
            if(nums1Counter < nums1.length && nums2Counter < nums2.length){
                
                if(nums1[nums1Counter] < nums2[nums2Counter]){
                newArray.add(nums1[nums1Counter]);
                nums1Counter++;
                }
                else if(nums2[nums2Counter] < nums1[nums1Counter]){
                    newArray.add(nums2[nums2Counter]);
                    nums2Counter++;
                }
                else if(nums2[nums2Counter] == nums1[nums1Counter]){
                    newArray.add(nums2[nums2Counter]);
                    newArray.add(nums2[nums2Counter]);
                    nums1Counter++;
                    nums2Counter++;
                }
            }
            else if(nums1Counter < nums1.length && nums2Counter == nums2.length){
                newArray.add(nums1[nums1Counter]);
                nums1Counter++;
            }
             else if(nums2Counter < nums2.length && nums1Counter == nums1.length){
                newArray.add(nums2[nums2Counter]);
                nums2Counter++;
            }
            
        
        }
        
        // after numbers are put into one arrayList, get the size         
        int arraySize=newArray.size();
        double medianResult = 0;
        //if size of list is even
        if(arraySize%2==0){
            // result is the parsed double value of the middle index and middle index -1 all divided by 2
            medianResult=Double.valueOf(newArray.get(arraySize/2)+newArray.get(arraySize/2-1))/2;
        }
        //if size of list is odd
        else {
            //  result is the middle value in list
            medianResult = newArray.get(arraySize/2);
        }
        
        return medianResult;
    }
}
