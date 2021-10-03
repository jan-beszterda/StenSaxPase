package stensaxpase;

/**
 * Figure class provides the template for the options (Rock, Paper, Scissors) used within the game.
 * @author Jan Beszterda
 */
public class Figure {

    private String name;
    private String winOver;
    private String looseTo;


    /**
     * Constructor of the game figure object.
     * @param name Figure name.
     * @param winOver Figure name this Figure wins over.
     * @param looseTo Figure name this Figure looses to.
     */
    public Figure(String name, String winOver, String looseTo) {
        this.name = name;
        this.winOver = winOver;
        this.looseTo = looseTo;
    }

    public String getName() {
        return name;
    }

    public String getWinOver() {
        return winOver;
    }

    public String getLooseTo() {
        return looseTo;
    }
}
