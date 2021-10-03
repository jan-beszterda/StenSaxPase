package stensaxpase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class holding the main game logic. It stores players list, current player, current match and is responsible for
 * analysing choices done by user in the menus.
 * @author Jan Beszterda
 */
public class Game {

    private ArrayList<Player> players;
    private Player currentPlayer;
    private Match currentMatch;


    /**
     * Constructor of the central Game object.
     */
    public Game() {
        this.currentMatch = null;
        this.currentPlayer = null;
        this.players = new ArrayList<>();
    }

    /**
     * Initial method to start the program.
     */
    public void showMenu() {
        if (currentPlayer == null) {
            showNoPlayerMainMenu();
        } else {
            showPlayerMainMenu();
        }
    }

    /**
     * Method calling the correct menu if no player was registered and analysing user's choice.
     */
    private void showNoPlayerMainMenu() {
        int choice = Menus.displayNoPlayerMenuOptions();
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
     * Method calling the correct menu if there is an active player registered and analysing user's choice.
     */
    private void showPlayerMainMenu() {
        int choice = Menus.displayRegisteredPlayerMenuOptions(currentPlayer);
        switch (choice) {
            case 1:
                currentMatch = new Match(currentPlayer);
                currentMatch.showMatchMenu();
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
     * Method calling the menu to display when the current match was completed and analysing user's next choice.
     */
    private void showAfterMatchMenu() {
        int choice = Menus.displayAfterMatchMenuOptions();
        switch (choice) {
            case 1:
                showMenu();
                break;
            case 2:
                currentMatch = new Match(currentPlayer);
                currentMatch.showMatchMenu();
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
        currentPlayer = new Player(Menus.displayPlayerCreationMenu());
        players.add(currentPlayer);
    }

    /**
     * Method allowing the player to choose an active player from the created players.
     */
    private void choosePlayer() {
        int choice = Menus.displayPlayerChoiceMenu(players);
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
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter("StenSaxPase_History.txt", true));
            bufferedWriter.append("********************\n");
            String dateToWrite = formatter.format(now) + "\n\n";
            bufferedWriter.append(dateToWrite);
            for (Player player : players) {
                String playerToWrite = player.getName() + "\n";
                bufferedWriter.append(playerToWrite);
                if (player.getResultHistory().isEmpty()) {
                    bufferedWriter.append("Inga matcher spelade.\n");
                } else {
                    for (int i = 0; i < player.getResultHistory().size(); i++) {
                        String resultToWrite = "Spel nummer: " + player.getResultHistory().get(i).getMatchId() + " " +
                                player.getResultHistory().get(i).getResultText() + " Ditt val var: " +
                                player.getResultHistory().get(i).getPlayersFigure().getName() + ", datorns val var: " +
                                player.getResultHistory().get(i).getComputersFigure().getName() + "\n";
                        bufferedWriter.append(resultToWrite);
                        if (i == player.getResultHistory().size()-1) {
                            bufferedWriter.append("\n");
                        }
                    }
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
