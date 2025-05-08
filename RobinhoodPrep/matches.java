package RobinhoodPrep;

import java.util.*;

/*
 * 
 * 
 * A trade is defined as a fixed-width string containing the 4 properties given below separated by commas:

Symbol (4 alphabetical characters, left-padded by spaces)
Type (either "B" or "S" for buy or sell)
Quantity (4 digits, left-padded by zeros)
ID (6 alphanumeric characters)
e.g.
"AAPL,B,0100,ABC123"

which represents a trade of a buy of 100 shares of AAPL with ID "ABC123"

Given two lists of trades - called "house" and "street" trades, write code to filter out groups of matches between trades and return a list of unmatched house and street trades sorted alphabetically. There are many ways to match trades, the first and most important way is an exact match (Tests 1-5):

An exact match is a house_trade+street_trade pair with identical symbol, type, quantity, and ID
Note: Trades are distinct but not unique

For example, given the following input:

// house_trades:
[
"AAPL,B,0100,ABC123",
"AAPL,B,0100,ABC123",
"GOOG,S,0050,CDC333"
]

// street_trades:
[
" FB,B,0100,GBGGGG",
"AAPL,B,0100,ABC123"
]

We would expect the following output:

[
" FB,B,0100,GBGGGG",
"AAPL,B,0100,ABC123",
"GOOG,S,0050,CDC333"
]

Because the first (or second) house trade and second street trade form an exact match, leaving behind three unmatched trades.

Follow-up 1 (Test 6,7,8,9): A "fuzzy" match is a house_trade+street_trade pair with identical symbol, type, and quantity ignoring ID. Prioritize exact matches over fuzzy matches. Prioritize matching the earliest alphabetical house trade with the earliest alphabetical street trade in case of ties.

Follow-up 2: (Test 10) An offsetting match is a house_trade+house_trade or street_trade+street_trade pair where the symbol and quantity of both trades are the same, but the type is different (one is a buy and one is a sell). Prioritize exact and fuzzy matches over offsetting matches. Prioritize matching the earliest alphabetical buy with the earliest alphabetical sell.

[execution time limit] 4 seconds (py3)

[input] array.string house_trades

[input] array.string street_trades

[output] array.string

A list of unmatched trades both house and street, ordered alphabetically
Some Testcases:

TEST1
Input:

house_trades:
["AAPL,B,0100,ABC123", 
 "GOOG,S,0050,CDC333"]
street_trades:
["  FB,B,0100,GBGGGG", 
 "AAPL,B,0100,ABC123"]
Expected Output:

["  FB,B,0100,GBGGGG", 
 "GOOG,S,0050,CDC333"]

TEST2
Input:

house_trades:
["AAPL,S,0010,ZYX444", 
 "AAPL,S,0010,ZYX444", 
 "AAPL,B,0010,ABC123", 
 "GOOG,S,0050,GHG545"]
street_trades:
["GOOG,S,0050,GHG545", 
 "AAPL,S,0010,ZYX444", 
 "AAPL,B,0010,TTT222"]
Expected Output:

["AAPL,S,0010,ZYX444"]
TESTS2
Input:

house_trades:
["AAPL,B,0010,ABC123", 
 "AAPL,S,0015,ZYX444", 
 "AAPL,S,0015,ZYX444", 
 "GOOG,S,0050,GHG545"]
street_trades:
["GOOG,S,0050,GHG545", 
 "AAPL,S,0015,ZYX444", 
 "AAPL,B,0500,TTT222"]
Expected Output:

["AAPL,B,0010,ABC123", 
 "AAPL,B,0500,TTT222", 
 "AAPL,S,0015,ZYX444"]
TEST3
Input:

house_trades:
["AAPL,B,0100,ABC123", 
 "AAPL,B,0100,ABC123", 
 "AAPL,B,0100,ABC123", 
 "GOOG,S,0050,CDC333"]
street_trades:
["  FB,B,0100,GBGGGG", 
 "AAPL,B,0100,ABC123"]
Expected Output:

["  FB,B,0100,GBGGGG", 
 "AAPL,B,0100,ABC123", 
 "AAPL,B,0100,ABC123", 
 "GOOG,S,0050,CDC333"]
 */


