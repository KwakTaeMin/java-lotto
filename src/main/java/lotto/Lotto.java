package lotto;

import java.util.Collections;
import java.util.List;

public class Lotto {

	public static final int LOTTO_PRICE = 1000;
	public static final int LOTTO_NUMBER_SIZE = 6;

	private final List<LottoNumber> numbers;

	public Lotto(final List<LottoNumber> numbers) {
		validate(numbers);
		this.numbers = numbers;
	}

	public List<LottoNumber> lottoNumbers() {
		return this.numbers;
	}

	//todo : indent 2 streams
	public int matchesCount(Lotto lotto) {
		int matchesCount = 0;
		for (LottoNumber lottoNumber : lotto.lottoNumbers()) {
			if (containNumber(lottoNumber))
				matchesCount++;
		}
		return matchesCount;
	}

	@Override
	public String toString() {
		return this.numbers.toString();
	}

	@Override
	public boolean equals(Object lotto) {
		if (lotto instanceof Lotto) {
			Lotto anotherLotto = (Lotto)lotto;
			Collections.sort(this.numbers);
			Collections.sort(anotherLotto.lottoNumbers());
			return this.numbers == anotherLotto.lottoNumbers();
		}
		return false;
	}

	private boolean containNumber(LottoNumber number) {
		return this.numbers.contains(number);
	}

	private void validate(List<LottoNumber> numbers) {
		if (numbers.size() > LOTTO_NUMBER_SIZE) {
			throw new IllegalArgumentException("6개 보다 크다");
		}
	}
}
