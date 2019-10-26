package problems.SkiplistTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import problems.Skiplist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Skiplist Test")
public class SkiplistTest {
    @Test
    @DisplayName("1")
    void one() {
        Skiplist skiplist = new SkiplistDebug();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        assertFalse(skiplist.search(0));   // return false.
        skiplist.add(4);
        assertTrue(skiplist.search(1));   // return true.
        assertFalse(skiplist.erase(0));    // return false, 0 is not in skiplist.
        assertTrue(skiplist.erase(1));    // return true.
        assertFalse(skiplist.search(1));   // return false, 1 has already been erased.
    }

    @Test
    @DisplayName("2")
    void two() {
        SkiplistDebug skiplist = new SkiplistDebug();
        for (Integer i = 0; i < 100; i++) {
            assertFalse(skiplist.search(i));
            skiplist.add(i);
            assertTrue(skiplist.search(i));
            skiplist.add(i);
            assertTrue(skiplist.search(i));
        }
    }

    @Test
    @DisplayName("3")
    void three() {
        SkiplistDebug skiplist = new SkiplistDebug();
        skiplist.add(1);
        skiplist.add(1);
        assertTrue(skiplist.erase(1));
        skiplist.add(2);
        skiplist.add(2);
        assertTrue(skiplist.erase(2));
    }

    @Test
    @DisplayName("4")
    void four() {
        SkiplistDebug skiplist = new SkiplistDebug();
        for (Integer i = 0; i < 100; i++) {
            skiplist.add(i);
            assertTrue(skiplist.search(i));
            skiplist.assertAcyclic();
            skiplist.add(i);
            assertTrue(skiplist.search(i));
            skiplist.assertAcyclic();
        }
        for (Integer i = 99; i >= 0; i--) {
            assertTrue(skiplist.erase(i));
            skiplist.assertAcyclic();
            assertTrue(skiplist.erase(i));
            skiplist.assertAcyclic();
            assertFalse(skiplist.search(i));
        }
    }

    @Test
    @DisplayName("5")
    void five() {
        SkiplistDebug skiplist = new SkiplistDebug();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        skiplist.add(4);
        skiplist.add(5);
        skiplist.add(6);
        skiplist.add(7);
        skiplist.erase(2);
        skiplist.erase(3);
        skiplist.erase(4);
        skiplist.erase(5);
        skiplist.assertAcyclic();
    }

    @Test
    @DisplayName("6")
    void six() {
        SkiplistDebug skiplist = new SkiplistDebug();
        HashSet<Integer> nums = new HashSet<>();
        for (Integer i = 0; i < 1000; i++) {
            Integer x = (int) Math.round(Math.random() * 9999999);
            if (nums.contains(x)) {
                continue;
            }
            skiplist.add(x);
            assertTrue(skiplist.search(x));
            skiplist.assertAcyclic();
            nums.add(x);
        }
        nums.forEach((x) -> {
            assertTrue(skiplist.erase(x));
            skiplist.assertAcyclic();
            assertFalse(skiplist.search(x));
            Integer y = (int) Math.round(Math.random() * 9999999);
            skiplist.add(y);
            assertTrue(skiplist.search(y));
            skiplist.assertAcyclic();
        });
    }

    @Test
    @DisplayName("7")
    void seven() throws IOException {
        run("test_1.json");
    }

    @Test
    @DisplayName("7")
    void eight() throws IOException {
        run("test_2.json");
    }

    private static class SkiplistDebug extends Skiplist {
        public void assertAcyclic() {
            if (head == null) {
                return;
            }
            for (Integer list = 0; list < head.child.size(); list++) {
                HashSet<Node> nodes  = new HashSet<Node>();
                Node node = head;
                while (node != null) {
                    if (nodes.contains(node)) {
                        throw new Error("Cyclic reference");
                    }
                    if (node.child.size() > head.child.size()) {
                        throw new Error("Nodes exist unbound to the head");
                    }
                    nodes.add(node);
                    node = node.child.get(list);
                }
            }
        }
    }

    static void run(List<String> commands, List<List<Integer>> args, List<Boolean> expectedResults) {
        SkiplistDebug skiplist = new SkiplistDebug();
        for (Integer i = 0; i < commands.size(); i++) {
            Integer arg = null;
            if (args.get(i).size() > 0) {
                arg = args.get(i).get(0);
            }
            switch (commands.get(i)) {
                case "add": {
                    skiplist.add(arg);
                    break;
                }
                case "erase": {
                    skiplist.erase(arg);
                    break;
                }
                case "search": {
                    if (expectedResults.get(i) != skiplist.search(arg)) {
                        throw new Error("Incorrect result");
                    }
                    break;
                }
            }
            skiplist.assertAcyclic();
        }
    }

    static void run(String fileName) throws IOException {
        fileName = "src/test/java/problems/SkiplistTest/" + fileName;
        byte[] jsonData = Files.readAllBytes(Paths.get(fileName));
        TestCase testCase = new ObjectMapper().readValue(jsonData, TestCase.class);
        run(testCase.commands, testCase.args, testCase.expected);
    }

    static class TestCase {
        public List<String> commands;
        public List<List<Integer>> args;
        public List<Boolean> expected;
    }

    static List<Integer> createNumberList(String arrayList) {
        return Arrays.asList(arrayList.split("\\D"))
                .stream()
                .filter((item) -> item != null)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
