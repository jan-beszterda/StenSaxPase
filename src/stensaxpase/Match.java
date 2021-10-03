package stensaxpase;

import java.util.Random;

/**
 * Class responsible for playing a match. Allows player to choose their figure, chooses at random a figure
 * for the computer, evaluates and creates the match result.
 * @author Jan Beszterda
 */
public class Match {

    private Figure[] Figures = {new Figure("STEN", "SAX", "PÅSE"),
            new Figure("SAX", "PÅSE", "STEN"),
            new Figure("PÅSE", "STEN", "SAX")};
    private Player currentPlayer;
    private int id;
    private static int counter = 1;
    private Result result;

    /**
     * Constructor for the Match object. Initialises the match ID with the next integer number starting from 1.
     * @param currentPlayer Current player playing the match passed from the game.
     */
    public Match(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.id = counter;
        counter++;
    }

    /**
     * Method calls the menu to display the possible choices to the player and calls the match evaluation method
     * forwarding it the computer's and player's choices.
     */
    public void showMatchMenu() {
        int choice = Menus.displayMatchMenuOptions();
        if (choice < 1 || choice > 3) {
            System.out.println();
            System.out.println("Felaktigt val!");
            showMatchMenu();
        } else {
            compareChoices(chooseComputersFigure(), Figures[choice - 1]);
            System.out.println();
        }
    }

    /**
     * Method chooses at random one of the Figures used in the game for the computer opponent.
     * @return One of the three figures: Sten, Sax or Påse.
     */
    private Figure chooseComputersFigure() {
        Random r = new Random();
        return Figures[r.nextInt(3)];
    }

    /**
     * Method compares player's and computer's match figures and evaluates the game result which is added
     * to the player's results list.
     * @param computersChoice Figure chosen at random for the computer.
     * @param playersChoice Figure chosen by the player.
     */
    private void compareChoices(Figure computersChoice, Figure playersChoice) {
        System.out.println();
        if (playersChoice.getName().equalsIgnoreCase(computersChoice.getLooseTo())) {
            this.result = new Result(this.id, playersChoice, computersChoice, "Du vann!");
            this.currentPlayer.getResultHistory().add(this.result);
            System.out.format("Du vann! Ditt val var: %1$s, datorns val var: %2$s", playersChoice.getName(), computersChoice.getName());
        } else if (playersChoice.getName().equalsIgnoreCase(computersChoice.getWinOver())) {
            this.result = new Result(this.id, playersChoice, computersChoice, "Du förlorade!");
            this.currentPlayer.getResultHistory().add(this.result);
            System.out.format("Du förlorade! Ditt val var: %1$s, datorns val var: %2$s", playersChoice.getName(), computersChoice.getName());
        } else {
            this.result = new Result(this.id, playersChoice, computersChoice, "Oavgjort!");
            this.currentPlayer.getResultHistory().add(this.result);
            System.out.format("Oavgjort! Ditt val var: %1$s, datorns val var: %2$s", playersChoice.getName(), computersChoice.getName());
        }
    }
}
