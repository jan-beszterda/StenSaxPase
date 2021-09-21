package stensaxpase;

import java.util.Random;

public class Match {


    private Player currentPlayer;
    private int id;
    private static int counter = 1;
    private Result result;

    public Match(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.id = counter;
        counter++;
    }

    /**
     * Method chooses at random one of the Figures used in the game for the computer opponent.
     *
     * @return One of the three figures: Sten, Sax or Påse
     */
    public Figure chooseComputersFigure() {
        Random r = new Random();
        return Game.getFIGURES()[r.nextInt(3)];
    }

    /**
     * Method compares player's and computer's game figures and evaluates the game result which is added
     * to the player's results list
     *
     * @param computersChoice Figure chosen at random for the computer
     * @param playersChoice Figure chosen by the player
     */
    public void compareChoices(Figure computersChoice, Figure playersChoice) {
        System.out.println();
        if (playersChoice.getNAME().equalsIgnoreCase(computersChoice.getWEAKNESS())) {
            this.result = new Result(this.id, playersChoice, computersChoice, "Du vann!");
            this.currentPlayer.getResultHistory().add(this.result);
            System.out.format("Du vann! Ditt val var: %1$s, datorns val var: %2$s", playersChoice.getNAME(), computersChoice.getNAME());
        } else if (playersChoice.getNAME().equalsIgnoreCase(computersChoice.getSTRENGTH())) {
            this.result = new Result(this.id, playersChoice, computersChoice, "Du förlorade!");
            this.currentPlayer.getResultHistory().add(this.result);
            System.out.format("Du förlorade! Ditt val var: %1$s, datorns val var: %2$s", playersChoice.getNAME(), computersChoice.getNAME());
        } else {
            this.result = new Result(this.id, playersChoice, computersChoice, "Oavgjort!");
            this.currentPlayer.getResultHistory().add(this.result);
            System.out.format("Oavgjort! Ditt val var: %1$s, datorns val var: %2$s", playersChoice.getNAME(), computersChoice.getNAME());
        }
    }
}
