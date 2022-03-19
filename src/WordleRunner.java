import java.util.Arrays;

public class WordleRunner
{
    public static void main(String arg[]) {
        WordleExtractMasterFile extractWordle = new WordleExtractMasterFile();

        String answer = extractWordle.getTheAnswer();
        System.out.println(answer);

        Wordle wordle = new Wordle();
        wordle.instructions();

        Letter letter = new Letter("T", "yellow");

        letter.printOutLetter();


        String[] cArray = wordle.stringToArray(answer);
        System.out.println(cArray[0]);

        System.out.println(Arrays.toString(cArray));

        wordle.drawBoard();

    }
}
