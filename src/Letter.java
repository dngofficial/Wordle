public class Letter
{
    final String GREEN = "\033[0;32m";  //green
    final String RESET = "\u001B[0m";      //reset
    final String YELLOW = "\u001B[33m"; //yellow
    final String GRAY = "\u001B[37m";   //gray

    private String letter;
    private String letterColorCode;

    public Letter (String letter, String letterColorCode)
    {
        this.letter = letter;
        this.letterColorCode = letterColorCode;
    }

    public String getLetter()
    {
        return letter;
    }

    public String getLetterColorCode()
    {
        return letterColorCode;
    }

    public void setLetter(String newLetter) {letter = newLetter;}

    public void setLetterColorCode(String newCode) {letterColorCode = newCode; }

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
