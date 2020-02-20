import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class TestSolution {
    private DataReadFromFile solution;

    @Before
    public void setUp() {
        solution = new Solution(new File("src/main/resources/file_for_testing.txt"));
    }

    @Test
    public void readFromFileToListValid() {
        File file = new File("src/main/resources/file_for_testing.txt");
        List<String> expectedList = new ArrayList<>();
        expectedList.add("cat");
        expectedList.add("cats");
        expectedList.add("catsdogcats");
        expectedList.add("dog");
        expectedList.add("dogcatsdog");
        expectedList.add("hippopotamuses");
        expectedList.add("rat");
        expectedList.add("ratcatdogcat");
        assertEquals(expectedList, solution.convertWordsFromFileToList(file));
    }

    @Test
    public void getConcatWordsValid() {
        File file = new File("src/main/resources/file_for_testing.txt");
        List<String> expectedList = new ArrayList<>();
        expectedList.add("catsdogcats");
        expectedList.add("dogcatsdog");
        expectedList.add("ratcatdogcat");
        assertEquals(expectedList, solution.getConcatenatedWords(solution.convertWordsFromFileToList(file)));
    }

    @Test
    public void getLongestWordValid() {
        assertEquals("ratcatdogcat", solution.getLongestWord());
    }

    @Test
    public void getSecondLongestWordValid() {
        assertEquals("catsdogcats", solution.getSecondLongestWord());
    }

    @Test
    public void getAmountOfConcatWordsValid() {
        assertEquals(3, solution.getAmountOfConcatWords());
    }

    @Test(expected = RuntimeException.class)
    public void checkUsingInvalidFile() {
        File file = new File("src/main/resources/wrongfile.txt");
        solution.convertWordsFromFileToList(file);
    }

    @Test(expected = NoSuchElementException.class)
    public void checkUsingEmptyFile() {
        solution = new Solution(new File("src/main/resources/empty.txt"));
        assertNull(solution.getLongestWord());
    }

}
