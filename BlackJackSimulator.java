package BlackJack;

public class BlackJackSimulator {
    private Deck deck;
    private Player player;
    private Player dealer;

    public BlackJackSimulator() {
        deck = new Deck();
        deck.shuffle(); // 洗牌
        player = new Player();
        dealer = new DealerPlayer(player);
        player.hit(deck);
        dealer.hit(deck);
        player.hit(deck);
        dealer.hit(deck);
        printStatus();
    }

    private void printStatus() {
        player.printStatus();
        dealer.printStatus();
    }

    public void simulator() {
        if (player.isBlackJack()) {
            if (dealer.isBlackJack()) {
                System.out.println("-- Draw --");
            } else {
                System.out.println("-- Player black jack wins --");
            }
            return;
        }
        if (dealer.isBlackJack()) {
            System.out.println("-- Dealer black jack wins --");
            printStatus();
            return;
        }
        System.out.println("-- Player round --");
        while (player.action(this.deck) == Action.Hit) {
            if (player.isBusted()) {
                System.out.println("-- Dealer wins --");
                printStatus();
                return;
            }
        }
        System.out.println("-- Dealer round --");
        while (dealer.action(this.deck) == Action.Hit) {
            if (dealer.isBusted()) {
                System.out.println("-- Player wins --");
                printStatus();
                return;
            }
        }
        if (player.score() > dealer.score()) {
            System.out.println("-- Player wins --");
        } else if (player.score() < dealer.score()) {
            System.out.println("-- Dealer wins --");
        } else {
            System.out.println("-- Draw --");
        }
        printStatus();
    }

    public static void main(String[] args) {
        BlackJackSimulator blackJackSimulator = new BlackJackSimulator();
        blackJackSimulator.simulator();
    }
}
