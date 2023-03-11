package domain;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private static final Money BET_MINIMUM = Money.of(100);

    private final Map<User, Money> deposits = new HashMap<>();
    private Money bankProfit = Money.of(0);

    public void bet(User user, Money money) {
        validateMinimum(money);
        deposit(user, money);
    }

    private void validateMinimum(Money money) {
        if (money.isLessThan(BET_MINIMUM)) {
            throw new IllegalArgumentException("100원 이상 배팅해야 합니다");
        }
    }

    public void evaluate(User user, Result result) {
        Money profit = getDepositOf(user).multiply(result.getDividend());
        spend(profit);
        deposit(user, profit);
    }

    public Money withdrawDepositOf(User user) {
        Money deposit = getDepositOf(user);
        clearDepositOf(user);
        return deposit;
    }

    private void spend(Money money) {
        bankProfit = bankProfit.sub(money);
    }

    private void deposit(User user, Money money) {
        Money deposit = getDepositOf(user).add(money);
        deposits.put(user, deposit);
    }

    private void clearDepositOf(User user) {
        deposits.put(user, Money.of(0));
    }

    private Money getDepositOf(User user) {
        return deposits.getOrDefault(user, Money.of(0));
    }

    public Money getProfit() {
        return bankProfit;
    }
}
