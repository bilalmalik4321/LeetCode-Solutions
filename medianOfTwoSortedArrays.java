import java.util.ArrayList;

//Runtime: 3 ms, faster than 38.02% of Java online submissions for Median of Two Sorted Arrays.
//Memory Usage: 40 MB, less than 79.46% of Java online submissions for Median of Two Sorted Arrays.

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // initialize counter to keep track of each num list index
        int nums1Counter=0;
        int nums2Counter=0;
        
        // get the length of both arrays placed together        
        int combinedLengths=nums1.length + nums2.length;
        
        // initialize arrayList to place values from both arrays in order        
        ArrayList<Integer> combinedOrderedList= new ArrayList<Integer>(); 

        // while the arrayList is not the size of the combined arrays
        while(combinedOrderedList.size() != combinedLengths){
            // if both counters have not reached the end of the array
            if(nums1Counter < nums1.length && nums2Counter < nums2.length){
                // if the current list1 value is less then current list 2 value
                if(nums1[nums1Counter] < nums2[nums2Counter]){
                // add to combined orderedlist
                combinedOrderedList.add(nums1[nums1Counter]);
                nums1Counter++;
                }
                else if(nums2[nums2Counter] < nums1[nums1Counter]){
                    combinedOrderedList.add(nums2[nums2Counter]);
                    nums2Counter++;
                }
                else if(nums2[nums2Counter] == nums1[nums1Counter]){
                    combinedOrderedList.add(nums2[nums2Counter]);
                    combinedOrderedList.add(nums2[nums2Counter]);
                    nums1Counter++;
                    nums2Counter++;
                }
            }
            // if counter2 has reached the end of array 2
            else if(nums1Counter < nums1.length && nums2Counter == nums2.length){
                // only add the nums1 value to combined orderedlist
                combinedOrderedList.add(nums1[nums1Counter]);
                nums1Counter++;
            }
            // if counter1 has reached the end of array 1
             else if(nums2Counter < nums2.length && nums1Counter == nums1.length){
                //only add the nums2 value to combined orderedlist
                combinedOrderedList.add(nums2[nums2Counter]);
                nums2Counter++;
            }
            
        
        }
        
        // after numbers are put into one arrayList, get the size         
        int arraySize=combinedOrderedList.size();
        double medianResult = 0;
        //if size of list is even
        if(arraySize%2==0){
            // result is the parsed double value of the middle index and middle index -1 all divided by 2
            medianResult=Double.valueOf(combinedOrderedList.get(arraySize/2)+combinedOrderedList.get(arraySize/2-1))/2;
        }
        //if size of list is odd
        else {
            //  result is the middle value in list
            medianResult = combinedOrderedList.get(arraySize/2);
        }
        
        //return value
        return medianResult;
    }
}
