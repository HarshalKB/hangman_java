//     print(hangman_art.stages[lives])

package hangman;

import java.util.ArrayList;
import java.util.Scanner;

public class HangmanMain {
    public static void main(String[] args) {
        String[] wordList = HangmanWords.wordList;
        String chosenWord = wordList[(int) Math.round(Math.random()*wordList.length)];
        int wordLength = chosenWord.length();
        boolean endOfGame = false;
        int lives = 6;

        ArrayList<String> display = new ArrayList<String>();
        for (int i = 0; i < wordLength; i++) {
            display.add("_");
        }
        ArrayList<String> alreadyGuessed = new ArrayList<String>();
        while (!endOfGame) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Guess a letter: ");
            String guess = sc.next();
            if (alreadyGuessed.contains(guess)) {
                System.out.printf("%s already guessed%n", guess);
                continue;
            } else {
                alreadyGuessed.add(guess);
            }

            for (int i = 0; i < wordLength; i++) {
                String letter = String.valueOf(chosenWord.charAt(i));
                if (letter.equals(guess)) {
                    display.set(i, guess);
                }
            }
            if (chosenWord.indexOf(guess) == -1) {
                System.out.printf("%s not in the word.%n", guess);
                lives -= 1;
                if (lives == 0) {
                    endOfGame = true;
                    sc.close();
                    System.out.printf("You lose. The word was %s.%n", chosenWord);
                }
            }

            String updatedDisplay = String.join("", display);
            System.out.printf("%s%n", updatedDisplay);
            if (updatedDisplay.indexOf("_") == -1) {
                endOfGame = true;
                sc.close();
                System.out.println("You Win");
            }
            System.out.println(HangmanArt.stages[lives]);
        }
    }
}