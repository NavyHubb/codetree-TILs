import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String comm = st.nextToken();
            int n;
            switch (comm) {
                case "push_front" : 
                    n = Integer.parseInt(st.nextToken());
                    deque.addFirst(n);
                    break;
                case "push_back" : 
                    n = Integer.parseInt(st.nextToken());
                    deque.addLast(n);
                    break;
                case "pop_front" : 
                    System.out.println(deque.pollFirst());
                    break;
                case "pop_back" : 
                    System.out.println(deque.pollLast());
                    break;
                case "size" : 
                    System.out.println(deque.size());
                    break;
                case "front" : 
                    System.out.println(deque.peekFirst());
                    break;
                case "back" : 
                    System.out.println(deque.peekLast());
                    break;
                case "empty":
                    System.out.println(deque.isEmpty() ? 1 : 0);
            }
        }
    }
}