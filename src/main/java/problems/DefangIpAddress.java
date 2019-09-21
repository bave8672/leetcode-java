package problems;

import java.util.regex.Pattern;

public class DefangIpAddress {
    public static class Solution {
        public String defangIPaddr(String address) {
            return Pattern.compile("\\.").matcher(address).replaceAll("[$0]");
        }
    }
}
