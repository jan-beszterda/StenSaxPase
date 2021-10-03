package stensaxpase;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Menus class holds static methods responsible for displaying various menus with options to the user and passing the
 * choice back to the game or match.
 * @author Jan Beszterda
 */
public class Menus {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Menu with options displayed to the user if no player was previously registered.
     * @return choice made in this menu by user
     */
    public static int displayNoPlayerMenuOptions() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("*    STEN SAX PÅSE    *");
        System.out.println("***********************");
        System.out.println("Aktiv spelare: ingen spelare vald");
        System.out.println("***********************");
        System.out.println("* 1. Skapa ny spelare *");
        System.out.println("* 2. Avsluta spelet   *");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Gör ett val: ");
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception ignore) {
            System.out.println();
            System.out.println("Felaktigt alternativ!");
            choice = Menus.displayNoPlayerMenuOptions();
        }
        return choice;
    }

    /**
     * Menu with options displayed to the user if a player was previously registered.
     * @param currentPlayer player that is currently active in the game
     * @return choice made in this menu by user
     */
    public static int displayRegisteredPlayerMenuOptions(Player currentPlayer) {
        System.out.println();
        System.out.println("***********************");
        System.out.println("*    STEN SAX PÅSE    *");
        System.out.println("***********************");
        System.out.format("Aktiv spelare: %1$s\n", currentPlayer.getName());
        System.out.println("***********************");
        System.out.println("* 1. Ny match         *");
        System.out.println("* 2. Historik         *");
        System.out.println("* 3. Välj spelare     *");
        System.out.println("* 4. Skapa ny spelare *");
        System.out.println("* 5. Avsluta spelet   *");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Gör ett val: ");
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println();
            System.out.println("Felaktigt alternativ!");
            choice = Menus.displayRegisteredPlayerMenuOptions(currentPlayer);
        }
        return choice;
    }

    /**
     * Menu with options displayed to the user after a match was completed by the player.
     * @return choice made in this menu by user
     */
    public static int displayAfterMatchMenuOptions() {
        System.out.println();
        System.out.println("*********************");
        System.out.println("*   STEN SAX PÅSE   *");
        System.out.println("*********************");
        System.out.println("* 1. Huvudmeny      *");
        System.out.println("* 2. Spela igen     *");
        System.out.println("* 3. Avsluta spelet *");
        System.out.println("*********************");
        System.out.println();
        System.out.print("Gör ett val: ");
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println();
            System.out.println("Felaktigt alternativ!");
            choice = Menus.displayAfterMatchMenuOptions();
        }
        return choice;
    }

    /**
     * Menu with options displayed to the user when a match is started.
     * @return choice made in this menu by user
     */
    public static int displayMatchMenuOptions() {
        System.out.println();
        System.out.println("**********************");
        System.out.println("*    Gör ditt val    *");
        System.out.println("**********************");
        System.out.println("* 1. Sten            *");
        System.out.println("* 2. Sax             *");
        System.out.println("* 3. Påse            *");
        System.out.println("**********************");
        System.out.println();
        System.out.print("Ditt val: ");
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println();
            System.out.println("Felaktigt alternativ!");
            choice = Menus.displayMatchMenuOptions();
        }
        return choice;
    }

    /**
     * Menu with options displayed to the user when they decide to create a new player.
     * @return name for the new player provided by user
     */
    public static String displayPlayerCreationMenu() {
        String name = "";
        System.out.println();
        System.out.print("Skriv in spelarens namn: ");
        name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Spelarens namn kan inte vara tom!");
            name = Menus.displayPlayerCreationMenu();
        }
        return name;
    }

    /**
     * Menu with options displayed to the user when they decide to select an active player from the list of all
     * registered players.
     * @param players ArrayList of all registered players to choose from
     * @return choice made in this menu by user
     */
    public static int displayPlayerChoiceMenu(ArrayList<Player> players) {
        System.out.println();
        System.out.println("Välj spelare:");
        System.out.println("0. Tillbaka till huvudmenyn");
        for (int i = 0; i < players.size(); i++) {
            System.out.println(i + 1 + ". " + players.get(i).getName());
        }
        System.out.println();
        System.out.print("Gör ett val: ");
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println();
            System.out.println("Felaktigt alternativ!");
            choice = Menus.displayPlayerChoiceMenu(players);
        }
        return choice;
    }
}




