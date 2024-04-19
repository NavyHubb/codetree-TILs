import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int INF = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] coin = new int[N+1];
        for (int i = 1; i <= N; i++) {
            coin[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][4];  // dp[i][j] : i 위치까지 가는 데 1계단 오르기를 j번 사용했을 때의 최대 코인

        dp[1][1] = coin[1];
        dp[2][0] = coin[2];
        dp[2][2] = coin[1] + coin[2];

        for (int i = 3; i <= N; i++) {
            for (int j = 0; j <= 3; j++) {
                if (dp[i-2][j] != 0) {  // 2계단 전이 도달 가능한 위치인 경우
                    dp[i][j] = Math.max(dp[i][j], dp[i-2][j] + coin[i]);
                }

                if (j > 0 && dp[i-1][j-1] != 0) {  // 현재 위치에 1계단 오르기로 온 경우
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + coin[i]);
                }
            }
        }

        int result = 0;
        for (int j = 0; j <= 3; j++) {
            result = Math.max(result, dp[N][j]);
        }

        System.out.println(result);
    }

}