/*
Questions I was asked during interview
 */
// given an array, return any index, where the sum to the right of the index, matches the sum to the left


class Solution{

    public int getAvgIndex(int[] A){

        int index = A.length/2;

        int leftSum = 0;
        for(int i=0;i<index;i++){
            leftSum += A[i];
        }

        int rightSum = 0;
        for(int i=index+1;i<A.length;i++){
            rightSum += A[i];
        }

        if(leftSum==rightSum)return index;
        
        int checkRightLeftSum = leftSum;
        int checkRightRightSum = rightSum;

        for(int i=index+1;i<A.length;i++){
            checkRightLeftSum += A[i-1];
            checkRightRightSum -= A[i];

            if(checkRightLeftSum == checkRightRightSum)return i;
        }

        int checkLeftLeftSum = leftSum;
        int checkLeftRightSum = rightSum;

        for(int i=index-1;i>=0;i--){
            checkLeftLeftSum -= A[i];
            checkLeftRightSum += A[i+1];

            if(checkLeftLeftSum == checkLeftRightSum)return i;
        }

        return -1;
    }

}

/*
{2,2,3,4}

index = 1

leftSum = 2
rightSum = 7

checkRightLeftSum = 4
checkRightRightSum = 4
i = 2

 */



//given a tree, return the longest paths, number of nodes




