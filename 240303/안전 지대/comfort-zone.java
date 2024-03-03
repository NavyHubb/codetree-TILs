import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int maxHeight;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        maxHeight = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int cnt = 0;
        int height = 0;
        // 안전구역의 갯수가 같다면 가장 작은 높이를 우선 채택
        for (int k = 1; k < maxHeight; k++) {
            int tmpCnt = solution(k);
            if (tmpCnt > cnt) {
                cnt = tmpCnt;
                height = k;
            }
        }

        System.out.println(height + " " + cnt);
    }

    public static int solution(int height) {
        visited = new boolean[N][M];

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] <= height) continue;
                if (visited[i][j]) continue;

                cnt++;
                dfs(i, j, height);
            }
        }

        return cnt;
    }

    public static void dfs(int i, int j, int height) {
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (ni < 0 || nj < 0 || ni >= N || nj >= M) continue;
            if (map[ni][nj] <= height) continue;
            if (visited[ni][nj]) continue;

            visited[ni][nj] = true;
            dfs(ni, nj, height);
        }
    }

}