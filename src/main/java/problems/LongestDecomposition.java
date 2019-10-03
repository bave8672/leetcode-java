package problems;

public class LongestDecomposition {
    public static class Solution {
        public int longestDecomposition(String text) {
            if (text.isEmpty()) {
                return 0;
            }
            for (int i = 0; i < Math.floor(text.length() / 2); i++) {
                if (text.substring(0, i + 1).equals(text.substring(text.length() - 1 - i))) {
                    return 2 + longestDecomposition(text.substring(i + 1, text.length() - 1 - i));
                }
            }
            return 1;
        }
    }
}
