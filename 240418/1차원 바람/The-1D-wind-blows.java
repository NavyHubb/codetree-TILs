import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, Q;
    static int[][] map;
    static final int LEFT = -1;
    static final int RIGHT = 1;

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
            char d = st.nextToken().charAt(0);

            solve(r, d == 'L' ? RIGHT : LEFT);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void solve(int startRow, int startDir) {
        // 시작행 쉬프트
        shift(startRow, startDir);


        // 시작행의 위쪽 행들 탐색
        for (int r = startRow, d = startDir*(-1); r > 0; r--) {
            if (hasSameNumber(r, r-1)) {
                shift(r-1, d);
                d *= -1;
            } else {
                break;
            }
        }

        // 시작행의 아래쪽 행들 탐색
        for (int r = startRow, d = startDir*(-1); r < N-1; r++) {
            if (hasSameNumber(r, r+1)) {
                shift(r+1, d);
                d *= -1;
            } else {
                break;
            }
        }
    }

    public static void shift(int row, int dir) {
        if (dir == LEFT) {
            int tmp = map[row][0];
            for (int i = 0; i < M-1; i++) {
                map[row][i] = map[row][i+1];
            }
            map[row][M-1] = tmp;
        } else {
            int tmp = map[row][M-1];
            for (int i = M-1; i > 0; i--) {
                map[row][i] = map[row][i-1];
            }
            map[row][0] = tmp;
        }
    }

    public static boolean hasSameNumber(int r1, int r2) {
        for (int i = 0; i < M; i++) {
            if (map[r1][i] == map[r2][i]) return  true;
        }
        return  false;
    }

}