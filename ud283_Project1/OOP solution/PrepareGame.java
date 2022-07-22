import java.io.FileNotFoundException;

public class PrepareGame {

    char[] correctAnswer;
    char[] game;
    String titleLetters;
    int howManyTitleLetters;

    public PrepareGame(String title) throws FileNotFoundException {
        correctAnswer = prepareCorrectAnswer(title);
        game = prepareGame(correctAnswer, title);
        titleLetters = chooseTitleLetters(title);
        howManyTitleLetters = chooseTitleLettersLength(titleLetters);
    }

    private int chooseTitleLettersLength(String title) {

        return chooseTitleLetters(title).length();
    }

    private String chooseTitleLetters(String title) {

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

    private char[] prepareGame(char[] answer, String title) {

        String letters = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        char[] game = new char[title.length()];
        for (int i = 0; i < title.length(); i++) {
            if (!letters.contains("" + answer[i])) {
                game[i] = answer[i];
            } else {
                game[i] = '_';
            }
        }
        return game;
    }

    private char[] prepareCorrectAnswer(String title) {

        char[] answer = new char[title.length()];
        for (int i = 0; i < title.length(); i++) {
            answer[i] = title.charAt(i);
        }
        return answer;
    }
}
