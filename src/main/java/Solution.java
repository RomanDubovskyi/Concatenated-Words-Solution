import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution implements DataReadFromFile {
    private File file;
    private static final String EMPTY_STRING = "";

    public Solution(File file) {
        this.file = file;
    }

    public String getLongestWord() {
        List<String> words = convertWordsFromFileToList(file);
        List<String> concatWords = getConcatenatedWords(words);
        return Collections.max(concatWords, Comparator.comparing(String::length));
    }

    public String getSecondLongestWord() {
        List<String> words = convertWordsFromFileToList(file);
        List<String> concatWords = getConcatenatedWords(words);
        String longestWord = EMPTY_STRING;
        String secondLongest = EMPTY_STRING;
        for (String word : concatWords) {
            if (word.length() > longestWord.length()) {
                secondLongest = longestWord;
                longestWord = word;
            } else if (word.length() > secondLongest.length()) {
                secondLongest = word;
            }
        }
        return secondLongest;
    }

    public int getAmountOfConcatWords() {
        List<String> words = convertWordsFromFileToList(file);
        return getConcatenatedWords(words).size();
    }
}
