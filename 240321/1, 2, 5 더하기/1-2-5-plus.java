import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] dp = new int[n+1];

        dp[0] = 1;
        
        int[] nums = {1, 2, 5};
        for (int i = 1; i <= n; i++) {
            for (int num : nums) {
                if (num > i) break;

                dp[i] += dp[i-num] % 10_007;
            }
            dp[i] %= 10_007;
        }

        System.out.print(dp[n]);
    }
}