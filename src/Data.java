import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Data {

    String[] glueWords = {
            "the",
            "and",
            "of",
            "to",
            "that",
            "in",
            "for",
    };

    public static HashMap mapText() {

        File KJB = new File("C:\\Users\\plevy\\IdeaProjects\\KJB top 10\\src\\kjBible");
        HashMap<String, Integer> holyWords = new HashMap<String, Integer>();
        String words;
        try {
            Scanner scan = new Scanner(KJB);
            while (scan.hasNext()) {
                words = scan.next().toLowerCase(Locale.ROOT);
                if (holyWords.containsKey(words)) {//key already  exists
                    int x = holyWords.get(words);
                    x++;
                    holyWords.put(words, x);
                } else {//key doesn't exist
                    holyWords.put(words, 1); //create new key and set the count to one
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return holyWords;
    }

    public static void findTop10(HashMap listOfWords) {

        HashMap<String, Integer> top10Words = new HashMap<String, Integer>();
        top10Words.put(null, 0);

        for (int i = 0; i < 10; i++) {

            int originalValue = 0;
            String topWord = null;

            Map.Entry<String, Integer> entry;
            for (Object iter : listOfWords.entrySet()) {
                entry = (Map.Entry<String, Integer>) iter;

                int newValue = entry.getValue();
                if (newValue > originalValue) {
                    originalValue = newValue;
                    topWord = entry.getKey();

                }
            }
            top10Words.put(topWord, originalValue);
            System.out.println(top10Words);
            //System.out.println("\""+ topWord.toUpperCase(Locale.ROOT) + "\"" + " which appears: " + originalValue + " times.");
            //listOfWords.remove(topWord);
        }
    }
}



//hashmap picks next value
//compares it to the lowest value in the hashmap
//replaces the lowest value