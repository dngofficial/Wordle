import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class WordleExtractMasterFile
{
    private ArrayList<String> wordleMasterList;

    public WordleExtractMasterFile()
    {
        importWordleMasterList();
    }

    /**
     * Scans the word list and puts it's into a word list. Not in alphabetical order.
     */
    private void importWordleMasterList()
    {
        String[] tmp = null;
        try
        {
            FileReader fileReader = new FileReader("src\\wordle_master_text.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> lines = new ArrayList<String>();
            String line = null;

            while ((line = bufferedReader.readLine()) != null)
            {
                lines.add(line);
            }

            bufferedReader.close();
            tmp = lines.toArray(new String[lines.size()]);
        }
        catch (IOException e)
        {
            System.out.println("Error importing file; unable to access "+ e.getMessage());
        }

        wordleMasterList = new ArrayList<String>(Arrays.asList(tmp));
    }

    /**
     * Randomly selects a word from the masterlist of words.
     * @return A random words.
     */
    public String getTheAnswer()
    {
        return wordleMasterList.get((int)(Math.random() * wordleMasterList.size()));
    }

    /**
     * Checks if the word is in the master list.
     * @param guess
     * @return true or false
     */
    public boolean isValid(String guess)
    {
        String word = guess.toLowerCase();
        for(int i=0; i < wordleMasterList.size(); i++) {

            if (word.equals(wordleMasterList.get(i))) {

                return true;
            }
        }
        return false;
    }

}

