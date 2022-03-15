public class Wordle
{

    final String GREEN = "\033[0;32m";
    final String RESET = "\u001B[0m";
    final String YELLOW = "\u001B[33m";
    final String GRAY = "\u001B[37m";


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

    }
}
