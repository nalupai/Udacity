import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

class Main {
    public static void main(String[] args) throws FileNotFoundException {

        int maxTitles = 25;
        int availableTitles = 0;
        String[] titles = new String[maxTitles];
        File file = new File("titles.txt");
        Scanner scannerFile = new Scanner(file);

        while (scannerFile.hasNextLine() && availableTitles < maxTitles) {
            titles[availableTitles] = scannerFile.nextLine();
            availableTitles++;
        }

        Random random = new Random();
        int randomTitleIndex = random.nextInt(availableTitles);

        String title = titles[randomTitleIndex];
        char[] answer = new char[title.length()];

        for (int i = 0; i < title.length(); i++) {
            answer[i] = title.charAt(i);
        }

        char[] game = new char[title.length()];

        for (int i = 0; i < title.length(); i++) {
            if (answer[i] != ' ') {
                game[i] = '_';
            } else {
                game[i] = answer[i];
            }
        }

        int availableChars = title.contains(" ") ? -1 : 0;
        String uniqueTitleLetters = "";
        for (int i = 0; i < title.length(); i++) {
            if (!uniqueTitleLetters.contains("" + title.charAt(i))) {
                uniqueTitleLetters += title.charAt(i);
                availableChars++;
            }
        }
        
        int mistakes = 0;
        boolean isMistake = true;

        Scanner scanner = new Scanner(System.in);
        char letter;

        String usedCorrectLetters = uniqueTitleLetters.contains(" ") ? " " : "";
        String wrongLettersLog = "";

        while (mistakes <= 10) {

            if (availableChars == 0) {
                System.out.print("You have guessed '");
                for (int i = 0; i < answer.length; i++) {
                    System.out.print(answer[i]);
                }
                System.out.println("' correctly");
                break;
            }

            if (mistakes == 10) {
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
                    System.out.println("You have guesses (" + mistakes + ") wrong letter: " + wrongLettersLog.trim());
                    break;
                default:
                    System.out.println("You have guesses (" + mistakes + ") wrong letters: " + wrongLettersLog.trim());
            }

            System.out.println("Guess a letter: ");
            letter = scanner.next().charAt(0);
            for (int i = 0; i < answer.length; i++) {
                if (("" + answer[i]).equalsIgnoreCase("" + letter)) {
                    game[i] = answer[i];
                    isMistake = false;
                }
            }

            if (!isMistake && !usedCorrectLetters.contains("" + letter)) {
                usedCorrectLetters += letter;
                availableChars--;
            }

            if (isMistake && !wrongLettersLog.contains("" + letter)) {
                wrongLettersLog += letter + " ";
                mistakes++;
            }

            isMistake = true;
        }

        scanner.close();
        scannerFile.close();
    }
}
