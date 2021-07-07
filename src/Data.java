import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;

public class Data {


    static ArrayList glueWords = new ArrayList<String>();

    static HashMap<String, Integer> top10Words = new HashMap<>();

    public static HashMap mapText() {

        File KJB = new File("C:\\Users\\plevy\\IdeaProjects\\KJB top 10\\src\\smolkjBible");
        HashMap<String, Integer> holyWords = new HashMap<>();
        String words;
        try {
            Scanner scan = new Scanner(KJB);
            while (scan.hasNext()) {
                words = scan.next().toLowerCase(Locale.ROOT);
                if (holyWords.containsKey(words)) {
                    int x = holyWords.get(words);
                    x++;
                    holyWords.put(words, x);
                } else {
                    holyWords.put(words, 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return holyWords;
    }

    public static HashMap removeGlueWord(HashMap words) {

        Entry<String, Integer> entry;
        for (Object iter : words.entrySet()) {
            entry = (Entry<String, Integer>) iter;
            String word = entry.getKey();

            if (isGlueWord(word)) {
                words.remove(word);
            }
        }
        return words;
    }

    public static boolean isGlueWord(String word) {

        for (Object element : glueWords) {
            if (word.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInTop(String word, Integer occurence) {
        int minimum = 99999;
        String minimumWord = null;

        if (top10Words.size() < 10) {
            top10Words.put(word, occurence);
            if(minimum > occurence){
                minimum = occurence;
                minimumWord = word;
            }
        } else {
            if (occurence > minimum) {
                top10Words.remove(minimumWord);
                top10Words.put(word, occurence);
                
                //swap occurence and value
                return true;
            }else{
                return false;
            }
        }
        return true;
    }

    public static void findTop10(HashMap listOfWords) {


        String topWord;
        int originalValue;
        for (int i = 0; i < 10; i++) {
            originalValue = 0;
            topWord = null;

            Entry<String, Integer> entry;
            for (Object iter : listOfWords.entrySet()) {
                entry = (Entry<String, Integer>) iter;

                if (isInTop(entry.getKey(), entry.getValue())) {
                    int newValue = entry.getValue();
                    if (newValue > originalValue) {
                        originalValue = newValue;
                        topWord = entry.getKey();
                    }
                }
            }
            System.out.println("\"" + topWord.toUpperCase(Locale.ROOT) + "\"" + " which appears: " + originalValue + " times.");
            listOfWords.remove(topWord);
        }
    }
}