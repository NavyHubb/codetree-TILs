import java.util.*;
import java.io.*;

public class Main {

    static int N, M, max;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        max = 0;
        map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }

        solution();
        System.out.print(max == 0 ? -1 : max);
    }

    public static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int x = i; x < N; x++) {
                    for (int y = j; y < M; y++) {
                        if (check(i, j, x, y)) {
                            max = Math.max(max, (x-i+1) * (y-j+1));
                        }
                    }
                }
            }
        }
    }

    public static boolean check(int i, int j, int x, int y) {
        for (int k = i; k <= x; k++) {
            for (int l = j; l <= y; l++) {
                if (map[k][l] <= 0) return false;
            }
        }
        return true;
    }

}