public class matches {
    matches(List<String> houseTrades, List<String> streetTrades){
        Map<String,Integer> streetSet = new HashMap();
        Map<String,Integer> houseSet = new HashMap();

        // removes all exact matches
        for(String house : houseTrades){
            houseSet.put(house, houseSet.getOrDefault(house, 0)+1);
        }

        for(String street : streetTrades){
            if(houseSet.containsKey(street)){
                houseSet.put(street, houseSet.get(street) - 1);
                if(houseSet.get(street) == 0) houseSet.remove(street);
            }
            else streetSet.put(street, streetSet.getOrDefault(street, 0)+1);
        }

        // fuzz logic
        Map<String,TreeSet<String>> streetFuzz = new HashMap();
        Map<String,TreeSet<String>> houseFuzz = new HashMap();

        for(Map.Entry<String,Integer> entry : houseSet.entrySet()){
            String trade = entry.getKey();
            String[] vals = trade.split(",");
            
            String fuzzId = "";
            for(int i=0; i<vals.length-1; i++){
                fuzzId += vals[i] + ",";
            }
            TreeSet<String> lst = streetFuzz.getOrDefault(fuzzId, new TreeSet());
            lst.add(vals[3]);
        }

        for(Map.Entry<String,Integer> entry : streetSet.entrySet()){
            String trade = entry.getKey();
            String[] vals = trade.split(",");
            
            String fuzzId = "";
            for(int i=0; i<vals.length-1; i++){
                fuzzId += vals[i] + ",";
            }
            TreeSet<String> lst = houseFuzz.getOrDefault(fuzzId, new TreeSet());
            lst.add(vals[3]);
        }

        for(String fuzzId : streetFuzz.keySet()){
            if(houseFuzz.containsKey(fuzzId)){
                TreeSet<String> tr = houseFuzz.get(fuzzId);
                String id = tr.first();
                String complete = fuzzId+","+id;
                
                if(houseSet.containsKey(complete)){
                    houseSet.put(complete, houseSet.get(complete) - 1);
                    if(houseSet.get(complete) == 0){
                        houseSet.remove(complete);
                        tr.remove(id);
                        if(tr.isEmpty()) houseFuzz.remove(fuzzId);
                    }
                }
            }
        }

        // offset logic
        for(Map.Entry<String, Integer> entry : streetSet.entrySet()){
            String key = entry.getKey();
            String[] vals = key.split(",");
            if(vals[1].equals("b")){
                vals[1] = "s";
                String key2 = String.join(",", vals);
                
                if(streetSet.containsKey(key2)){
                    streetSet.put(key2, streetSet.get(key2) - 1);
                    streetSet.put(key, streetSet.get(key) - 1);

                    if(streetSet.get(key2) == 0) streetSet.remove(key2);
                    if(streetSet.get(key) == 0) streetSet.remove(key);
                }
            }
        }

        for(Map.Entry<String, Integer> entry : houseSet.entrySet()){
            String key = entry.getKey();
            String[] vals = key.split(",");
            if(vals[1].equals("b")){
                vals[1] = "s";
                String key2 = String.join(",", vals);
                
                if(houseSet.containsKey(key2)){
                    houseSet.put(key2, houseSet.get(key2) - 1);
                    houseSet.put(key, houseSet.get(key) - 1);

                    if(houseSet.get(key2) == 0) houseSet.remove(key2);
                    if(houseSet.get(key) == 0) houseSet.remove(key);
                }
            }
        }

        List<String> newHouseTrades = new ArrayList<>();
        List<String> newStreetTrades = new ArrayList<>();

        for(Map.Entry<String, Integer> entry : houseSet.entrySet()){
            int freq = entry.getValue();
            String trade = entry.getKey();

            while(freq-- > 0){
                newHouseTrades.add(trade);
            }
        }

        for(Map.Entry<String, Integer> entry : streetSet.entrySet()){
            int freq = entry.getValue();
            String trade = entry.getKey();

            while(freq-- > 0){
                newStreetTrades.add(trade);
            }
        }

        System.err.println(newHouseTrades);
        System.err.println(newStreetTrades);
    }
}
