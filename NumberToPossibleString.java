
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberToPossibleString {

    public static List<String> numberStrToStrings(final String number) {
        Map<String, String> map = new HashMap<String, String>();
        for(int i=1;i<=26;i++) {
            char c = (char)('a'+(i-1));
            map.put(String.valueOf(i), String.valueOf(c));
        }
        return numberStrToStrings(number, 0, map);
    }

    private static List<String> numberStrToStrings(final String number, final int pos, final Map<String, String> map) {
        if(pos >= number.length()) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList();
        if(pos+2 <= number.length()) {
            String twoChar = number.substring(pos, pos + 2);
            String rep2 = map.get(twoChar);
            if(rep2 != null) {
                List<String> char2result = numberStrToStrings(number, pos+2, map);
                if(char2result.isEmpty()) {
                    result.add(rep2);
                } else {
                    for (String s : char2result) {
                        result.add(rep2 + s);
                    }
                }
            }
        }
        String oneChar = number.substring(pos, pos + 1);
        String rep1 = map.get(oneChar);
        if( rep1 != null) {
            List<String> char1result = numberStrToStrings(number, pos + 1, map);
            if (char1result.isEmpty()) {
                result.add(rep1);
            } else {
                for (String s : char1result) {
                    result.add(rep1 + s);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(numberStrToStrings("92020"));
    }
}
