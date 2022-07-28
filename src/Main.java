import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static boolean wordState, playerGuess;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.print("1 or 2 players?: ");
        String players = sc.nextLine();
        String word;

        if (players.equals("1")) {
            Scanner listOfWords = new Scanner(new File("C:\\Users\\Dell\\Desktop\\words.txt"));

            List<String> words = new ArrayList<>();

            while (listOfWords.hasNext()) {
                words.add(listOfWords.next());
            }

            Random rand = new Random();
            word = words.get(rand.nextInt(words.size()));

            if (word.length() > 10) {
                while (word.length() > 10) {
                    word = words.get(rand.nextInt(words.size()));
                }
            }

        } else {
            System.out.println("Player 1, please enter your word");
            word = sc.nextLine();

            for (int i = 0; i < 20; i++) {
                System.out.print("\n");
            }

            System.out.println("Ready for player 2! Good luck!");
        }

        List<Character> playerGuesses = new ArrayList<>();

        int wrongCount = 0;
        while (true) {

            printHangedMan(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("You lose");
                System.out.println("The word was " + word);
                break;
            }

            printWordState(word, playerGuesses);

            if (!getPlayerGuess(sc, word, playerGuesses)) {
                wrongCount++;
            }

            if (wordState) {
                System.out.println("You win!");
                break;
            }

            System.out.print("Please enter your guess for the word: ");
            if (sc.nextLine().equals(word)) {
                System.out.println("You win!");
                break;
            } else {
                System.out.println("Nope! Try again.");
            }
        }


    }

    private static void printHangedMan(int wrongCount) {
        System.out.println(" -------");
        System.out.println(" |     |");

        if (wrongCount >= 1) System.out.println(" O");

        if (wrongCount >= 2) {
            System.out.print("\\ ");

            if (wrongCount >= 3) System.out.println("/");
            else System.out.println();
        }

        if (wrongCount >= 4) System.out.println(" |");

        if (wrongCount >= 5) {
            System.out.print("/ ");

            if (wrongCount >= 6) System.out.println("\\");
            else System.out.println();
        }

        System.out.println();
    }

    private static boolean getPlayerGuess(Scanner sc, String word, List<Character> playerGuesses) {
        System.out.print("Please enter a letter: ");
        String letterGuess = sc.nextLine();
        System.out.println();

        playerGuesses.add(letterGuess.charAt(0));

        printWordState(word, playerGuesses);

        return (word.contains(letterGuess));
    }

    private static void printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print((Object) word.charAt(i));
                correctCount++;
            } else System.out.print((Object) "_");
        }
        System.out.println();

        wordState = (word.length() == correctCount);
    }
}
