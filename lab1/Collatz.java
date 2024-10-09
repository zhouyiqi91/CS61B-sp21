/** Class that prints the Collatz sequence starting from a given number.
 *  @author YOUR NAME HERE
 */
public class Collatz {

    /** returns the next number of Collatz sequence  */
    public static int nextNumber(int n) {
        if (n==1) return 1;
        if (n % 2 == 0) {
            return n/2;
        } else {
            return n*3+1;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");
        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
    }
}

