import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

class Main {

    final static String ALL_LETTERS = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    final static int MAX_TITLES = 25;
    final static int MAX_MISTAKES = 10;
    
    public static void main(String[] args) throws FileNotFoundException {

        String title = randomTitle();
        char[] answer = prepareAnswer(title);
        char[] game = prepareGame(answer, title);
        String titleLetters = chooseTitleLetters(title);

        int availableChars = titleLetters.length();
        
        int mistakes = 0;
        boolean isMistake = true;

        Scanner scanner = new Scanner(System.in);
        char letter;

        String usedCorrectLetters = "";
        String wrongLettersLogShown = "";
        String wrongLettersLogHidden = "";

        while (mistakes <= MAX_MISTAKES) {

            if (availableChars == 0) {
                System.out.print("You have guessed '");
                for (int i = 0; i < answer.length; i++) {
                    System.out.print(answer[i]);
                }
                System.out.println("' correctly");
                break;
            }

            if (mistakes == MAX_MISTAKES) {
                System.out.println("You have guesses (" + mistakes + ") wrong letters");
                System.out.print("You didn't guessed '");
                for (int i = 0; i < answer.length; i++) {
                    System.out.print(answer[i]);
                }
                System.out.println("' correctly");
                break;
            }

            System.out.print("You are guessing: ");
            for (int i = 0; i < game.length; i++) {
                System.out.print(game[i]);
            }
            System.out.println();

            switch (mistakes) {
                case 0:
                    System.out.println("You have guesses (" + mistakes + ") wrong letters");
                    break;
                case 1:
                    System.out.println("You have guesses (" + mistakes + ") wrong letter: " + wrongLettersLogShown.trim());
                    break;
                default:
                    System.out.println("You have guesses (" + mistakes + ") wrong letters: " + wrongLettersLogShown.trim());
            }

            System.out.println("Guess a letter: ");
            letter = scanner.next().charAt(0);
            if (!isLetter(letter)) {
                System.out.println("Oops!"); 
                continue;
            }

            for (int i = 0; i < answer.length; i++) {
                if (("" + answer[i]).equalsIgnoreCase("" + letter)) {
                    game[i] = answer[i];
                    isMistake = false;
                }
            }

            if (!isMistake && !usedCorrectLetters.contains("" + letter)) {
                usedCorrectLetters += ("" + letter).toLowerCase() + ("" + letter).toUpperCase();
                availableChars--;
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

    public static boolean isLetter(char letter) {

        return ALL_LETTERS.contains("" + letter);
    }

    public static String chooseTitleLetters(String title) {

        String letters = "abcdefghijklmnopqrstuvwxyz";
        title = title.toLowerCase();
        String result = "";

        for (int i = 0; i < title.length(); i++) {
            if (!result.contains("" + title.charAt(i)) && letters.contains("" + title.charAt(i))) {
                result += title.charAt(i);
            }
        }

        return result;
    }

    public static char[] prepareGame(char[] answer, String title) {

        char[] game = new char[title.length()];

        for (int i = 0; i < title.length(); i++) {
            if (!ALL_LETTERS.contains("" + answer[i])) {
                game[i] = answer[i];
            } else {
                game[i] = '_';
            }
        }

        return game;
    }

    public static char[] prepareAnswer(String title) {

        char[] result = new char[title.length()];

        for (int i = 0; i < title.length(); i++) {
            result[i] = title.charAt(i);
        }

        return result;
    }

    public static String randomTitle() throws FileNotFoundException {

        int availableTitles = 0;
        String[] titles = new String[MAX_TITLES];
        File file = new File("titles.txt");
        Scanner scannerFile = new Scanner(file);

        while (scannerFile.hasNextLine() && availableTitles < MAX_TITLES) {
            titles[availableTitles] = scannerFile.nextLine();
            availableTitles++;
        }

        Random random = new Random();
        int randomTitleIndex = random.nextInt(availableTitles);
        scannerFile.close();

        return titles[randomTitleIndex];
    }
}
