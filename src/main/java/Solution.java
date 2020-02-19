import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    private File file;

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
        concatWords.remove(Collections.max(concatWords, Comparator.comparing(String::length)));
        return Collections.max(concatWords, Comparator.comparing(String::length));
    }

    public int getAmountOfConcatWords() {
        List<String> words = convertWordsFromFileToList(file);
        return getConcatenatedWords(words).size();
    }

    private List<String> getConcatenatedWords(List<String> words) {
        Set<String> allWords = new HashSet<>(words);
        List<String> result = new ArrayList<>();
        for (String word : words) {
            allWords.remove(word);
            if (validateWord(word, allWords)) {
                result.add(word);
            }
            allWords.add(word);
        }
        return result;
    }

    private boolean validateWord(String word, Set<String> set) {
        if (set.contains(word)) {
            return true;
        }
        for (int i = 1; i < word.length(); i++) {
            if (set.contains(word.substring(0, i)) && validateWord(word.substring(i), set)) {
                return true;
            }
        }
        return false;
    }

    private List<String> convertWordsFromFileToList(File file) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                Collections.addAll(words, line.split(" "));
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Something wrong with the file ", e);
        }
        return words;
    }
}
