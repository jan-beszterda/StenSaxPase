package stensaxpase;

public class Result {

    private int matchId;
    private Figure playersFigure;
    private Figure computersFigure;
    private String resultText;


    public Result(int matchId, Figure playersFigure, Figure computersFigure, String resultText) {
        this.matchId = matchId;
        this.playersFigure = playersFigure;
        this.computersFigure = computersFigure;
        this.resultText = resultText;
    }

    public int getMatchId() {
        return matchId;
    }

    public Figure getPlayersFigure() {
        return playersFigure;
    }

    public Figure getComputersFigure() {
        return computersFigure;
    }

    public String getResultText() {
        return resultText;
    }
}
