package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static  org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LongestDecompositionTest Test")
public class LongestDecompositionTest {
    public final LongestDecomposition.Solution sln = new LongestDecomposition.Solution();

    @Test
    @DisplayName("1")
    void exampleTestCase1() {
        assertEquals(sln.longestDecomposition("ghiabcdefhelloadamhelloabcdefghi"), 7);
    }

    @Test
    @DisplayName("2")
    void exampleTestCas2() {
        assertEquals(sln.longestDecomposition("merchant"), 1);
    }

    @Test
    @DisplayName("3")
    void exampleTestCas3() {
        assertEquals(sln.longestDecomposition("aaa"), 3);
    }

    @Test
    @DisplayName("4")
    void exampleTestCase4() {
        assertEquals(sln.longestDecomposition("structstruct"), 2);
    }

    @Test
    @DisplayName("5")
    void exampleTestCase5() {
        assertEquals(sln.longestDecomposition("structfennelstruct"), 3);
    }

    @Test
    @DisplayName("6")
    void exampleTestCase6() {
        assertEquals(sln.longestDecomposition("structfennelfennelstruct"), 4);
    }
}
