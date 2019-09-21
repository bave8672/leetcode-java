package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static  org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("DefangIpAddressTest Test")
public class JewelsAndStonesTest {
    public final JewelsAndStones.Solution sln = new JewelsAndStones.Solution();

    @Test
    @DisplayName("1")
    void exampleTestCase1() {
        assertEquals(sln.numJewelsInStones("aA",  "aAAbbbb"), 3);
    }

    @Test
    @DisplayName("2")
    void exampleTestCase2() {
        assertEquals(sln.numJewelsInStones("z",  "ZZ"), 0);
    }
}
