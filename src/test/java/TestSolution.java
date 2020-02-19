import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class TestSolution {
    private Solution solution;

    @Before
    public void setUp() throws Exception {
        solution = new Solution(new File("src/main/resources/words.txt"));
    }

    @Test
    public void getLongestWordValid() {
        assertEquals("subscriptions", solution.getLongestWord());
    }

    @Test
    public void getSecondLongestWordValid() {
        assertEquals("substructure", solution.getSecondLongestWord());
    }

    @Test
    public void getAmountOfConcatWordsValid() {
        assertEquals(14024, solution.getAmountOfConcatWords());
    }

    @Test(expected = RuntimeException.class)
    public void checkUsingInvalidFile() {
        solution = new Solution(new File("src/main/resources/wrongfile.txt"));
        assertEquals(14024, solution.getAmountOfConcatWords());
    }

    @Test(expected = NoSuchElementException.class)
    public void checkUsingEmptyFile() {
        solution = new Solution(new File("src/main/resources/empty.txt"));
        assertNull(solution.getLongestWord());
    }

}
