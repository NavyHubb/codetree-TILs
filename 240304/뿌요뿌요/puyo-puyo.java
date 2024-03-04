import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }
        visited = new boolean[N][N];
        int blockCnt = 0;
        int maxSize = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;

                int cnt = BFS(i, j);
                maxSize = Math.max(maxSize, cnt);

                if (cnt >= 4) {
                    blockCnt++;
                }
            }
        }

        System.out.println(blockCnt + " " + maxSize);
    }

    public static int BFS(int si, int sj) {
        Queue<int[]> que = new ArrayDeque<>();

        que.add(new int[]{si, sj});
        visited[si][sj] = true;

        int target = map[si][sj];
        int cnt = 0;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int i = cur[0], j = cur[1];
            cnt++;

            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if (map[ni][nj] != target) continue;
                if (visited[ni][nj]) continue;

                visited[ni][nj] = true;
                que.add(new int[]{ni, nj});
            }
        }

        return cnt;
    }

}