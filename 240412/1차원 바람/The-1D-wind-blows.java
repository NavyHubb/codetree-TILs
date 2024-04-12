import java.util.*;
import java.io.*;

public class Main {

    static int N, M, Q;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            
            int r = Integer.parseInt(st.nextToken()) - 1;
            int d = 0;
            if (st.nextToken().equals("L")) {
                d = -1;
            } else {
                d = 1;
            }

            move(r, d);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public static void move(int R, int D) {
        Queue<int[]> que = new LinkedList<>();
        boolean[] visited = new boolean[N];

        que.add(new int[]{R, D});
        visited[R] = true;

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int r = cur[0], d = cur[1];

            // 현재 행 이동
            if (d == -1) {
                int tmp = map[r][M-1];
                for (int j = M-1; j > 0; j--) {
                    map[r][j] = map[r][j-1];
                }
                map[r][0] = tmp;
            } else {
                int tmp = map[r][0];
                for (int j = 0; j < M-1; j++) {
                    map[r][j] = map[r][j+1];
                }
                map[r][M-1] = tmp;
            }

            // 주변 행 탐색
            boolean flag = false;
            if (r > 1) {
                if (!visited[r-1]) {
                    visited[r-1] = true;
                    for (int j = 0; j < M; j++) {
                        if (map[r][j] == map[r-1][j]) {
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {
                        que.add(new int[]{r-1, -d});
                    }
                }
            } 

            if (r < N-1) {
                if (!visited[r+1]) {
                    visited[r+1] = true;
                    for (int j = 0; j < M; j++) {
                        if (map[r][j] == map[r+1][j]) {
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {
                        que.add(new int[]{r+1, -d});
                    }
                }
            }
        }
    }
}