import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 가능한 기울어진 직사각형들 중 해당 직사각형을 이루는 지점에 적힌 숫자들의 합이 최대가 되도록 하는 프로그램을 작성하라
 */
public class Main {

    static int N;
    static int[][] map;
    static int[] di = {-1, -1, 1, 1};
    static int[] dj = {1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                max = Math.max(max, solution(i, j));
            }
        }

        System.out.println(max);
    }

    public static int solution(int si, int sj) {
        int max = 0;

        Loop1: for (int w = 1; w < N; w++) {
            Loop2: for (int h = 1; h < N; h++) {
                // 변수 초기화
                int i = si, j = sj, sum = map[si][sj];

                // 3번 방향 탐색
                for (int k = 0; k < w; k++) {
                    i += di[2];
                    j += dj[2];

                    if (j < 0 || i >= N) continue Loop1;

                    sum += map[i][j];
                }

                // 4번 방향 탐색
                for (int k = 0; k < h; k++) {
                    i += di[3];
                    j += dj[3];

                    if (i >= N || j >= N) continue Loop2;

                    sum += map[i][j];
                }

                // 1번 방향 탐색
                for (int k = 0; k < w; k++) {
                    i += di[0];
                    j += dj[0];

                    if (i < 0 || j >= N) continue Loop2;

                    sum += map[i][j];
                }

                // 2번 방향 탐색
                for (int k = 0; k < h-1; k++) {
                    i += di[1];
                    j += dj[1];

                    if (i < 0 || j < 0) continue Loop2;

                    sum += map[i][j];
                }

                max = Math.max(max, sum);
            }
        }

        return max;
    }

}