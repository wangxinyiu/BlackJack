package BlackJack;

import java.util.Scanner;

public class DealerPlayer extends Player {

    private static final int DEALER_MUST_HIT = 17;
    private Player player;
    private Scanner scanner = new Scanner(System.in);

    public DealerPlayer(Player player) {
        this.player = player;
    }

/*    @Override
    public Action action(Deck d) {
        if (score() < DEALER_MUST_HIT) {
            System.out.println("Dealer must hit under 17");
            printStatus();
            hit(d);
            return Action.Hit;
        } else {
            if (score() > this.player.score()) {
                stand();
                return Action.Stand;
            } else {
                return super.action(d);
            }
        }
    }*/
    @Override
    public Action action (Deck d) {
        if (score() < DEALER_MUST_HIT) {
            System.out.println("Dealer must hit under 17");
            hit(d);
            printStatus();
            return Action.Hit;
        } else {
            String s = scanner.next();
            if (s.equals("stand")) {
                stand();
                printStatus();
                return Action.Stand;
            } else {
                hit(d);
                printStatus();
                return Action.Hit;
            }
        }
    }


    @Override
    public void printStatus() {
        System.out.println("Dealer Status:");
        System.out.println("score: " + score());
        for (Card card : super.cards) {
            System.out.println(card.toString());
        }
    }
}
