import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        File file = new File("src/main/resources/words.txt");
        List<String> words = convertWordsFromFileToList(file);
        // First we'll sort out all concatenated words into "result" list.
        List<String> concatWords = getConcatenatedWords(words);
        /*
        According to conditions of the task file contains sorted list, therefore longest
        and second longest words will be last two words from our "result" list.
         */
        int concatenatedWordsAmount = concatWords.size();
        String longestConcatenatedWord = concatWords.get(concatWords.size() - 1);
        String secondLongestWord = concatWords.get(concatWords.size() - 2);
        printResultValues(concatenatedWordsAmount, longestConcatenatedWord, secondLongestWord);
    }

    public static void printResultValues(int number, String longestWord, String secondLongestWord) {
        System.out.println("Amount of concatenated words: " + number);
        System.out.println("Longest concatenated word: " + longestWord);
        System.out.println("Second longest concatenated word: " + secondLongestWord);
    }

    public static List<String> getConcatenatedWords(List<String> words) {
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

    private static boolean validateWord(String word, Set<String> set) {
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

    private static List<String> convertWordsFromFileToList(File file) {
        List<String> words = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                Collections.addAll(words, line.split(" "));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}
