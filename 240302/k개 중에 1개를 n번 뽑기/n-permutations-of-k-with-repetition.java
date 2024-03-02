import java.util.Scanner;

public class Main {

    static int K, N;
    static int[] output;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        K = sc.nextInt();
        N = sc.nextInt();
        output = new int[N];

        solution(0);
        System.out.print(sb);
    }

    public static void solution(int depth) {
        if (depth == N) {
            for (int i : output) {
                sb.append(i).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= K; i++) {
            output[depth] = i;
            solution(depth+1);
        }
    }
}