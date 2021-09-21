package stensaxpase;

public class Figure {

    private String NAME;
    private String STRENGTH;
    private String WEAKNESS;

    public Figure(String NAME, String WIN_OVER, String LOOSE_TO) {
        this.NAME = NAME;
        this.STRENGTH = WIN_OVER;
        this.WEAKNESS = LOOSE_TO;
    }

    public String getNAME() {
        return NAME;
    }

    public String getSTRENGTH() {
        return STRENGTH;
    }

    public String getWEAKNESS() {
        return WEAKNESS;
    }
}
