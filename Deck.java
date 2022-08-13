package BlackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 牌堆
public class Deck {
    private static final Random random = new Random();
    private final List<Card> cards = new ArrayList<>(); // or Card[]
    private int dealtIndex = 0; // 发到哪了

    // 建造52张牌并放到我的cards里
    public Deck() {
        for (int i = 1; i <= 13; i++) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(i, suit));
            }
        }
    }

    public void shuffle() {
        for (int i = cards.size() - 1; i >= 0; i--) {
            int j = random.nextInt(i + 1);
            // this will return an integer between 0 and i inclusively.
            Card card1 = cards.get(i);
            Card card2 = cards.get(j);
            cards.set(i, card2);
            cards.set(j, card1);
        }
    }

    private int remainingCard() {
        return cards.size() - dealtIndex;
    }

    public Card dealCard() {
        return remainingCard() == 0 ? null : cards.get(dealtIndex++);
    }
}
