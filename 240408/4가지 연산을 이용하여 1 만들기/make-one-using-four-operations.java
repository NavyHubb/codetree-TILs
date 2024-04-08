import java.util.*;
import java.io.*;

// 연산의 횟수를 최소화 하여 숫자 1을 만들기 위한 연산 횟수
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();

        Queue<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[1_000_001];
        
        que.add(N);
        visited[N] = true;

        int depth = 2;
        while (!que.isEmpty()) {
            int size = que.size();

            while (size-- > 0) {
                int cur = que.poll();
                
                int next = 0;
                for (int i = 0; i < 3; i++) {
                    if (i == 0) {
                        next = cur + 1;

                        if (visited[next]) continue;

                        visited[next] = true;
                        que.add(next);
                    } else if (i == 1) {
                        if (cur % 2 == 0) {
                            next /= 2;

                            if (next == 1) {
                                System.out.print(depth);
                                System.exit(0);
                            }

                            if (visited[next]) continue;

                            visited[next] = true;
                            que.add(next);
                        }
                    } else {
                        if (cur % 3 == 0) {
                            next /= 3;

                            if (next == 1) {
                                System.out.print(depth);
                                System.exit(0);
                            }

                            if (visited[next]) continue;

                            visited[next] = true;
                            que.add(next);
                        }
                    }
                }
            }

            depth++;
        }
        
    }
}