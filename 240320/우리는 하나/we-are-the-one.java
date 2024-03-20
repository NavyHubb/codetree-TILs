import java.util.*;
import java.io.*;

// 각 칸마다 도시마다의 높이가 주어진다
// k개 도시를 골라, 그 도시들로부터 갈 수 있는 서로 다른 도시 수를 최대화
// 이동은 도시간 높이차가 u 이상 d 이하인 경우에만 가능
// 1 <= k <= min(n, 3)
// 즉, 선택할 수 있는 도시의 최대 갯수는 3개 -> 완탐
public class Main {

    static int N, K, U, D;
    static int[][] map;
    static int[] comb;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static boolean[][] visited;
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb = new int[K];
        makeComb(0, 0);

        System.out.print(res);
    }

    public static void makeComb(int depth, int start) {
        if (depth == K) {
            res = Math.max(res, calc());
            return;
        }

        for (int i = start; i < N*N; i++) {
            comb[depth] = i;
            makeComb(depth+1, i+1);
        }
    }

    public static int calc() {
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        
        int cnt = 0;
        for (int idx : comb) {
            int si = idx / N;
            int sj = idx % N;

            visited[si][sj] = true;
            que.add(new int[]{si, sj});
            cnt++;

            while (!que.isEmpty()) {
                int[] cur = que.poll();
                int i = cur[0], j = cur[1];

                for (int d = 0; d < 4; d++) {
                    int ni = i + di[d];
                    int nj = j + dj[d];

                    if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;

                    int gap = Math.abs(map[i][j] - map[ni][nj]);
                    if (!(gap >= U && gap <= D)) continue;

                    if (ni == si && nj == sj) continue;
                    if (visited[ni][nj]) continue;

                    visited[ni][nj] = true;
                    que.add(new int[]{ni, nj});
                    cnt++;
                }
            }
        }
        
        return cnt;
    }

}