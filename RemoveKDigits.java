package com.madhukar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by madhukm on 8/23/17.
 */
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        if( num == null) {
            return "0";
        }
        if( k == num.length()) {
            return "0";
        }
        int i =1;
        List<Integer> indexToRemove = new ArrayList();
        while(i < num.length() && k > 0) {
            if(num.charAt(i) < num.charAt(i - 1)) {
                indexToRemove.add(i-1);
                num = removeCharAtIdx(num, i-1);
                i=Math.max(i-2, 0);
                k--;
            }
            i++;
        }
        if(k != 0 && k < num.length()) {
            num = num.substring(0, num.length()-k);
        }
        return num.length() == 0?"0":num;
    }

    public String removeCharAtIdx(String num, int idx) {
        String newNum = num.substring(0, idx) + num.substring(idx+1,num.length());
        while( newNum.charAt(0) == '0' && newNum.length() > 1) {
            newNum = newNum.substring(1, newNum.length());
        }
        return newNum;
    }
}
