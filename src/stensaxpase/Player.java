package stensaxpase;

import java.util.ArrayList;

/**
 * Class provides a template for the player objects within the game. Each player stores their own results and is able
 * to print them to console.
 *
 * @author Jan Beszterda
 */
public class Player {

    private String name;
    private ArrayList<Result> resultHistory;

    /**
     * Constructor of the player object.
     *
     * @param name Name of the player supplied by the person playing the game.
     */
    public Player(String name) {
        this.name = name;
        resultHistory = new ArrayList<>();
    }

    /**
     * Method iterates over player's results list and prints them to console.
     *
     */
    public void showHistory() {
        System.out.println();
        System.out.println("Spel historik för: " + name);
        if (!resultHistory.isEmpty()){
            for (Result result : resultHistory) {
                System.out.format("Spel nummer: %1$d. %2$s Ditt val var: %3$s, datorns val var: %4$s", result.getMatchId(),
                        result.getResultText(), result.getPlayersFigure().getName(), result.getComputersFigure().getName());
                System.out.println();
            }
        } else {
            System.out.println("Inga matcher spelade hittills!");
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Result> getResultHistory() {
        return resultHistory;
    }
}
