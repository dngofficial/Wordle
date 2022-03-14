import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WordleExtractMasterFile
{
    private ArrayList<String> wordleMasterList;

    public WordleExtractMasterFile()
    {
        importWordleMasterList();
    }

    public ArrayList<String> getWordleMasterList()
    {
        return wordleMasterList;
    }

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


    public String getTheAnswer()
    {
        return wordleMasterList.get((int)(Math.random() * wordleMasterList.size()));
    }

}

