import java.util.*;
import java.io.*;

// 연산의 횟수를 최소화 하여 숫자 1을 만들기 위한 연산 횟수
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();

        if (N == 1) {
            System.out.print(0);
            System.exit(0);
        }

        Queue<int[]> que = new LinkedList<>();
        boolean[] visited = new boolean[5_000_000];
        
        que.add(new int[]{N, 0});
        visited[N] = true;

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int n = cur[0], cnt = cur[1];
                
                int next = n;
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        next = n + 1;

                        if (visited[next]) continue;

                        visited[next] = true;
                        que.add(new int[]{next, cnt+1});
                    } else if (i == 1) {
                        next = n - 1;
                        
                        if (next < 0) continue;
                        if (visited[next]) continue;

                        if (next == 1) {
                            System.out.print(cnt+1);
                            System.exit(0);
                        }

                        visited[next] = true;
                        que.add(new int[]{next, cnt+1});
                    } else if (i == 2) {
                        if (n % 2 == 0) {
                            next = n/2;

                            if (next == 1) {
                                System.out.print(cnt+1);
                                System.exit(0);
                            }

                            if (visited[next]) continue;

                            visited[next] = true;
                            que.add(new int[]{next, cnt+1});
                        }
                    } else {
                        if (n % 3 == 0) {
                            next = n/3;

                            if (next == 1) {
                                System.out.print(cnt+1);
                                System.exit(0);
                            }

                            if (visited[next]) continue;

                            visited[next] = true;
                            que.add(new int[]{next, cnt+1});
                        }
                    }
                }
        }
        
    }
}