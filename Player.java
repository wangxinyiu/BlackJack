package BlackJack;

import java.util.*;

// 玩家
public class Player {
    private static final Random random = new Random();
    // private static final double hitRatio = 0.5;
    Scanner scanner = new Scanner(System.in);
    protected final List<Card> cards = new ArrayList<>();

/*    public Action action(Deck d) {
        // 得到 0 - 1之前的任意一个double值
        if (random.nextDouble() < hitRatio) {
            hit(d);
            return Action.Hit;
        } else {
            stand();
            return Action.Stand;
        }
    }*/
    public Action action (Deck d) {
        String s = scanner.next();
        if (s.equals("hit")) {
            hit(d);
            printStatus();
            System.out.println("hit successful");
            return Action.Hit;
        } else if (s.equals("stand")) {
            stand();
            printStatus();
            System.out.println("stand successful");
            return Action.Stand;
        }
        System.out.println("Please input again");
        return action(d);
    }

/*    public Action action(Deck d) {
        if (scanner.)
    }*/

    public int score() {
        Collections.sort(cards, Collections.reverseOrder()); // sort descending
        int score = 0;
        int i = 0;
        for (; i < cards.size(); i++) {
            Card c = cards.get(i);
            if (c.isFace()) {
                score += 10;
            } else if (!c.isAcr()) {
                score += c.value();
            } else {
                // 因为其实我满足一个BlackJack我最多需要一个1当作11
                break;
            }
        }
        int numAces = cards.size() - i;
        if (numAces == 0) {
            return score <= 21 ? score : 0;
        }
        // we can not have two or more Aces counted as 11.
        int max = numAces + 10;
        int min = numAces;
        if (score + min > 21) {
            return 0;
        } else if (score + max > 21) {
            return score + min;
        } else {
            return score + max;
        }
    }

    public boolean isBusted() {
        return score() == 0;
    }

    public boolean isBlackJack() {
        if (cards.size() != 2) {
            return false;
        }
        return score() == 21;
    }

    public void hit(Deck d) {
        cards.add(d.dealCard());
    }

    public void stand() {

    }

    public void printStatus() {
        System.out.println("Player Status:");
        System.out.println("score: " + score());
        for (Card card : cards) {
            System.out.println(card.toString());
        }
    }
}
