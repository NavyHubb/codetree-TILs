import java.io.*;
import java.util.*;

// 0 - 귤 없음
// 1 - 정상 귤
// 2 - 상한 귤
////
// -1 처음부터 비어있던 칸
// -2 끝까지 상하지 않는 칸
public class Main {

    static int n, k;
    static int[][] map, res;
    static Queue<int[]> que = new LinkedList<>();
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        res = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                
                if (map[i][j] == 0) {
                    res[i][j] = -1;
                } else if (map[i][j] == 1) {   // 정상 귤
                    res[i][j] = -2;  // 일단 끝까지 상하지 않는 상태로 세팅해놓고 상하는 경우 갱신
                } else {  // 상한 귤
                    que.add(new int[]{i, j});
                }
            }
        }

        int time = 1;
        while (!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                int[] cur = que.poll();
                int i = cur[0], j = cur[1];

                for (int d = 0; d < 4; d++) {
                    int ni = i + di[d];
                    int nj = j + dj[d];

                    if (ni < 0 || nj < 0 || ni >= n || nj >= n) continue;

                    if (map[ni][nj] == 1) {
                        map[ni][nj] = 2;
                        res[ni][nj] = time;

                        que.add(new int[]{ni, nj});
                    }
                }
            }
            time++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(res[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

}