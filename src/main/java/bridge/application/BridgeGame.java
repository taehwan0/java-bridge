package bridge.application;

import bridge.domain.BridgeMaker;
import bridge.domain.BridgeType;
import bridge.domain.Result;
import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final BridgeMaker bridgeMaker;

    private List<String> bridge;
    private int gameCount;
    private int position;
    private boolean terminate;

    private BridgeGame(BridgeMaker bridgeMaker) {
        this.bridgeMaker = bridgeMaker;
        this.gameCount = 1;
        this.position = 0;
        this.terminate = false;
    }

    public static BridgeGame createInstance(BridgeMaker bridgeMaker) {
        return new BridgeGame(bridgeMaker);
    }

    public List<String> initBridge(int size) {
        bridge = bridgeMaker.makeBridge(size);
        return bridge;
    }


    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public Result move(String command) {
        validateGame();
        validateMoveCommand(command);

        if (bridge.get(position).equals(command)) {
            Result result = Result.of(bridge.subList(0, position + 1), true, isEnd(), gameCount);
            continueGame();
            return result;
        }
        terminateGame();
        return Result.of(bridge.subList(0, position + 1), false, false, gameCount);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        gameCount++;
        position = 0;
        terminate = false;
    }

    public Result exitGame() {
        if (bridge.size() == position) {
            return Result.of(new ArrayList<>(bridge), true, true, gameCount);
        }
        return Result.of(bridge.subList(0, position + 1), false, false, gameCount);
    }

    private void validateGame() {
        if (terminate) {
            throw new IllegalStateException("종료된 게임은 더이상 진행할 수 없습니다.");
        }
    }

    private void validateMoveCommand(String command) {
        List<String> types = BridgeType.getNames();
        if (!types.contains(command)) {
            throw new IllegalArgumentException("유효하지 않은 이동 명령입니다.");
        }
    }

    private boolean isEnd() {
        return bridge.size() == position + 1;
    }

    private void continueGame() {
        if (bridge.size() == position + 1) {
            terminate = true;
        }
        position++; // terminate 가 true 라면 position 의 크기가 성공의 여부로 작용할 수 있다.
    }

    private void terminateGame() {
        terminate = true;
    }
}
