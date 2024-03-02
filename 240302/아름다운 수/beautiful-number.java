import java.util.Scanner;

public class Main {

    static int N;
    static int cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        solution(0);
        System.out.print(cnt);
    }

    public static void solution(int depth) {
        if (depth == N) {
            cnt++;
            return;
        }

        for (int i = 1; i <= 4; i++) {
            if (depth + i > N) break;

            solution(depth+i);
        }
    }
}