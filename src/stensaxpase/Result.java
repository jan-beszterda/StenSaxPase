package stensaxpase;

/**
 * Class provides a template for match results to be stored in the player's results history.
 * @author Jan Beszterda
 */
public class Result {

    private int matchId;
    private Figure playersFigure;
    private Figure computersFigure;
    private String resultText;


    /**
     * Constructor of the Result object initialising it with necessary data generated in the match.
     * @param matchId Match ID from the match that created this result.
     * @param playersFigure Figure chosen in the match by the player.
     * @param computersFigure Figure chosen at random for the computer in the match.
     * @param resultText Textual representation of the match evaluation.
     */
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
