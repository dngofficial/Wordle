import java.util.Scanner;

public class Wordle
{
    final String GREEN = "\033[0;32m";
    final String RESET = "\u001B[0m";
    final String YELLOW = "\u001B[33m";
    final String GRAY = "\u001B[37m";

    private Letter[][] wordleBoard;
    private String solutionAsAnArray[];
    private String solution;
    private WordleExtractMasterFile extractWordle;
    private Scanner scanner;

    /**
     * Boolean value for whether the player wants to play with valid word checker or they can use whatever 5 letter word they want.
     */
    private boolean validCheckToggle;

    /**
     * Initializes the wordle array and fills it out with empty Letters. Also, randomly generates the solution for the wordle.
     */
    public Wordle()
    {
        wordleBoard = new Letter[6][5];

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                wordleBoard[row][col] = new Letter("_", "reset");
            }
        }

         extractWordle = new WordleExtractMasterFile();

        solution = extractWordle.getTheAnswer().toUpperCase();
        solutionAsAnArray = stringToArray(solution);

        scanner = new Scanner(System.in);  // Create a Scanner object

        validCheckToggle = false;

    }

    /**
     * Instructions for the wordle game. Also, checker for playing with valid word checks or not.
     */
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

                System.out.print("Press enter to continue.");

        String placeholder = scanner.nextLine();

        String toggle = "";
        boolean checker = true;

        while(checker)
        {
            System.out.print("Do you want to play with valid word checks (1) or use any 5 letter word you want (2)?:  ");
              toggle = scanner.nextLine();

              if (toggle.equals("2") || toggle.equals("1"))
            {
                checker = false;
            }
        }

        if (toggle.equals("1"))
        {
            validCheckToggle = true;
        }

    }

    /**
     * The primary gameplay loop. Takes and processes the guesses.
     */
    public void gameplayLoop()
    {

        instructions();
        drawBoard();
        for (int i = 0; i < 6; i++)
        {
            String guess = "";
            boolean checker = false;
            while(!checker)
            {
            if (validCheckToggle)
            {
                    System.out.println("What's your guess? (Has to be a valid 5 letter word): ");
                    guess = scanner.nextLine();
                checker = extractWordle.isValid(guess);
                }
                else
                {
                    System.out.println("What's your guess? (Has to be a 5 letter word): ");
                    guess = scanner.nextLine();
                    checker = guess.length() == 5;
                }
            }

            fillOutLine(i, guess);

            drawBoard();

            if(guess.toUpperCase().equals(solution))
            {
                System.out.println("Wow you solved the wordle!");
                System.exit(0);
            }
        }

        System.out.println("Looks like you didn't solve the worlde in 6 tries! The actual word was " + GREEN + solution.toUpperCase());

    }

    /**
     *  Converts a word into a String array composed of uppercase letters from that word.
     * @param word A word.
     * @return A string array composed of the single letters of the word.
     */
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

    /**
     * Prints out the wordle board.
     */
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

    /**
     *
     * @param idx
     * @param guess
     * @return True or false based if the current letter at the index of the guess string is in the correct spot in conjunction with the solution.
     */
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

    /**
     *
     * @param idx
     * @param guess
     * @return True or false based if the current letter at the index of the guess string is in the guess, but not the right spot in conjunction with the solution.
     */
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

    /**
     * The number of duplicates there are of a letter within the guess string.
     * @param letter
     * @param guess
     * @return Duplicate total count
     */
    public int duplicatesCountTotal(String letter, String guess)
    {
        String[] guessArr = stringToArray(guess);
        int counter = 0;
        for (int i = 0; i < 5; i++)
        {
            if (guessArr[i].equals(letter))
            {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Returns the current number of a duplicate considering that there are multiple of the same letter.
     * @param idx
     * @param guess
     * @return Current duplicate iteration.
     */
    public int currentDuplicate(int idx, String guess)
    {
        String[] guessArr = stringToArray(guess);
        int counter = 0;
        for (int i = 0; i < 5; i++)
        {
            if (guessArr[i].equals(guessArr[idx]) && i >= idx)
            {
                counter ++;
            }
            if (i == idx)
            {
                counter++;
                return counter;
            }
        }
        return counter;
    }

    public boolean duplicates(String letter, String guess)
    {
        String[] guessArr = stringToArray(guess);
        int counter = 0;
        for (int i = 0; i < 5; i++)
        {
            if (guessArr[i].equals(letter))
            {
                counter ++;
            }
        }
        if (counter != 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * The main logic for how the wordle is played out. Fills out completely a row on the wordle board and determines what color the letter will be in conjunction to a number of factors
     * in comparison with the solution.
     * @param row
     * @param guess
     */
    public void fillOutLine(int row, String guess)
    {
        guess = guess.toUpperCase();
        String[] guessArr = stringToArray(guess);
        for (int col = 0; col < 5; col++)
        {
            String letter = guessArr[col];

                if (isInCorrectSpot(col, guess))
                {
                    wordleBoard[row][col] = new Letter(guessArr[col], "green");
                }
                else if (duplicates(letter, guess) && isInTheGuessButInCorrectSpot(col, guess) && !isInCorrectSpot(col, guess) && currentDuplicate(col, guess) > duplicatesCountTotal(letter, solution))
                {
                    wordleBoard[row][col] = new Letter(guessArr[col], "gray");
                }
                else if (isInTheGuessButInCorrectSpot(col, guess) && !isInCorrectSpot(col, guess))
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
