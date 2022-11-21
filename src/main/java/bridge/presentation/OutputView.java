package bridge.presentation;

import bridge.domain.Result;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public static void printMap(Result result) {
        List<String> bridge = result.getBridge();
        boolean survive = result.isSurvive();

        StringBuilder upperBridgeBuilder = new StringBuilder();
        StringBuilder lowerBridgeBuilder = new StringBuilder();
        for (int i = 0; i < bridge.size() - 1; i++) {
            if (bridge.get(i).equals("U")) {
                upperBridgeBuilder.append(" O ").append("|");
                lowerBridgeBuilder.append("   ").append("|");
            }

            if (bridge.get(i).equals("D")) {
                upperBridgeBuilder.append("   ").append("|");
                lowerBridgeBuilder.append(" O ").append("|");
            }
        }

        if (survive) {
            // 살았다면
            if (bridge.get(bridge.size() - 1).equals("U")) {
                upperBridgeBuilder.append(" O ");
                lowerBridgeBuilder.append("   ");
            } else {
                upperBridgeBuilder.append("   ");
                lowerBridgeBuilder.append(" O ");
            }
        } else {
            if (bridge.get(bridge.size() - 1).equals("U")) {
                upperBridgeBuilder.append("   ");
                lowerBridgeBuilder.append(" X ");
            } else {
                upperBridgeBuilder.append(" X ");
                lowerBridgeBuilder.append("   ");
            }
        }

        System.out.println("[" + upperBridgeBuilder + "]");
        System.out.println("[" + lowerBridgeBuilder + "]");
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public static void printResult(Result result) {
        System.out.println(Message.OUTRO);
        System.out.println(Message.OUTPUT_GAME_RESULT + getGameResultToString(result));
        System.out.println(Message.OUTPUT_GAME_COUNT + result.getGameCount());
    }

    private static String getGameResultToString(Result result) {
        String gameResult = "실패";
        if (result.isVictory()) {
            gameResult = "성공";
        }
        return gameResult;
    }
}
