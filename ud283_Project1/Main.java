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
        System.out.println(title);

        char[] answer = new char[title.length()];

        for (int i = 0; i < title.length(); i++) {
            answer[i] = title.charAt(i);
            System.out.println(answer[i]);
        }

        char[] game = new char[title.length()];

        for (int i = 0; i < title.length(); i++) {
            if (answer[i] != ' ') {
                game[i] = '_';
            } else {
                game[i] = answer[i];
            }
            System.out.println(game[i]);
        }

        scannerFile.close();
    }
}
