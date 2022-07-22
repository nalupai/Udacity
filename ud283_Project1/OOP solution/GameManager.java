import java.util.Scanner;

public class GameManager {

    private final int MAX_MISTAKES = 10;
    int mistakes = 0;
    boolean isMistake = true;

    String usedCorrectLetters = "";
    String wrongLettersLogShown = "";
    String wrongLettersLogHidden = "";

    TakeTitle takeTitle;
    PrepareGame prepareGame;

    public GameManager(TakeTitle takeTitle, PrepareGame prepareGame) {
        this.takeTitle = takeTitle;
        this.prepareGame = prepareGame;
    }

    public void game() {

        Scanner scanner = new Scanner(System.in);
        char letter;

        while (mistakes <= MAX_MISTAKES) {

            if (prepareGame.howManyTitleLetters == 0) {
                System.out.print("You have guessed '");
                show(prepareGame.correctAnswer);
                System.out.println("' correctly");
                break;
            }
    
            if (mistakes == MAX_MISTAKES) {
                System.out.println("You have guesses (" + mistakes + ") wrong letters");
                System.out.print("You didn't guessed '");
                show(prepareGame.correctAnswer);
                System.out.println("' correctly");
                break;
            }
    
            System.out.print("You are guessing: ");
            show(prepareGame.game);
            System.out.println();
    
            System.out.println(mistakeMessage(mistakes));

            System.out.println("Guess a letter: ");
            letter = scanner.next().charAt(0);
            if (!isLetter(letter)) {
                System.out.println("Oops!");
                continue;
            }
            
            for (int i = 0; i < prepareGame.correctAnswer.length; i++) {
                if (("" + prepareGame.correctAnswer[i]).equalsIgnoreCase("" + letter)) {
                    prepareGame.game[i] = prepareGame.correctAnswer[i];
                    isMistake = false;
                }
            }
    
            if (!isMistake && !usedCorrectLetters.contains("" + letter)) {
                usedCorrectLetters += ("" + letter).toLowerCase() + ("" + letter).toUpperCase();
                prepareGame.howManyTitleLetters--;
            }
    
            if (isMistake && !wrongLettersLogHidden.contains("" + letter)) {
                wrongLettersLogShown += letter + " ";
                wrongLettersLogHidden += ("" + letter).toLowerCase() + ("" + letter).toUpperCase();
                mistakes++;
            }
    
            isMistake = true;
        }
    
        scanner.close();
    }

    private boolean isLetter(char letter) {

        String letters = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        return letters.contains("" + letter);
    }

    private String mistakeMessage(int amount) {

        switch (mistakes) {
            case 0:
                return "You have guesses (" + mistakes + ") wrong letters";
            case 1:
                return "You have guesses (" + mistakes + ") wrong letter: " + wrongLettersLogShown.trim();
            default:
                return "You have guesses (" + mistakes + ") wrong letters: " + wrongLettersLogShown.trim();
        }
    }

    private void show(char[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }
}
