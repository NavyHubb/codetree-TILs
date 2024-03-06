import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, M, max;
    static int[][] map;
    static List<Point> rocks;
    static List<Point> starts;
    static int[] comb;
    static boolean[] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());  // 시작점 개수
        M = Integer.parseInt(st.nextToken());  // 치워야할 돌 개수
        map = new int[N][N];
        rocks = new ArrayList<>();
        starts = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) rocks.add(new Point(i, j));
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            starts.add(new Point(x-1, y-1));
        }

        comb = new int[M];
        visited = new boolean[rocks.size()];
        max = 0;

        makeCombination(0, 0);
        System.out.println(max);
    }

    public static void makeCombination(int depth, int start) {
        if (depth == M) {
            max = Math.max(max, BFS());
            return;
        }

        for (int i = start; i < rocks.size(); i++) {
            comb[depth] = i;
            makeCombination(depth+1, i+1);
        }
    }

    public static int BFS() {
        int[][] tempMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, tempMap[i], 0, N);
        }

        for (int c : comb) {
            Point rock = rocks.get(c);
            tempMap[rock.x][rock.y] = 0;
        }

        Queue<Point> que = new ArrayDeque<>();
        boolean[][] mapVisited = new boolean[N][N];

        for (Point start : starts) {
            que.add(start);
            mapVisited[start.x][start.y] = true;
        }

        int cnt = 0;
        while (!que.isEmpty()) {
            Point cur = que.poll();
            cnt++;

            for (int d = 0; d < 4; d++) {
                int ni = cur.x + di[d];
                int nj = cur.y + dj[d];

                if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if (tempMap[ni][nj] == 1) continue;
                if (mapVisited[ni][nj]) continue;

                mapVisited[ni][nj] = true;
                que.add(new Point(ni, nj));
            }
        }

        return cnt;
    }

}