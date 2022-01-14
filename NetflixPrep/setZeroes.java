package NetflixPrep;

class Solution {
    public void setZeroes(int[][] matrix) {
        int[] col = new int[matrix.length];
        int[] row = new int[matrix[0].length];

        for(int k=0; k<matrix.length; k++){
            for(int l=0;l<matrix[0].length;l++){
                if(matrix[k][l]==0){
                    col[k] = -1;
                    row[l] = -1;
                }
            }
        }

        //check all columns
        for(int k=0; k<col.length; k++){
            //if flag caught, turn col to zeroes
            if(col[k]==-1){
                int l=0;
                while(l<matrix[0].length){
                    matrix[k][l] = 0;
                    l++;
                }
            }
        }

        //check all rows
        for(int k=0; k<row.length; k++){
            if(row[k]==-1){
                int l=0;
                //eliminate row if row is -1
                while(l<matrix.length){
                    matrix[l][k] = 0;
                    l++;
                }
            }
        }
        
    }
}