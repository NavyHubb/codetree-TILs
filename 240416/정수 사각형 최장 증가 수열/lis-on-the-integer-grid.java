import java.util.*;
import java.io.*;

public class Main {

    static int N, max;
    static int[][] map;
    static int[][] dp;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp = new int[N][N];

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = 0;
                dfs(i, j, 1);

                dp[i][j] = max;
                result = Math.max(result, dp[i][j]);
            }
        }

        System.out.println(result);
    }

    public static void dfs(int i, int j, int cnt) {
        max = Math.max(max, cnt);

        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;

            if (map[ni][nj] > map[i][j]) {
                if (dp[ni][nj] == 0) {
                    dfs(ni, nj, cnt+1);
                } else {
                    max = Math.max(max, cnt+dp[ni][nj]-1);
                }
            }
        }
    }

}