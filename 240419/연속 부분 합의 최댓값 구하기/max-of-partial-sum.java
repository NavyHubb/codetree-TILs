import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        System.arraycopy(arr, 0, dp, 0, N);

        int result = arr[0];
        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);
    }

}