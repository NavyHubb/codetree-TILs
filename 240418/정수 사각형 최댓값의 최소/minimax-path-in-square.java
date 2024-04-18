import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 최솟값 이상의 수들만 써서 이동한다는 가정하에 경로상에 놓여있는 수들 중 최댓값을 최소화하는 전략 사용
public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int N;
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }

        dp = new int[N][N];
        dp[0][0] = map[0][0];
        for (int k = 1; k < N; k++) {
            dp[k][0] = Math.max(dp[k-1][0], map[k][0]);
            dp[0][k] = Math.max(dp[0][k-1], map[0][k]);
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                dp[i][j] = Math.max(Math.min(dp[i-1][j], dp[i][j-1]), map[i][j]);
            }
        }

        System.out.println(dp[N-1][N-1]);
    }

}