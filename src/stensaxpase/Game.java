package stensaxpase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class holding the main game logic, players list, current player, current match and responsible for showing the menus
 * and providing other main functionality.
 *
 * @author Jan Beszterda
 */
public class Game {

    private ArrayList<Player> players;
    private Player currentPlayer;
    private Match currentMatch;
    private Scanner scanner;


    /**
     * Constructor of the central Game object.
     *
     * @param scanner Scanner for user input passed from the main method launching the application.
     */
    public Game(Scanner scanner) {
        this.scanner = scanner;
        this.currentMatch = null;
        this.currentPlayer = null;
        this.players = new ArrayList<>();
    }

    /**
     * Initial method to show the main menu, deciding which one to show and calling respective method.
     */
    public void showMenu() {
        if (currentPlayer == null) {
            showNoPlayerMainMenu();
        } else {
            showPlayerMainMenu();
        }
    }

    /**
     * Method showing the menu with options if no player was registered.
     */
    private void showNoPlayerMainMenu() {
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
        int choice = 0;
        try {
            choice = Integer.parseInt(this.scanner.nextLine());
        } catch (Exception e) {
            System.out.println();
            System.out.println("Felaktigt alternativ!");
            showNoPlayerMainMenu();
        }
        switch (choice) {
            case 1:
                createPlayer();
                showMenu();
                break;
            case 2:
                exitGame();
                break;
            default:
                System.out.println();
                System.out.println("Felaktigt val!");
                showNoPlayerMainMenu();
                break;
        }
    }


    /**
     * Method showing the menu with options if there's an active player.
     */
    private void showPlayerMainMenu() {
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
        int choice = 0;
        try {
            choice = Integer.parseInt(this.scanner.nextLine());
        } catch (Exception e) {
            System.out.println();
            System.out.println("Felaktigt alternativ!");
            showPlayerMainMenu();
        }
        switch (choice) {
            case 1:
                currentMatch = new Match(currentPlayer, scanner);
                currentMatch.showPlayerChoiceMenu();
                showAfterMatchMenu();
                break;
            case 2:
                currentPlayer.showHistory();
                showMenu();
                break;
            case 3:
                choosePlayer();
                showMenu();
                break;
            case 4:
                createPlayer();
                showMenu();
                break;
            case 5:
                exitGame();
                break;
            default:
                System.out.println();
                System.out.println("Felaktigt val!");
                showPlayerMainMenu();
                break;
        }
    }



    /**
     * Method showing the menu to the active player when the current match was completed.
     */
    private void showAfterMatchMenu() {
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
        int choice = 0;
        try {
            choice = Integer.parseInt(this.scanner.nextLine());
        } catch (Exception e) {
            System.out.println();
            System.out.println("Felaktigt alternativ!");
            showAfterMatchMenu();
        }
        switch (choice) {
            case 1:
                showMenu();
                break;
            case 2:
                currentMatch = new Match(currentPlayer, scanner);
                currentMatch.showPlayerChoiceMenu();
                showAfterMatchMenu();
                break;
            case 3:
                exitGame();
                break;
            default:
                System.out.println();
                System.out.println("Felaktigt val!");
                showAfterMatchMenu();
                break;
        }
    }

    /**
     * Method allowing the user of the application to create a player. It sets the newly created player as active.
     */
    private void createPlayer() {
        String name = "";
        while (name.isEmpty()) {
            System.out.println();
            System.out.print("Skriv in spelarens namn: ");
            name = this.scanner.nextLine();
            if(name.isEmpty()) {
                System.out.println("Spelarens namn kan inte vara tom!");
            }
        }
        currentPlayer = new Player(name);
        players.add(currentPlayer);
    }

    /**
     * Method allowing the player to choose an active player from the created players.
     */
    private void choosePlayer() {
        System.out.println();
        System.out.println("Välj spelare:");
        System.out.println("0. Tillbaka till huvudmenyn");
        for (int i = 0; i < players.size(); i++) {
            System.out.println(i + 1 + ". " + players.get(i).getName());
        }
        System.out.println();
        System.out.print("Gör ett val: ");
        int choice = 0;
        try {
            choice = Integer.parseInt(this.scanner.nextLine());
        } catch (Exception e) {
            System.out.println();
            System.out.println("Felaktigt alternativ!");
            choosePlayer();
        }
        if (choice == 0) {
            showMenu();
        } else if (choice < 0 || choice > players.size() + 1) {
            System.out.println();
            System.out.println("Felaktigt alternativ!");
            choosePlayer();
        } else {
            currentPlayer = players.get(choice - 1);
        }
    }

    /**
     * Method closes the application. If there were players created it will call a method to write results to file.
     */
    private void exitGame() {
        if (!players.isEmpty()) {
            writeResultsToFile();
        }
        System.out.println();
        System.out.println("Hej då!");
        System.exit(0);
    }

    /**
     * Method writes the results' history of each created player to a text file.
     */
    private void writeResultsToFile() {
        System.out.println();
        System.out.println("Skriver resultathistorik till fil...");
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("StenSaxPase_History.txt", true));
            String dateToWrite = formatter.format(now) + "\n";
            bufferedWriter.append(dateToWrite);
            for (Player player : players) {
                String playerToWrite = player.getName() + "\n";
                bufferedWriter.append(playerToWrite);
                if (player.getResultHistory().isEmpty()) {
                    bufferedWriter.append("Inga matcher spelade hittills!");
                }
                for (Result result : player.getResultHistory()) {
                    String resultToWrite = "Spel nummer: " + result.getMatchId() + " " + result.getResultText() + " Ditt val var: " + result.getPlayersFigure().getName() + ", datorns val var: " + result.getComputersFigure().getName() + "\n";
                    bufferedWriter.append(resultToWrite);
                }
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            System.out.println();
            System.out.println("Klart!");
        } catch (IOException e) {
            System.out.println("Ett fel uppstod vid skrivning till filen!");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
