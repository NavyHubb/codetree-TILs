import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int max;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][];
        max = 0;
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j,-1, -1, 1, map[i][j]);
            }
        }

        System.out.println(max);
    }

    /**
     * 
     * @param i 현재 위치의 행 인덱스
     * @param j 현재 위치의 열 인덱스
     * @param pi 이전 위치의 행 인덱스
     * @param pj 이전 위치의 열 인덱스
     * @param depth 탐색한 블록 갯수
     * @param sum 탐색한 블록들의 합
     */
    public static void dfs(int i, int j, int pi, int pj, int depth, int sum) {
        if (depth >= 3) {
            max = Math.max(max, sum);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (ni == pi && nj == pj) continue;
            if (isOutOfRange(ni, nj)) continue;

            dfs(ni, nj, i, j, depth+1, sum+map[ni][nj]);
        }
    }

    public static boolean isOutOfRange(int i, int j) {
        return i < 0 || j < 0 || i >= N || j >= M;
    }

}