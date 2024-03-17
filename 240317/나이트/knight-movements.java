import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] loc;
    static int[] di = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dj = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        loc = new int[2][2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            loc[i/2][i%2] = Integer.parseInt(st.nextToken()) - 1;
        }

        System.out.println(BFS());
    }

    public static int BFS() {
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        que.add(new int[]{loc[0][0], loc[0][1], 0});
        visited[loc[0][0]][loc[0][1]] = true;

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int i = cur[0], j = cur[1], cnt = cur[2];

            if (i == loc[1][0] && j == loc[1][1]) return cnt;

            for (int d = 0; d < 8; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if (visited[ni][nj]) continue;

                visited[ni][nj] = true;
                que.add(new int[]{ni, nj, cnt+1});
            }
        }

        return -1;
    }

}