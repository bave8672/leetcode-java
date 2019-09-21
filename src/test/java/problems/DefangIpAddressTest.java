package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static  org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("DefangIpAddressTest Test")
public class DefangIpAddressTest {
    @Test
    @DisplayName("1")
    void exampleTestCase1() {
        DefangIpAddress.Solution sln = new DefangIpAddress.Solution();
        assertEquals(
                "1[.]1[.]1[.]1",
                sln.defangIPaddr("1.1.1.1")
        );
    }

    @Test
    @DisplayName("2")
    void exampleTestCase2() {
        DefangIpAddress.Solution sln = new DefangIpAddress.Solution();
        assertEquals(
                "255[.]100[.]50[.]0",
                sln.defangIPaddr("255.100.50.0")
        );
    }
}
