import java.io.FileNotFoundException;

class Main {
    public static void main(String[] args) throws FileNotFoundException {

        TakeTitle takeTitle = new TakeTitle();
        PrepareGame prepareGame = new PrepareGame(takeTitle.title);
        GameManager gameManager = new GameManager(takeTitle, prepareGame);

        gameManager.game();
    }
}
