import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String[] glueWords ={
                "the",
                "and",
                "of",
                "to",
                "that",
                "in",
                "for",
        };

        HashMap listOfWords = Data.mapText();
        System.out.println("The Top 10 Words are: ");
        Data.findTop10(listOfWords);




    }
}
