public class Letter
{

    final String GREEN = "\033[0;32m";  //green
    final String RESET = "\u001B[0m";      //reset
    final String YELLOW = "\u001B[33m"; //yellow
    final String GRAY = "\u001B[37m";   //gray

    private String letter;
    private String letterColorCode;

    /**
     * Contructor for the Letter class
     * @param letter Takes a single letter string.
     * @param letterColorCode A letter code that is associated to the letter that will print out the letter in that color.
     */
    public Letter (String letter, String letterColorCode)
    {
        this.letter = letter;
        this.letterColorCode = letterColorCode;
    }

    /**
     * Prints out a letter based on the given string and the color code.
     */
    public void printOutLetter()
    {
        if(letterColorCode.equals("green"))
        {
            System.out.print(GREEN + letter);
        }
        else if(letterColorCode.equals("gray"))
        {
            System.out.print(GRAY + letter);
        }
        else if(letterColorCode.equals("yellow"))
        {
            System.out.print(YELLOW + letter);
        }
        else if(letterColorCode.equals("reset"))
        {
            System.out.print(RESET + letter);
        }
    }
}
