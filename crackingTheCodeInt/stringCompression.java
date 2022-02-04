package crackingTheCodeInt;

/*
aabcccccaaa = a2b1c5a3
*/

public class stringCompression {

    public static void main(String[] args){
        String test1 = "aabcccccaaa";
        System.out.println( solution(test1) );

        String test2 = "abcdef";
        System.out.println( solution(test2) );

        String test3 = "aabb";
        System.out.println( solution(test3) );

        String test4 = "a";
        System.out.println( solution(test4) );

        String test5 = "";
        System.out.println( solution(test5) );
    }

    public static String solution(String input){
        String compressed = "";

        int counter = 0;

        for(int i=0; i<input.length(); i++){
            if( i>0 && input.charAt(i)!=input.charAt(i-1) ){
                compressed += Character.toString( input.charAt(i-1) ) + Integer.toString(counter);
                counter = 0;
            }
            
            if( i==input.length()-1 ){
                compressed += Character.toString( input.charAt(i) ) + Integer.toString(counter+1);
            }

            counter++;
        }

        return compressed.length() < input.length() ? compressed : input;
    }    
}
