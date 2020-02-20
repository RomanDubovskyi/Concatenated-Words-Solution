import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface DataReadFromFile {
    default List<String> convertWordsFromFileToList(File file) {
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

    default List<String> getConcatenatedWords(List<String> words) {
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

    default boolean validateWord(String word, Set<String> set) {
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

    String getLongestWord();

    String getSecondLongestWord();

    int getAmountOfConcatWords();
}
