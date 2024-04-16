import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }

        int[][] dp = new int[N][N];
        dp[0][N-1] = map[0][N-1];
        // 최우측열 세팅
        for (int i = 1; i < N; i++) {
            dp[i][N-1] = dp[i-1][N-1] + map[i][N-1];
        }
        // 최상측행 세팅
        for (int j = N-2; j >= 0; j--) {
            dp[0][j] = dp[0][j+1] + map[0][j];
        }

        for (int i = 1; i < N; i++) {
            for (int j = N-2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j+1]) + map[i][j];
            }
        }

        System.out.println(dp[N-1][0]);
    }

}