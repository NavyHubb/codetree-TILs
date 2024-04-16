import java.util.*;
import java.io.*;

public class Main {

    static int N, result;
    static int[][] map;
    static int[][] dp;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                pq.offer(new int[]{i, j, map[i][j]});
            }
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int i = cur[0], j = cur[1], num = cur[2];

            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;

                if (map[ni][nj] > num) {
                    dp[ni][nj] = Math.max(dp[ni][nj], dp[i][j] + 1);
                }
            }
        }

        result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result = Math.max(result, dp[i][j]);
            }
        }

        System.out.println(result);
    }

}