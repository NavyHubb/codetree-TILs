import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] di = {1, 0};  // 아래, 오른쪽
    static int[] dj = {0, 1};

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
        visited = new boolean[N][M];

        visited[0][0] = true;
        DFS(0, 0);
        System.out.print(0);
    }

    public static void DFS(int i, int j) {
        if (i == N-1 && j == M-1) {
            System.out.print(1);
            System.exit(0);
        }

        for (int d = 0; d < 2; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (ni < 0 || nj < 0 || ni >= N || nj >= M) continue;
            if (map[ni][nj] == 0) continue;
            if (visited[ni][nj]) continue;

            visited[ni][nj] = true;
            DFS(ni, nj);
        }
    }

}