package scaleAI;

import java.util.*;
import java.util.stream.Collectors;

public class PokerEvaluator {
    // ========== ENUMS ==========
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES;
    }

    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
        JACK, QUEEN, KING, ACE;
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

    // ========== CARD ==========
    public static class Card {
        private final Suit suit;
        private final Rank rank;
        private final boolean isJoker;

        public static final Card JOKER = new Card(true);

        public Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
            this.isJoker = false;
        }

        private Card(boolean isJoker) {
            this.suit = null;
            this.rank = null;
            this.isJoker = isJoker;
        }

        public boolean isJoker() {
            return isJoker;
        }

        public Suit getSuit() {
            return suit;
        }

        public Rank getRank() {
            return rank;
        }

        @Override
        public String toString() {
            return isJoker ? "JOKER" : rank + " of " + suit;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Card)) return false;
            Card card = (Card) o;
            return isJoker == card.isJoker &&
                    suit == card.suit &&
                    rank == card.rank;
        }

        @Override
        public int hashCode() {
            return Objects.hash(suit, rank, isJoker);
        }
    }

    // ========== POKER HAND ==========
    public static class PokerHand {
        private final List<Card> cards;
        private static final List<Card> FULL_DECK = generateFullDeck();

        public PokerHand(List<Card> cards) {
            if (cards.size() != 5)
                throw new IllegalArgumentException("Hand must contain 5 cards.");
            this.cards = cards;
        }

        public HandRank evaluate() {
            if (isFlush() && isStraight()) return HandRank.STRAIGHT_FLUSH;
            if (hasNOfAKind(4)) return HandRank.FOUR_OF_A_KIND;
            if (hasFullHouse()) return HandRank.FULL_HOUSE;
            if (isFlush()) return HandRank.FLUSH;
            if (isStraight()) return HandRank.STRAIGHT;
            if (hasNOfAKind(3)) return HandRank.THREE_OF_A_KIND;
            if (hasTwoPair()) return HandRank.TWO_PAIR;
            if (hasNOfAKind(2)) return HandRank.ONE_PAIR;
            return HandRank.HIGH_CARD;
        }

        public HandRank evaluateWithJokers() {
            int jokerCount = (int) cards.stream().filter(Card::isJoker).count();
            if (jokerCount == 0) return evaluate();

            List<Card> nonJokers = cards.stream().filter(c -> !c.isJoker()).toList();
            Set<Card> used = new HashSet<>(nonJokers);

            List<Card> substitutes = FULL_DECK.stream().filter(c -> !used.contains(c)).toList();
            List<List<Card>> jokerCombos = combinations(substitutes, jokerCount);

            HandRank best = HandRank.HIGH_CARD;
            for (List<Card> subs : jokerCombos) {
                List<Card> fullHand = new ArrayList<>(nonJokers);
                fullHand.addAll(subs);
                PokerHand testHand = new PokerHand(fullHand);
                HandRank result = testHand.evaluate();
                if (result.ordinal() > best.ordinal()) {
                    best = result;
                }
            }
            return best;
        }

        private boolean isFlush() {
            Suit suit = cards.get(0).getSuit();
            return cards.stream().allMatch(c -> !c.isJoker() && c.getSuit() == suit);
        }

        private boolean isStraight() {
            List<Integer> values = cards.stream()
                .filter(c -> !c.isJoker())
                .map(c -> c.getRank().ordinal())
                .sorted()
                .toList();

            // Handle Ace-low straight (A, 2, 3, 4, 5)
            if (values.equals(List.of(0, 1, 2, 3, 12))) return true;

            for (int i = 1; i < values.size(); i++) {
                if (values.get(i) - values.get(i - 1) != 1) return false;
            }
            return true;
        }

        private boolean hasFullHouse() {
            Map<Rank, Long> freq = getRankFrequencies();
            return freq.containsValue(3L) && freq.containsValue(2L);
        }

        private boolean hasTwoPair() {
            return getRankFrequencies().values().stream().filter(v -> v == 2).count() == 2;
        }

        private boolean hasNOfAKind(int n) {
            return getRankFrequencies().containsValue((long) n);
        }

        private Map<Rank, Long> getRankFrequencies() {
            return cards.stream()
                    .filter(c -> !c.isJoker())
                    .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        }

        private static List<Card> generateFullDeck() {
            List<Card> deck = new ArrayList<>();
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    deck.add(new Card(suit, rank));
                }
            }
            return deck;
        }

        private static <T> List<List<T>> combinations(List<T> list, int k) {
            List<List<T>> result = new ArrayList<>();
            backtrack(list, k, 0, new ArrayList<>(), result);
            return result;
        }

        private static <T> void backtrack(List<T> list, int k, int index, List<T> current, List<List<T>> result) {
            if (current.size() == k) {
                result.add(new ArrayList<>(current));
                return;
            }

            for (int i = index; i < list.size(); i++) {
                current.add(list.get(i));
                backtrack(list, k, i + 1, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    // ========== MAIN ==========
    public static void main(String[] args) {
        List<Card> handWithJoker = List.of(
            new Card(Suit.SPADES, Rank.TEN),
            new Card(Suit.SPADES, Rank.JACK),
            new Card(Suit.SPADES, Rank.QUEEN),
            new Card(Suit.SPADES, Rank.KING),
            Card.JOKER
        );

        PokerHand hand = new PokerHand(handWithJoker);
        System.out.println("Best possible hand with Joker: " + hand.evaluateWithJokers());

        List<Card> regularHand = List.of(
            new Card(Suit.HEARTS, Rank.TWO),
            new Card(Suit.HEARTS, Rank.FOUR),
            new Card(Suit.HEARTS, Rank.SIX),
            new Card(Suit.HEARTS, Rank.EIGHT),
            new Card(Suit.HEARTS, Rank.TEN)
        );

        PokerHand flushHand = new PokerHand(regularHand);
        System.out.println("Regular hand: " + flushHand.evaluate());
    }
}

