package example.crosswordpuzzlegenerator;

import java.util.ArrayList;
import java.util.List;

public class WordExtraction {



    public static List<String> extractWordsVertically(String[][] res) {
        List<String> words = new ArrayList<>();


        String ans = "";
        // now check for vertically
//        System.out.println("words collected horizontally are " +words);
        for( int j = 0; j< res.length;j++)
        {

            ans = "";
            for ( int i = 0; i <= res[j].length;i++)
            {

//                System.out.println("character is   --" +    res[i+1][j]);

                if ( i == res[j].length)
                {
                    if ( ans.length() >=Dictionary.MIN_WORD_LENGTH)
                    {

                        words.add(ans);

                    }
                    break;
                }
                res[i][j] = res[i][j].trim();
                if (   res[i][j].equals("_"))  // it implies there is a word down.
                {
                    // loop down
                    if (ans.length() >= Dictionary.MIN_WORD_LENGTH) {

                        words.add(ans);

                    }
                    ans = "";
                }
                else{
                        ans += res[i][j];
                    }

            }
        }
//        System.out.println("words collected vertically are " +words);
        return words;
    }

    public static List<String> extractWordsHorizontally(String[][] res) {
        List<String> words = new ArrayList<>();
        String ans = "";
        // now check horizontally
        for ( String row[] : res)
        {
//            System.out.println(Arrays.toString(row));
            int i = 0;
            ans = "";
            for(  i = 0; i<= row.length; i++)
            {

                if ( i == row.length )
                {
                    if ( ans.length() >=Dictionary.MIN_WORD_LENGTH)
                    {
                        words.add(ans);
                    }
                    break;
                }
                row[i] = row[i].trim();
//                 System.out.println("element is " + row[i]);
                if ( row[i].equals("_"))
                {
                    if ( ans.length() >=Dictionary.MIN_WORD_LENGTH)
                    {
//                         System.out.println("word is " + ans);
                        words.add(ans);

                    }
                    ans = new String("");  // restarting the answer
                }
                else{
                    ans += row[i];
                }
            }

        }
        return words;
    }
}
