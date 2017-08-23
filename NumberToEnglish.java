

import java.util.ArrayList;
import java.util.List;


public class NumberToEnglish {

    /*
4 -> "four"
45 -> "fourty five"
451 -> "four hundred fifty one"
482910231 -> "four hundred eighty two million nine hundred ten thousand two hundred thirty one"
1, 034, 321

1) traverse from right to the left
2) get number.
3) look up dictonary for the string of that.
4) for next look up the the tens represent.
5) similarly for the thoushand
6)

*/

    static String intToEnglish(int value) { // 2 pow 32:
        String[] places = new String[] {"", "thousand", "million", "billion"};
        List<Integer> partionedChunks = getSplits(value); // reverse order
        StringBuilder number = new StringBuilder();
        for(int i =0;i< partionedChunks.size(); i++)  { // 482 910 231
            String part = toEnglish(partionedChunks.get(0));
            number.insert(0, places[i]).insert(0, part);
        }
        return number.toString().trim();
    }


    static String toEnglish(int value) {   // 231
        String[][] place = new String[][] {
                {"", "one ", "two ", "three "},
                {"", "ten ", "twenty ", "thirty ", "forty "}
        };
        int pos = 0;
        StringBuilder number = new StringBuilder();
        while(value > 0) {
            int reminder = value % 10;  // 1,3
            value = value / 10;         //23, 2
            if( pos == 2 ) {
                number.insert(0, "hundred ");
                number.insert(0, place[0][reminder]);
                break;
            }
            number.insert(0, place[pos][reminder]); // "two ", "hundred" "thirty " "one "
            pos++;
        }
        return number.toString();
    }


    static List<Integer> getSplits(int value) { // 482 910 231
        List<Integer> parts = new ArrayList<>();
        while(value >0) {
            int rem = value % 1000;
            parts.add(rem);
            value = value /1000;
        }
        return parts;
    }

    public static void main(String[] args) {
        System.out.println(intToEnglish(123));
    }
}
