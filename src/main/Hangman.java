import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Hangman {
    public static void main(String[] args) {
        new Hangman().introduction();
    }

    private ArrayList<String> wordList = new ArrayList();
    private String currentWord;

    private ArrayList<String> goodGuesses = new ArrayList();
    private ArrayList<String> badGuesses = new ArrayList();
    private int goodGuessCount = 0;
    private int badGuessCount = 0;

    private boolean wonRound = false;
    private boolean lostGame = false;

    private Scanner myScanner = new Scanner(System.in);

    Hangman() {
        wordList.add("apple");
        wordList.add("england");
        wordList.add("table");
        wordList.add("scriptures");
        wordList.add("buffalo");
        wordList.add("scarecrow");
        wordList.add("peanut");
        wordList.add("terrific");
        wordList.add("dinosaur");
        wordList.add("complicated");
    }

    private void introduction() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("Welcome to Hangman!");
        System.out.println("Do your best to guess the word to save your friend!");
        System.out.println("Good luck!");
        System.out.println("--------------------------------------------------");

        this.assignWord();
        this.guessLetter();
    }

    private void assignWord() {
        Random rand = new Random();
        int randomInt = rand.nextInt(wordList.size());
        currentWord = wordList.get(randomInt);
    }

    private void guessLetter() {

        drawGallows();

        while (!wonRound && !lostGame) {
            System.out.println("This word has " + currentWord.length() + " letters.");

            System.out.println(goodGuessCount + " good guesses.");
            System.out.println(badGuessCount + " bad guesses.");

            System.out.println("Guess a letter: ");
            String userGuess = myScanner.next();

            if (userGuess.length() > 1) {
                System.out.println("Not a letter, try again.");
                continue;
            }
            else if (currentWord.contains(userGuess)) {
                goodGuess(userGuess);
            }
            else {
                badGuess(userGuess);
            }
        }//while in game

        if (!lostGame) {
            roundFinish();
        }
    }

    private void goodGuess(String userGuess) {
        if (!goodGuesses.contains(userGuess)) {
            goodGuesses.add(userGuess);
            goodGuessCount++;
        }
        currentWordFormatting();
    }

    private void currentWordFormatting() {
        char[] formattedWord = new char[currentWord.length()];

        for (int i = 0; i < currentWord.length(); i++) { //sets the formatted word to be underscores
            formattedWord[i] = '_';
        }

        for (int i = 0; i < currentWord.length(); i++) {  //goes through the current word one character at a time
            if (goodGuesses.contains(String.valueOf(currentWord.charAt(i)))) { //if the character has been guessed
                for (int j = 0; j < currentWord.length(); j++) {  //then it looks through the word for all instances of that letter
                    if (currentWord.charAt(j) == currentWord.charAt(i)) {
                        formattedWord[j] = currentWord.charAt(i); //and outputs them in the formatted word
                    }
                }
            }
        }

        System.out.println(formattedWord);

        for (int i = 0; i < formattedWord.length; i++) {
            if (formattedWord[i] == '_') {
                return;
            }
        }
        wonRound = true;
    }

    private void badGuess(String userGuess) {
        badGuessCount++;
        if (!badGuesses.contains(userGuess)) { //ensures that a bad guess is only output once
            badGuesses.add(userGuess);
        }

        if (badGuessCount == 6) { //the max number of bad guesses, game over
            drawGallows();
            System.out.println("Oh no! Your friend died!");
            lostGame = true;
            return;
        }

        drawGallows(); //draws gallows for each false guess

        System.out.println("Not quite. Wrong guesses: ");

        for (String element : badGuesses) {
            System.out.print(element);
            System.out.print(" ");
        }
        System.out.println();
    }

    private void roundFinish() {
        System.out.println("Congrats! You got it!");
        System.out.println("And it only took you " + (goodGuessCount + badGuessCount) + " guesses!");

        if (wordList.size() == 1) { //case where the wordList is empty, meaning you've beat the game
            System.out.println("Congrats, you beat the game!");
            System.out.println("Go live your life now.");
            return;
        }

        String playAgain = "";
        while (!(playAgain.equals("Yes") || (playAgain.equals("No")))) {
            System.out.println("Would you like to play again? (Yes/No)");
            playAgain = myScanner.next();
        }

        if (playAgain.equals("Yes")) {
            configureNextRound();
        }
        else {
            System.out.println("Thanks for playing! Until next time!");
        }
    }

    private void configureNextRound() {
        wordList.remove(currentWord);
        assignWord();
        goodGuessCount = 0;
        badGuessCount = 0;
        wonRound = false;
        goodGuesses.clear();
        badGuesses.clear();
        guessLetter();
    }

    private void drawGallows() {
        switch(badGuessCount) {
            case 0:
                drawGallowsOne();
                break;
            case 1:
                drawGallowsTwo();
                break;
            case 2:
                drawGallowsThree();
                break;
            case 3:
                drawGallowsFour();
                break;
            case 4:
                drawGallowsFive();
                break;
            case 5:
                drawGallowsSix();
                break;
            case 6:
                drawGallowsSeven();
                break;
        }
    }

    private void drawGallowsOne() {
        System.out.println("------------------------");
        System.out.println("            |           ");
        System.out.println("            |           ");
        System.out.println("            |           ");
    }

    private void drawGallowsTwo() {
        System.out.println("------------------------");
        System.out.println("            |           ");
        System.out.println("            |           ");
        System.out.println("          _ | _         ");
        System.out.println("         |     |         ");
        System.out.println("         |_ _ _|         ");
        System.out.println("                         ");
    }

    private void drawGallowsThree() {
        System.out.println("------------------------");
        System.out.println("            |           ");
        System.out.println("            |           ");
        System.out.println("          _ | _         ");
        System.out.println("         |     |        ");
        System.out.println("         |_ _ _|         ");
        System.out.println("            |             ");
        System.out.println("            |             ");
        System.out.println("            |             ");
        System.out.println("            |             ");
    }

    private void drawGallowsFour() {
        System.out.println("------------------------");
        System.out.println("            |           ");
        System.out.println("            |           ");
        System.out.println("          _ | _         ");
        System.out.println("         |     |        ");
        System.out.println("         |_ _ _|         ");
        System.out.println("            |    /         ");
        System.out.println("            |   /          ");
        System.out.println("            |  /           ");
        System.out.println("            | /            ");
    }

    private void drawGallowsFive() {
        System.out.println("------------------------");
        System.out.println("            |           ");
        System.out.println("            |           ");
        System.out.println("          _ | _         ");
        System.out.println("         |     |        ");
        System.out.println("         |_ _ _|         ");
        System.out.println("       \\    |    /         ");
        System.out.println("        \\   |   /          ");
        System.out.println("         \\  |  /           ");
        System.out.println("          \\ | /            ");
    }

    private void drawGallowsSix() {
        System.out.println("------------------------");
        System.out.println("            |           ");
        System.out.println("            |           ");
        System.out.println("          _ | _         ");
        System.out.println("         |     |        ");
        System.out.println("         |_ _ _|         ");
        System.out.println("       \\    |    /         ");
        System.out.println("        \\   |   /          ");
        System.out.println("         \\  |  /           ");
        System.out.println("          \\ | /            ");
        System.out.println("            | ");
        System.out.println("            |");
        System.out.println("           / ");
        System.out.println("          / ");
        System.out.println("         / ");
        System.out.println("        / ");

    }

    private void drawGallowsSeven() {
        System.out.println("------------------------");
        System.out.println("            |           ");
        System.out.println("            |           ");
        System.out.println("          _ | _         ");
        System.out.println("         |     |        ");
        System.out.println("         |_ _ _|         ");
        System.out.println("       \\    |    /         ");
        System.out.println("        \\   |   /          ");
        System.out.println("         \\  |  /           ");
        System.out.println("          \\ | /            ");
        System.out.println("            | ");
        System.out.println("            |");
        System.out.println("           / \\");
        System.out.println("          /   \\");
        System.out.println("         /     \\");
        System.out.println("        /       \\");
    }
}
