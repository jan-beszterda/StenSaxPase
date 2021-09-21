package stensaxpase;

import java.util.ArrayList;

public class Player {

    private final String NAME;
    private ArrayList<Result> resultHistory;

    public Player(String NAME) {
        this.NAME = NAME;
        resultHistory = new ArrayList<>();
    }

    /**
     * Method iterates over player's results list and prints them to console.
     *
     */
    public void showHistory() {
        System.out.println();
        System.out.println("Spel historik för: " + NAME);
        if (!resultHistory.isEmpty()){
            for (Result result : resultHistory) {
                System.out.format("Spel nummer: %1$d. %2$s Ditt val var: %3$s, datorns val var: %4$s", result.getMatchId(),
                        result.getResultText(), result.getPlayersFigure().getNAME(), result.getComputersFigure().getNAME());
                System.out.println();
            }
        } else {
            System.out.println("Inga matcher spelade hittills!");
        }
    }

    public String getName() {
        return NAME;
    }

    public ArrayList<Result> getResultHistory() {
        return resultHistory;
    }
}
