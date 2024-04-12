import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] arr = new int[3][];
        for (int i = 0; i < 3; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }

        T %= N*3;
        for (int t = 0; t < T; t++) {
            int one = arr[0][N-1];
            int two = arr[1][N-1];
            int three = arr[2][N-1];

            for (int j = N-1; j > 0; j--) {
                arr[0][j] = arr[0][j-1];
                arr[1][j] = arr[1][j-1];
                arr[2][j] = arr[2][j-1];
            }
            arr[1][0] = one;
            arr[2][0] = two;
            arr[0][0] = three;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}