import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, maxCnt;
    static int[][] map;
    static List<Point> points;
    static int[] comb;
    static int[][] di = {{0, -2, 2, -1, 1}, {0, -1, 1, 0, 0}, {0, -1, -1, 1, 1}};
    static int[][] dj = {{0, 0, 0 ,0, 0}, {0, 0, 0, -1, 1}, {0, -1, 1, -1, 1}};
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    points.add(new Point(i, j));
                }
            }
        }

        comb = new int[points.size()];
        maxCnt = 0;

        DFS(0);
        System.out.println(maxCnt);
    }

    public static void DFS(int depth) {
        if (depth == points.size()) {
            maxCnt = Math.max(maxCnt, count());
            return;
        }

        for (int i = 0; i < 3; i++) {
            comb[depth] = i;
            DFS(depth+1);
        }
    }

    public static int count() {
        boolean[][] visited = new boolean[N][N];

        int cnt = 0;
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);

            for (int d = 0; d < 5; d++) {
                int ni = point.x + di[comb[i]][d];
                int nj = point.y + dj[comb[i]][d];

                if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if (visited[ni][nj]) continue;

                visited[ni][nj] = true;
                cnt++;
            }
        }

        return cnt;
    }

}