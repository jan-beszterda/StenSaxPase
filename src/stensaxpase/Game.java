package stensaxpase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private static Figure[] FIGURES = {new Figure("STEN", "SAX", "PÅSE"),
            new Figure("SAX", "PÅSE", "STEN"),
            new Figure("PÅSE", "STEN", "SAX")};

    private ArrayList<Player> players;
    private Player currentPlayer;
    private Match currentMatch;

    public Game() {
        this.currentMatch = null;
        this.currentPlayer = null;
        this.players = new ArrayList<>();
    }

    /**
     *
     */
    public void showMenu() {
        if (currentPlayer == null) {
            showNoPlayerMainMenu();
        } else {
            showPlayerMainMenu();
        }
    }

    /**
     *
     */
    private void showNoPlayerMainMenu() {
        Scanner in;
        while(true) {
            in = new Scanner(System.in);
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
            int choice;
            try {
                choice = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println();
                System.out.println("Felaktigt alternativ!");
                continue;
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
                    break;
            }
        }
    }


    /**
     *
     */
    private void showPlayerMainMenu() {
        Scanner in;
        while(true) {
            in = new Scanner(System.in);
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
            int choice;
            try {
                choice = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println();
                System.out.println("Felaktigt alternativ!");
                continue;
            }
            switch (choice) {
                case 1:
                    currentMatch = new Match(currentPlayer);
                    showPlayerChoiceMenu();
                    break;
                case 2:
                    currentPlayer.showHistory();
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
                    break;
            }
        }
    }

    /**
     *
     */
    private void showPlayerChoiceMenu() {
        Scanner in;
        int choice;
        while(true) {
            in = new Scanner(System.in);
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
            try {
                choice = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println();
                System.out.println("Felaktigt alternativ!");
                continue;
            }
            if (choice < 1 || choice > 3) {
                System.out.println();
                System.out.println("Felaktigt val!");
            } else {
                break;
            }
        }
        currentMatch.compareChoices(currentMatch.chooseComputersFigure(), FIGURES[choice-1]);
        System.out.println();
        showAfterMatchMenu();

    }

    /**
     *
     */
    private void showAfterMatchMenu() {
        Scanner in;
        while(true) {
            in = new Scanner(System.in);
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
            int choice;
            try {
                choice = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println();
                System.out.println("Felaktigt alternativ!");
                continue;
            }
            switch (choice) {
                case 1:
                    showMenu();
                    break;
                case 2:
                    currentMatch = new Match(currentPlayer);
                    showPlayerChoiceMenu();
                    break;
                case 3:
                    exitGame();
                    break;
                default:
                    System.out.println();
                    System.out.println("Felaktigt val!");
                    break;
            }
        }
    }

    /**
     *
     */
    private void createPlayer(){
        Scanner in;
        String name = "";
        while(name.isEmpty()) {
            in = new Scanner(System.in);
            System.out.println();
            System.out.print("Skriv in spelarens namn: ");
            name = in.nextLine();
        }
        currentPlayer = new Player(name);
        players.add(currentPlayer);
    }

    /**
     *
     */
    private void choosePlayer(){
        Scanner in;
        while(true) {
            in = new Scanner(System.in);
            System.out.println();
            System.out.println("Välj spelare:");
            System.out.println("0. Tillbaka till huvudmenyn");
            for (int i = 0; i < players.size(); i++) {
                System.out.println(i+1 + ". " + players.get(i).getName());
            }
            System.out.println();
            System.out.print("Gör ett val: ");
            int choice;
            try {
                choice = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println();
                System.out.println("Felaktigt alternativ!");
                continue;
            }
            if (choice == 0) {
                break;
            } else if (choice < 0 || choice > players.size() + 1) {
                System.out.println();
                System.out.println("Felaktigt alternativ!");
            } else {
                currentPlayer = players.get(choice-1);
                break;
            }
        }
    }

    /**
     *
     */
    private void exitGame() {
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
                for (Result result : player.getResultHistory()) {
                    String resultToWrite = "Spel nummer: " + result.getMatchId() + " " + result.getResultText() + " Ditt val var: " + result.getPlayersFigure().getNAME() + ", datorns val var: " + result.getComputersFigure().getNAME() + "\n";
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
        System.out.println();
        System.out.println("Hej då!");
        System.exit(0);
    }

    public static Figure[] getFIGURES() {
        return FIGURES;
    }
}
