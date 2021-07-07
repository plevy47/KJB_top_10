import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;

public class Data {
    int minimum = 999999;
    String minimumWord = null;
    File textFile;

    ArrayList<String> glueWords = new ArrayList<>();
    HashMap<String, Integer> top10Words;
    HashMap<String, Integer> listOfWords;


    public Data(String path) {
        this.textFile = new File(path);
        this.top10Words = new HashMap<>();
        this.listOfWords = new HashMap<>();
        mapText();
    }

    public void mapText() {
        String words;
        try {
            Scanner scan = new Scanner(textFile);
            while (scan.hasNext()) {
                words = scan.next().toLowerCase(Locale.ROOT);
                if (listOfWords.containsKey(words)) {
                    int x = listOfWords.get(words);
                    x++;
                    listOfWords.put(words, x);
                } else {
                    listOfWords.put(words, 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public void removeGlueWords() {
        listOfWords.keySet().removeIf(key -> isGlueWord(key));
    }

    public boolean isGlueWord(String word) {
        for (Object element : glueWords) {
            if (word.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void addToTop10(String word, Integer occurence) {
        if (top10Words.size() < 10) {
            top10Words.put(word, occurence);
            if (minimum > occurence) {
                minimum = occurence;
                minimumWord = word;
            }
        } else {
            if (occurence > minimum) {
                top10Words.remove(minimumWord);
                top10Words.put(word, occurence);
                minimum = occurence;
                minimumWord = word;
            }
        }
    }

    public void findTop10() {
        for (Entry<String, Integer> entry : listOfWords.entrySet()) {
            addToTop10(entry.getKey(), entry.getValue());
        }
    }

    public void printTopTen() {
        for (Entry<String, Integer> entry : top10Words.entrySet()) {
            System.out.println("The word \"" + entry.getKey() + "\" appeared " + entry.getValue() + " times in the text");
        }
    }
}