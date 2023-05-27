package com.acm.rossword.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class WordMeaning {
    static List<String> words = new ArrayList<>();

    public static LinkedHashMap<String, String> extractMeaning(List<String> wordsOfCrossWord) throws Exception {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();

        for (int i = 0; i< wordsOfCrossWord.size(); i++)
        {
            String url = "https://api.datamuse.com/words?ml=" + wordsOfCrossWord.get(i)  +"&max=1";
//            String url = "https://api.datamuse.com/words?ml=" + wordsOfCrossWord.get(i)  ;
            URL link = new URL(url);
            URLConnection con = link.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
//                System.out.println(inputLine);
                String str[] = inputLine.split(",");
//                System.out.println("length of str is " + str.length);
//                for ( String temp : str)
//                {
//                    System.out.println("element is " + temp);
//                }
//                String innerString[] = str
//                System.out.println(Arrays.toString(str));
//                String innerString[] =str[0].split(":");
                map.put(wordsOfCrossWord.get(i),str[0]);  // the first element is the response
            }
            in.close();
        }
        return map;
    }
}
