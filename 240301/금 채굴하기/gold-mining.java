import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 마름모 모양이란 특정 중심점을 기준으로 K번 이내로 상하좌우의 인접한 곳으로 이동하는 걸 반복했을 때 갈 수 있는 모든 영역
 */
public class Main {

    static int N, M;
    static int[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }

        int res = 0;
        for (int k = 0; k <= N/2; k++) {
            for (int i = 0; i < N; i++) {
                if (i - k < 0 || i + k >= N) continue;
                for (int j = 0; j < N; j++) {
                    if (j - k < 0 || j + k >= N) continue;

                    res = Math.max(res, BFS(i, j, k));
                }
            }
        }

        System.out.println(res);
    }

    public static int BFS(int si, int sj, int k) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> que = new LinkedList<>();

        que.add(new int[]{si, sj});
        visited[si][sj] = true;

        int cost = k*k + (k+1)*(k+1);
        int cnt = 0;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int i = cur[0], j = cur[1];

            if (map[i][j] == 1) cnt++;

            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if (visited[ni][nj]) continue;
                if (Math.abs(si-ni) + Math.abs(sj-nj) > k) continue;

                visited[ni][nj] = true;
                que.add(new int[]{ni, nj});
            }
        }

        return cnt*M >= cost ? cnt : 0;
    }

}