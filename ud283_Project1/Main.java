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
            System.out.println(titles[availableTitles]);
            availableTitles++;
        }

        Random random = new Random();
        int randomTitleIndex = random.nextInt(availableTitles) + 1;
        System.out.println(randomTitleIndex);

        scannerFile.close();
    }
}
