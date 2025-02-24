package bridge.presentation;

import bridge.domain.BridgeMap;
import bridge.domain.Result;

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
        System.out.println(BridgeMap.of(result));
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public static void printResult(Result result) {
        System.out.println(Message.OUTRO);
        System.out.println(BridgeMap.of(result));
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
