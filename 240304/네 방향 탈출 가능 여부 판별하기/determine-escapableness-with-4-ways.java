import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
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

        System.out.println(BFS() ? 1 : 0);
    }

    public static boolean BFS() {
        Queue<int[]> que = new ArrayDeque<>();
        visited = new boolean[N][N];

        que.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int i = cur[0], j = cur[1];

            if (i == N-1 && j == M-1) return true;

            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if (map[ni][nj] != 1) continue;
                if (visited[ni][nj]) continue;

                visited[ni][nj] = true;
                que.add(new int[]{ni, nj});
            }
        }

        return false;
    }

}