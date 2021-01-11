package lotto.view;

import lotto.domain.*;

import java.util.Scanner;
import java.util.stream.Collectors;

public class OutputView {
    public static final String PRICE_INPUT_TEXT = "구입금액을 입력해 주세요.";
    public static final String SELF_LOTTO_COUNT_TEXT = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String SELF_LOTTO_NUMBER_INPUT_TEXT = "수동으로 구매할 번호를 입력해 주세요.";
    public static final String BUY_SUCCESS_TEXT = "수동으로 %d개, 자동으로 %d개를 구매했습니다.\n";
    public static final String NUMBER_INPUT_TEXT = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String BONUS_INPUT_TEXT = "보너스 볼을 입력해 주세요.";
    public static final String STATISTICS_LINE_TEXT = "당첨 통계\n---------";
    public static final String ALL_PROFIT_TEXT = "총 수익률은 %d%% 입니다.\n";
    public static final String RANK_FIFTH_TEXT = "%d개 일치 (%d원)- %d개\n";
    public static final String RANK_FOURTH_TEXT = "%d개 일치 (%d원)- %d개\n";
    public static final String RANK_THIRD_TEXT = "%d개 일치 (%d원)- %d개\n";
    public static final String RANK_SECOND_TEXT = "%d개 일치, 보너스 볼 일치(%d원)- %d개\n";
    public static final String RANK_FIRST_TEXT = "%d개 일치 (%d원)- %d개\n";
    public static final String JOIN_DELIMITER = ", ";
    public static final String CLOSE_BRACKET = "]";
    public static final String OPEN_BRACKET = "[";

    public static void printMoneyInputGuide() {
        System.out.println(PRICE_INPUT_TEXT);
    }

    public static void printSelfLottoCountInputGuide() {
        System.out.println(SELF_LOTTO_COUNT_TEXT);
    }

    public static void printSelfLottoNumberInputGuide() {
        System.out.println(SELF_LOTTO_NUMBER_INPUT_TEXT);
    }

    public static void printLottoCount(LottoKind lottoKind) {
        System.out.printf(BUY_SUCCESS_TEXT, lottoKind.getSelfLottoCount(), lottoKind.getRandomLottoCount());
    }

    public static void printLottos(Lottos lottos) {
        LottosDto lottosDto = lottos.getLottosData();
        for (LottoDto lottoDto : lottosDto.getLottosDto()) {
            System.out.println(getLottoText(lottoDto));
        }
    }

    private static String getLottoText(LottoDto lottoDto) {
        return OPEN_BRACKET + lottoDto.getLottoDto().stream().map(LottoNumber::getNumber)
                .map(lottoNumber -> Integer.toString(lottoNumber))
                .collect(Collectors.joining(JOIN_DELIMITER)) + CLOSE_BRACKET;
    }

    public static void printWinningNumberInputGuide() {
        System.out.println(NUMBER_INPUT_TEXT);
    }

    public static void printBonusNumberInputGuide() {
        System.out.println(BONUS_INPUT_TEXT);
    }

    public static void printStatistics(Statistics statistics, int profitRate) {
        System.out.println(STATISTICS_LINE_TEXT);
        printRaffleResult(statistics);
        System.out.printf(ALL_PROFIT_TEXT, profitRate);
    }

    private static void printRaffleResult(Statistics statistics) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(RANK_FIFTH_TEXT, Rank.FIFTH.getMatchCount(), Rank.FIFTH.getReward(), statistics.getCountOfRank(Rank.FIFTH)));
        sb.append(String.format(RANK_FOURTH_TEXT, Rank.FOURTH.getMatchCount(), Rank.FOURTH.getReward(), statistics.getCountOfRank(Rank.FOURTH)));
        sb.append(String.format(RANK_THIRD_TEXT, Rank.THIRD.getMatchCount(), Rank.THIRD.getReward(), statistics.getCountOfRank(Rank.THIRD)));
        sb.append(String.format(RANK_SECOND_TEXT, Rank.SECOND.getMatchCount(), Rank.SECOND.getReward(), statistics.getCountOfRank(Rank.SECOND)));
        sb.append(String.format(RANK_FIRST_TEXT, Rank.FIRST.getMatchCount(), Rank.FIRST.getReward(), statistics.getCountOfRank(Rank.FIRST)));
        System.out.println(sb);
    }
}
