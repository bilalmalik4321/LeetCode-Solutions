class Solution {
    public String customSortString(String order, String s) {
        HashMap<Character,Integer> frequency = new HashMap<>();
        String result = "";
        
        for(int i=0;i<order.length();i++){
            frequency.put(order.charAt(i),0);
        }
        
        for(int i=0;i<s.length();i++){
            if( frequency.containsKey(s.charAt(i)) ){
                frequency.put(s.charAt(i), frequency.get(s.charAt(i))+1 );
            }
            else {
                result += s.charAt(i);
            }
        }
        
        for(int i=0;i<order.length();i++){
            System.out.println(order.charAt(i));
            int freq = frequency.get( order.charAt(i) );
            
            while(freq > 0){
                result = result + order.charAt(i);
                
                freq--;
            }
            
        }
        
        return result;
        
    }
}

/*
order = "cbafg", s = "abcd"
frequency = {
    c:1,
    b:1,
    a:1,
    f:0,
    g:0
}
result = "cbad"

*/

