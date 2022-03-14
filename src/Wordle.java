public class Wordle
{

    final String GREEN = "\033[0;32m";

    public void instructions()
    {
        System.out.println("Welcome to Wordle,  I'll run you through the basic functions of how the game works!");
        System.out.println("You have 6 attempts to guess a randomly picked 5 letter word! But don't worry you won't be left in the dark.");

        System.out.println("Each guess must be a valid, 5 letter word, or else it won't be counted!");

        System.out.println("The letter W is in the word and in the correct spot!");
        System.out.println(GREEN + "W" + "EARY");

    }
}
