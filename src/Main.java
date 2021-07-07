import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Data.glueWords.add("the");

        HashMap listOfWords = Data.mapText();
        System.out.println("The Top 10 Words are: ");
        Data.findTop10(listOfWords);





    }
}
