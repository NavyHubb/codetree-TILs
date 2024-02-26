import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> list;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        visited = new boolean[N][N];
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) continue;
                if (visited[i][j]) continue;

                BFS(i, j);
            }
        }

        System.out.println(list.size());
        Collections.sort(list);
        for (int i : list) {
            System.out.println(i);
        }
    }

    static void BFS(int si, int sj) {
        Queue<int[]> que = new ArrayDeque<>();

        que.add(new int[]{si, sj});
        visited[si][sj] = true;

        int cnt = 0;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int i = cur[0], j = cur[1];
            cnt++;

            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if (map[ni][nj] == 0) continue;
                if (visited[ni][nj]) continue;

                visited[ni][nj] = true;
                que.add(new int[]{ni, nj});
            }
        }

        list.add(cnt);
    }

}