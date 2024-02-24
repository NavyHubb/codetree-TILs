import java.util.Scanner;

public class Main {

    static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        long[] dp = new long[1001];  // MOD를 두배 한것과 세배 한것을 더하면 int형의 범위를 초과할 수 있으므로 long으로 선언
        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i <= N; i++) {
            dp[i] = (dp[i-1]*2 + dp[i-2]*3) % MOD;
            for (int k = i-3; k >= 0; k--) {
                dp[i] = (dp[i] + dp[k]*2) % MOD;
            }
        }

        System.out.println(dp[N]);
    }

}