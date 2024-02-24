import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] dp = new int[1001];
        dp[3] = dp[2] = 1;

        for (int i = 4; i <= N; i++) {
            dp[i] = dp[i-2] + dp[i-3];
        }

        System.out.println(dp[N]);
    }
    
}