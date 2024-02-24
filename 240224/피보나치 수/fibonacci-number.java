import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        dp = new int[46];
        Arrays.fill(dp, -1);

        System.out.println(fib(N));
    }

    static int fib(int n) {
        if (dp[n] != -1) {
            return dp[n];
        }

        if (n <= 2) {
            dp[n] = 1;
        } else {
            dp[n] = fib(n-1) + fib(n-2);
        }

        return dp[n];
    }

}