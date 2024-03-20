import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = sc.nextInt();
        }
        Arrays.sort(coins);  // 오름차순 정렬

        int[] dp = new int[M+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        Loop: for (int i = 1; i <= M; i++) {
            for (int coin : coins) {
                if (i < coin) continue Loop;

                if (dp[i-coin] != INF) {
                    dp[i] = Math.min(dp[i], dp[i-coin]+1);
                }
            }
        }

        System.out.println(dp[M] == INF ? -1 : dp[M]);
    }
}