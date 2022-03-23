import java.util.Arrays;
import java.util.Scanner;

public class Wordle
{
    final String GREEN = "\033[0;32m";
    final String RESET = "\u001B[0m";
    final String YELLOW = "\u001B[33m";
    final String GRAY = "\u001B[37m";

    private final Letter[][] wordleBoard;
    private final String solutionAsAnArray[];
    private final String solution;
    private WordleExtractMasterFile extractWordle;
    private Scanner scanner;

    public Wordle()
    {
        wordleBoard = new Letter[6][5];

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                wordleBoard[row][col] = new Letter("_", "reset");
            }
        }

         extractWordle = new WordleExtractMasterFile();

        solution =  extractWordle.getTheAnswer().toUpperCase();
        solutionAsAnArray = stringToArray(solution);

        scanner = new Scanner(System.in);  // Create a Scanner object

    }

    public void instructions()
    {

        System.out.println("Guess the WORDLE in six tries.\n" +
                "\n" +
                "Each guess must be a valid five-letter word. Hit the enter button to submit when prompted too.\n" +
                "\n" +
                "After each guess, the color of the tiles will change to show how close your guess was to the word.");

                System.out.println("Examples: ");
                System.out.println("-----------------------------------------------");
                System.out.println(GREEN + "W" + RESET + "EARY");
                System.out.println(RESET + "The letter W is in the word and in the correct spot!");
                System.out.println("-----------------------------------------------");
                System.out.println("P" + YELLOW  + "I" + RESET + "LLS");
                System.out.println(RESET + "The letter I is in the word but in the wrong spot.");
                System.out.println("-----------------------------------------------");
                System.out.println("VAG" + GRAY + "U" + RESET + "E");
                System.out.println("The letter U is not in the word in any spot.");

        String placeholder = scanner.nextLine();

    }

    public void gameplayLoop()
    {

        instructions();
        drawBoard();
        for (int i = 0; i < 6; i++)
        {
            String guess = "";
            while(!extractWordle.isValid(guess))
            {
                System.out.println("What's your guess? (Has to be a valid 5 letter word): ");
                guess = scanner.nextLine();
            }

            fillOutLine(i, guess);

            drawBoard();

            if(guess.toUpperCase().equals(solution))
            {
                drawBoard();
                System.out.println("Wow you solved the wordle!");
                break;
            }
        }

        System.out.println("Looks like you didn't solve the worlde in 6 tries! The actual word was " + GREEN + solution.toUpperCase());

    }

//    public boolean inCorrectSpot

    public String[] stringToArray(String word)
    {
        String[] returnArr = new String[word.length()];

        for (int i = 0; i < word.length()- 1; i++)
        {

            returnArr[i] = word.substring(i, i + 1).toUpperCase();
        }

        returnArr[word.length() - 1] = word.substring(word.length() - 1).toUpperCase();

        return returnArr;
    }

    public void drawBoard()
    {
        for (int row = 0; row < 6; row++)
        {
            System.out.println(RESET + "----------------------");
            System.out.print(RESET + "| ");
            for (int col = 0; col < 5; col++)
            {
                wordleBoard[row][col].printOutLetter();
                System.out.print(RESET + " | ");
            }
            System.out.println("");
        }
        System.out.println(RESET + "----------------------");

    }

    public Letter[][] returnWordleBoard()
    {
        return wordleBoard;
    }

    public boolean isInCorrectSpot(int idx, String guess)
    {
        String[] guessArr = stringToArray(guess);
        if (guessArr[idx].equals( solutionAsAnArray[idx]))
        {
            return true;
        }
        else {

            return false;

        }
    }

    public boolean isInTheGuessButInCorrectSpot(int idx, String guess)
    {
        boolean inCorrectSpot = false;
        String[] guessArr = stringToArray(guess);
        for (int i = 0; i < 5; i++)
        {
            if (guessArr[idx].equals(solutionAsAnArray[i]))
            {
                inCorrectSpot = true;
            }
        }
        if (inCorrectSpot && !isInCorrectSpot(idx, guess))
        {
            return true;
        }
        else
        {
            return false;

        }
    }

    public void fillOutLine(int row, String guess)
    {
        guess = guess.toUpperCase();
        String[] guessArr = stringToArray(guess);
        for (int col = 0; col < 5; col++)
        {
            if(isInCorrectSpot(col, guess))
            {
                wordleBoard[row][col] = new Letter(guessArr[col], "green");
            }
            else if(isInTheGuessButInCorrectSpot(col, guess) && !isInCorrectSpot(col, guess))
            {
                wordleBoard[row][col] = new Letter(guessArr[col], "yellow");
            }
            else
            {
                wordleBoard[row][col] = new Letter(guessArr[col], "gray");
            }
        }
    }



}
