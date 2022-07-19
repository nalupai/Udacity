import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class TakeTitle {

    private final int MAX_TITLES = 25;

    String title;

    public TakeTitle() throws FileNotFoundException {
        
        title = this.randomTitle();
    }

    private String randomTitle() throws FileNotFoundException {

        int howManyTitles = 0;
        String[] titles = new String[MAX_TITLES];

        File file = new File("titles.txt");
        Scanner scannerFile = new Scanner(file);

        while (scannerFile.hasNextLine() && howManyTitles < MAX_TITLES) {
            titles[howManyTitles] = scannerFile.nextLine();
            howManyTitles++;
        }

        Random random = new Random();
        int randomTitleIndex = random.nextInt(howManyTitles);
        scannerFile.close();

        return titles[randomTitleIndex];
    }
}
