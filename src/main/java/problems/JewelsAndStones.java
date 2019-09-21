package problems;

import java.util.HashSet;

public class JewelsAndStones {
    public static class Solution {
        public int numJewelsInStones(String J, String S) {
            final HashSet<Integer> jewels = new HashSet();
            J.chars().forEach(jewels::add);
            return (int) S.chars().filter(jewels::contains).count();
        }
    }
}
