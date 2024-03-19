import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();

        int min = 1_000_000_000;

        // A -> B
        min = Math.min(min, getDist(A, B));

        // A -> x -> y -> B
        min = Math.min(min, getDist(A, x) + getDist(y, B));

        // A -> y -> x -> B
        min = Math.min(min, getDist(A, y) + getDist(x, B));

        System.out.println(min);
    }

    public static int getDist(int i, int j) {
        return Math.abs(i-j);
    }
}