import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[][] map;
    static int[] max;
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }

        st = new StringTokenizer(br.readLine());
        int i = Integer.parseInt(st.nextToken())-1;
        int j = Integer.parseInt(st.nextToken())-1;

        max = new int[]{i, j, 0};
        for (int k = 0; k < K; k++) {
            BFS(max[0], max[1]);

            if (max[0] == -1) break;
        }

        int ri = max[0]+1;
        int rj = max[1]+1;
        System.out.print(ri + " " + rj);
    }

    // 시작위치의 값보다 작은 곳으로만 이동 가능
    // 이동 가능한 칸들 중 최댓값으로 이동
    // 조건을 만족하는 칸이 여러 개일 경우, 행 번호 작게, 열 번호 작게 우선
    // 이렇게 이동하는 것을 k번 반복한 후의 위치를 구하라
    // 아직 k번을 반복하지 못했더라도, 더 이상 새로 이동할 위치가 없다면 종료
    public static void BFS(int si, int sj) {
        boolean result = false;

        Queue<int[]> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        int x = map[si][sj];
        max = new int[]{-1, -1, -1};

        que.add(new int[]{si, sj});
        visited[si][sj] = true;

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int i = cur[0], j = cur[1];

            if (map[i][j] > max[2] && map[i][j] < map[si][sj]) {  // 위치 갱신
                max = new int[]{i, j, map[i][j]};
            } else if (map[i][j] == max[2]) {  // 위치 비교 및 갱신
                if (i < max[0]) {  // 행이 더 작은 경우
                    max[0] = i;
                    max[1] = j;
                } else if (i == max[0]) {  // 행이 같은 경우
                    if (j < max[1]) {
                        max[1] = j;
                    }
                }
            }

            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if (map[ni][nj] >= x) continue;
                if (visited[ni][nj]) continue;

                visited[ni][nj] = true;
                que.add(new int[]{ni, nj});
            }
        }
    }

}