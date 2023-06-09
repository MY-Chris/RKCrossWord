package com.acm.rossword.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WordDefinition {
    static List<String> words = new ArrayList<>();

    public static HashMap<String, String> extractDefintion(List<String> wordsOfCrossWord) throws Exception {
        HashMap<String,String> map = new HashMap<>();

        for (int i = 0; i< wordsOfCrossWord.size(); i++)
        {
            String url = "https://api.datamuse.com/words?ml=" + wordsOfCrossWord.get(i)  +"&md=d&max=1";
//            String url = "https://api.datamuse.com/words?ml=" + wordsOfCrossWord.get(i)  ;
            URL link = new URL(url);
            URLConnection con = link.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            con.getInputStream()));
            String inputLine;
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode jsonNode = objectMapper.readTree(response);
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                String str[] = inputLine.split(",");
//                System.out.println("length of str is " + str.length);
//                for ( String temp : str)
//                {
//                    System.out.println("element is " + temp);
//                }
//                String innerString[] = str
                System.out.println(Arrays.toString(str));
                String innerString[] =str[4].split(",");
                System.out.println("word is " + innerString[0]);
                map.put(wordsOfCrossWord.get(i),str[4]);  // the first element is the response
            }
            in.close();
        }
        return map;
    }
}
