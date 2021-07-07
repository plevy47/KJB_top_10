import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Data data = new Data("D:\\Work_Boris\\Learn\\Java\\KJB_top_10\\src\\kjBible");
        data.glueWords.add("the");
        // data.addGlueWords("the") -> google "vararg java"
        data.removeGlueWords();
        System.out.println("Top 10 words are");
        data.findTop10();
        data.printTopTen();


//        HashMap listOfWords = Data.mapText();
//        Data.findTop10(listOfWords);


    }
}
