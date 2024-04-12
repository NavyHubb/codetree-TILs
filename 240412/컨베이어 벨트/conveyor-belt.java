import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] arr = new int[2][];
        for (int i = 0; i < 2; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }

        // 1. 첫째 줄 가장 우측 수 저장
        int first = arr[0][N-1];

        // 2. 첫째 줄 우로 한칸씩 이동
        for (int j = N-1; j >= 1; j--) {
            arr[0][j] = arr[0][j-1];
        }

        // 3. 둘째 줄 가장 우측 수 저장
        int second = arr[1][N-1];  

        // 4. 둘째 줄 우로 한칸씩 이동
        for (int j = N-1; j >= 1; j--) {
            arr[1][j] = arr[1][j-1];
        }

        // 5. 1에서 저장한 수 둘째 줄 좌측에 대입
        arr[1][0] = first;

        // 6. 3에서 저장한 수 첫째 줄 좌측에 대입
        arr[0][0] = second;
     

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}