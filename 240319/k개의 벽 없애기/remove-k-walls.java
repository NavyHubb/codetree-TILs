import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[][] map;
    static int[][] R;
    static int[] comb;
    static int result = Integer.MIN_VALUE;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static List<Point> walls;
    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        walls = new ArrayList<>();
        comb = new int[K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    walls.add(new Point(i, j));
                }
            }
        }

        R = new int[2][2];
        for (int k = 0; k < 2; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken())-1;
            int j = Integer.parseInt(st.nextToken())-1;

            R[k][0] = i;
            R[k][1] = j;
        }

        makeComb(0, 0);
        System.out.print(result);
    }

    public static void makeComb(int depth, int start) {
        if (depth == K) {
            result = Math.max(result, BFS());
            return;
        }

        for (int i = start; i < walls.size(); i++) {
            comb[depth] = i;
            makeComb(depth+1, i+1);
        }
    }

    public static int BFS() {
        // comb로 선정된 벽 부수기
        int[][] map_copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, map_copy[i], 0, N);
        }
        for (int k : comb) {
            Point target = walls.get(k);
            map_copy[target.i][target.j] = 0;
        }

        Queue<Point> que = new ArrayDeque<>();
        int[][] visited = new int[N][N];

        int start_i = R[0][0];
        int start_j = R[0][1];
        int end_i = R[1][0];
        int end_j = R[1][1];

        que.add(new Point(start_i, start_j));
        visited[start_i][start_j] = 1;  // 마지막에 1 다시 빼주기

        while (!que.isEmpty()) {
            Point cur = que.poll();

            if (cur.i == end_i && cur.j == end_j) return visited[end_i][end_j]-1;

            for (int d = 0; d < 4; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if (map_copy[ni][nj] == 1) continue;  // 벽인 경우
                if (visited[ni][nj] > 0) continue;  // 이미 방문한 곳인 경우

                visited[ni][nj] = visited[cur.i][cur.j] + 1;
                que.add(new Point(ni, nj));
            }
        }

        return -1;
    }

}