import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 0 - 이동 가능
 * 1 - 이동 불가능
 * 2 - 사람 있음
 * 3 - 비를 피할 수 있음
 *
 * 사람이 있던 칸이라면 해당 사람이 비를 피할 수 있는 공간까지 가는데 걸리는 최소시간을(비를 피할 수 없다면 -1을)
 * 사람이 있던 칸이 아니라면 0을 출력
 */
public class Main {

    static int N, H, M;
    static int[][] map;
    static int[][] res;
    static List<Point> people;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();
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

        N = Integer.parseInt(st.nextToken());  // 한변의 크기
        H = Integer.parseInt(st.nextToken());  // 사람의 수
        M = Integer.parseInt(st.nextToken());  // 비를 피할 수 있는 공간의 수
        map = new int[N][N];
        res = new int[N][N];
        people = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());

                if (n == 1) {  // 이동할 수 없는 칸인 경우
                    map[i][j] = 1;
                } else if (n == 2) {
                    people.add(new Point(i, j));
                } else if (n == 3) {  // 비를 피할 수 있는 칸인 경우
                    map[i][j] = 3;
                }
            }
        }

        for (Point person : people) {
            res[person.x][person.y] = BFS(person.x, person.y);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(res[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    public static int BFS(int si, int sj) {
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        que.add(new int[]{si, sj, 0});
        visited[si][sj] = true;

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int i = cur[0], j = cur[1], cnt = cur[2];

            if (map[i][j] == 3) return cnt;

            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if (map[ni][nj] == 1) continue;
                if (visited[ni][nj]) continue;

                visited[ni][nj] = true;
                que.add(new int[]{ni, nj, cnt+1});
            }
        }

        return -1;
    }
}