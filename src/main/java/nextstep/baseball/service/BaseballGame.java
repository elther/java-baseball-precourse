package nextstep.baseball.service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BaseballGame {
    private final BaseballGameNumberService baseballGameNumberService;
    private final BaseballGameCheckService baseballGameCheckService;

    public BaseballGame(BaseballGameNumberService baseballGameNumberService, BaseballGameCheckService baseballGameCheckService) {
        this.baseballGameNumberService = baseballGameNumberService;
        this.baseballGameCheckService = baseballGameCheckService;
    }

    public void start(){
        while(true) {
            if (game().equals("END")) {
                System.out.println("3개의숫자를모두맞히셨습니다! 게임종료");
                System.out.println("게임을새로시작하려면1,종료하려면2를입력하세요.");

                if (input().equals("1")) {
                    game();
                }
                return;
            }
        }
    }

    public String game(){
        final List<Integer> player2 = baseballGameNumberService.generateComputerNumber();
        while(true) {
            System.out.print("숫자를 입력해주세요 : ");
            final List<Integer> player1 = baseballGameNumberService.inputUserNumber(input());
            final Map<String, Integer> gameResult = baseballGameCheckService.getGameResult(player1, player2);

            if(gameResult.get("strike") != null && gameResult.get("strike") == 3){
                break;
            }
            System.out.println(resultPrint(gameResult));
        }
        return "END";
    }

    private String input(){
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    private String resultPrint(Map<String, Integer> gameResult){
        String strike = gameResult.get("strike") != null ? gameResult.get("strike") + " 스트라이크" : "";
        String ball = gameResult.get("ball") != null ? gameResult.get("ball") + " 볼" : "";
        return (strike + " " + ball).equals(" ") ? "낫싱" : (strike + " " + ball);
    }
}
