import java.io.*;
import java.util.*;

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
        for (int i = 0; i < M; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int res = BFS();
        System.out.print(res);
    }

    public static int BFS() {
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        que.add(new int[]{0, 0});
        visited[0][0] = true;

        int cnt = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                int[] cur = que.poll();
                int i = cur[0], j = cur[1];

                if (i == N-1 && j == M-1) return cnt;

                for (int d = 0; d < 4; d++) {
                    int ni = i + di[d];
                    int nj = j + dj[d];

                    if (ni < 0 || nj < 0 || ni >= N || nj >= M) continue;
                    if (map[ni][nj] == 0) continue;
                    if (visited[ni][nj]) continue;

                    visited[ni][nj] = true;
                    que.add(new int[]{ni, nj});
                }
            }
            cnt++;
        }

        return -1;
    }
}