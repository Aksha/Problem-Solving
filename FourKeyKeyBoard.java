
public class FourKeyKeyBoard {
    public static int maxChars(int n) {
        int[] counts = maxcharsHelper(n);
        return counts[0];

    }

    public static int[] maxcharsHelper(int n) {
        if(n<0) {
            return new int[]{0,0};
        }
        if(n>=0 && n<6) {
            return new int[]{n,0};
        }
        if(n==6) {
            return new int[]{n,3};
        }
        int[] previousCount = maxcharsHelper(n-1);
        int countUsingPreviousBuffer = previousCount[0] + previousCount[1];

        int[] previous3Count = maxcharsHelper(n-3);
        int countUsingPrevious3AndBuffer = 2 * previous3Count[0];

        if(countUsingPreviousBuffer > countUsingPrevious3AndBuffer) {
            return new int[]{ countUsingPreviousBuffer, previousCount[1]};
        } else {
            return new int[]{ countUsingPrevious3AndBuffer, previous3Count[0]};
        }
    }

    public static void main(String[] args) {
        System.out.println(maxChars(11));
    }
}
