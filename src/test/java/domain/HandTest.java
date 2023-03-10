package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class HandTest extends AbstractTestFixture {

    @Test
    @DisplayName("카드를 추가할 수 있다")
    void test_add_card() {
        var card = createCard(Letter.ACE);
        var hand = new Hand();

        assertThat(hand.hit(card).cards()).containsExactly(card);
    }

    @ParameterizedTest(name = "점수를 계산한다")
    @CsvSource({"8,K,18", "10,5,15", "SPADE-A,HEART-A,12"})
    void test_score(String letter1, String letter2, int score) {
        var hand = new Hand(createCards(letter1, letter2));

        assertThat(hand.score()).isEqualTo(new Score(score));
    }

    static Stream<Arguments> test_score_with_a() {
        return Stream.of(
                Arguments.of(21, createCards("J", "10", "A")),
                Arguments.of(21, createCards("7", "3", "A")),
                Arguments.of(14, createCards("SPADE-A", "HEART-A", "DIAMOND-A", "CLOVER-A"))
        );
    }

    @ParameterizedTest(name = "A는 플레이어에게 유리하게 계산한다")
    @MethodSource
    void test_score_with_a(int score, List<Card> cards) {
        var hand = new Hand(cards);

        assertThat(hand.score()).isEqualTo(new Score(score));
    }

    static Stream<Arguments> test_is_winner_against_other() {
        return Stream.of(
                Arguments.of(createCards("K", "10", "A"), createCards("J", "10"), true),
                Arguments.of(createCards("K", "10", "A"), createCards("K", "10", "Q"), true),
                Arguments.of(createCards("K", "10"), createCards("K", "10", "A"), false),
                Arguments.of(createCards("K", "10", "J"), createCards("K", "10", "Q"), false),
                Arguments.of(createCards("K", "10", "Q"), createCards("K", "10", "A"), false),
                Arguments.of(createCards("K", "10", "A"), createCards("K", "10", "A"), false)
        );
    }

    @ParameterizedTest(name = "다른 패와 비교해 승자인지 알 수 있다")
    @MethodSource
    void test_is_winner_against_other(List<Card> cards, List<Card> otherCards, boolean isWinner) {
        var hand = new Hand(cards);
        var other = new Hand(otherCards);

        assertThat(hand.isWinnerAgainst(other)).isEqualTo(isWinner);
    }

    static Stream<Arguments> test_is_draw_against_other() {
        return Stream.of(
                Arguments.of(createCards("K", "10", "A"), createCards("K", "10", "A"), true),
                Arguments.of(createCards("K", "10", "J"), createCards("K", "10", "Q"), true),
                Arguments.of(createCards("K", "10", "A"), createCards("K", "10", "Q"), false),
                Arguments.of(createCards("K", "10", "Q"), createCards("K", "10", "A"), false),
                Arguments.of(createCards("K", "10", "A"), createCards("J", "10"), false),
                Arguments.of(createCards("K", "10"), createCards("K", "10", "A"), false)
        );
    }

    @ParameterizedTest(name = "다른 패와 비교해 무승부인지 알 수 있다")
    @MethodSource
    void test_is_draw_against_other(List<Card> cards, List<Card> otherCards, boolean isDraw) {
        var hand = new Hand(cards);
        var other = new Hand(otherCards);

        assertThat(hand.isDrawAgainst(other)).isEqualTo(isDraw);
    }
}
