import java.util.Scanner;

public class Main {

    static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] dp = new int[1001];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 7;

        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i-1]*2%MOD + dp[i-2]*4%MOD) % MOD;
        }

        System.out.println(dp[N]);
    }

}