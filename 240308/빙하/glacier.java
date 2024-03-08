import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int lastCnt = 0;
    static int[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static boolean[][] visited;

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

        for (int t = 0; ; t++) {
            visited = new boolean[N][M];
            if (isFinish()) {  // 남은 빙하가 없는 경우
                System.out.println(t + " " + lastCnt);
                break;
            }

            BFS();
        }
    }

    public static boolean isFinish() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) return false;
            }
        }
        return true;
    }

    public static void BFS() {
        Queue<int[]> que = new LinkedList<>();

        que.add(new int[]{0, 0});
        visited[0][0] = true;

        int cnt = 0;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int i = cur[0], j = cur[1];
            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                if (ni < 0 || nj < 0 || ni >= N || nj >= M) continue;
                if (visited[ni][nj]) continue;

                visited[ni][nj] = true;
                if (map[ni][nj] == 0) {  // 물인 경우
                    que.add(new int[]{ni, nj});
                } else {
                    cnt++;
                    map[ni][nj] = 0;
                }
            }
        }

        lastCnt = cnt;
    }

}