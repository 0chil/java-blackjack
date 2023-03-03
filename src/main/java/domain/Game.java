package domain;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final List<Player> players;
    private final Deck deck;
    private final Dealer dealer;

    public Game(List<Player> players, Deck deck, Dealer dealer) {
        this.players = players;
        this.deck = deck;
        this.dealer = dealer;
    }

    public void dealTwoCards() {
        for (int i = 0; i < 2; i++) {
            dealCards();
        }
    }

    private void dealCards() {
        for (Player player : players) {
            player.addCard(deck.drawCard());
        }

        dealer.addCard(deck.drawCard());
    }

    public Result isWon(int index) {
        return getResult(players.get(index), dealer);
    }

    private Result getResult(Player player, Player other) {
        if (player.getScore() == other.getScore() || (player.isBusted() && other.isBusted())) {
            return Result.DRAW;
        }

        if (!player.isBusted() && (player.getScore() > other.getScore() || other.isBusted())) {
            return Result.WIN;
        }

        return Result.LOSE;
    }

    public List<Result> getDealerResults() {
        List<Result> results = new ArrayList<>();

        for (Player player : players) {
            results.add(getResult(dealer, player));
        }

        return results;
    }

    public void dealAnotherCard(int index) {
        Player player = players.get(index);
        player.addCard(deck.drawCard());
    }

    public boolean canHit(Player player) {
        return player.canHit();
    }

    public void dealAnotherCard() {
        dealer.addCard(deck.drawCard());
    }

    public List<Player> getPlayers() {
        return players;
    }
}
