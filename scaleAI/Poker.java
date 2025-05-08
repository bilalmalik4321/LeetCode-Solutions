package scaleAI;

import java.util.List;

public class Poker {
    public enum Suit {
        HEARTS, SPADES, DIAMONDS, CLUBS;
    }

    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;
    }

    public enum HandRank {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH,
        ROYAL_FLUSH;
    }
    
    // card class
    public class Card{
        private Suit suit;
        private Rank rank;
        private boolean isJoker;

        Card(boolean isJoker){
            this.isJoker = isJoker;
            this.suit = null;
            this.rank = null;
        }

        Card(Suit suit, Rank rank){
            this.suit = suit;
            this.rank = rank;
            this.isJoker = false;
        }
        
        public boolean getIsJoker(){ return this.isJoker; }

        public Suit getSuit(){ return this.suit; }

        public Rank getRank(){ return this.rank; }
    }

    // poker hand
    public class PokerHand{
        private List<Card> cards;

        public PokerHand(List<Card> cards){
            if(cards.size() > 5) throw new IllegalArgumentException("Too many cards!");

            this.cards = cards;
        }
    }

    public HandRank evaluate(){
        if(isRoyalFlush()) return HandRank.ROYAL_FLUSH;
        if(isStraight() && isFlush()) return HandRank.STRAIGHT_FLUSH;
        if(isNoOfAKind(4)) return HandRank.FOUR_OF_A_KIND;
        if(isFullHouse()) return HandRank.FULL_HOUSE;
        if(isFlush()) return HandRank.FLUSH;
        if(isStraight()) return HandRank.STRAIGHT;
        if(isNoOfAKind(3)) return HandRank.THREE_OF_A_KIND;
        if(isTwoPair()) return HandRank.TWO_PAIR;
        if(isNoOfAKind(2)) return HandRank.ONE_PAIR;
        return HandRank.HIGH_CARD;
    }

    private boolean isRoyalFlush(){
        //if is 10, j, q, k, a of the same suit return true;
        return false;
    }

    private boolean isStraight(){
        List<Integer> values = cards.stream()
            .filter(c -> !c.isJoker())
            .map(c -> c.getRank().ordinal())
            .sorted()
            .toList();

        
    }

    private boolean noOfAKind(){
        //if is 10, j, q, k, a of the same suit return true;
        return false;
    }
}
