package lotto.domain;

import lotto.util.LottoNumberGenerator;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Lottos {
    private static final int PER_MONEY = 1000;
    private final List<Lotto> lottos = new ArrayList<>();

    public void addLotto(Lotto lotto) {
        this.lottos.add(lotto);
    }

    public List<Lotto> makeLotto(int money) {
        int totalAmount = money / PER_MONEY;
        for (int i = 0; i < totalAmount; i++) {
            addLotto(new Lotto(LottoNumberGenerator.makeLotto()));
        }

        return this.lottos;
    }

    public List<Rank> matchOfRanks(WinningLotto winningLotto) {
        return lottos.stream()
                .map(winningLotto::match)
                .collect(Collectors.toList());
    }

    public Map<Rank, List<Rank>> groupByMatchRanks(WinningLotto winningLotto) {
        return matchOfRanks(winningLotto).stream()
                .filter(r -> r != Rank.MISS)
                .sorted(Comparator.comparingLong(Rank::getCountOfMatch))
                .collect(groupingBy(rank -> rank, Collectors.toList()));
    }

    public double calculateProfit(Map<Rank, List<Rank>> result, int money) {
        double profit = result.values()
                .stream()
                .flatMap(List::stream)
                .mapToLong(Rank::getWinningMoney)
                .sum();
        double profitRate = profit / money;

        return (double) (int)(profitRate*100) / 100.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos lottos1 = (Lottos) o;
        return Objects.equals(lottos, lottos1.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
