import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Line> lines;
    static class Line implements Comparable<Line> {
        int left, right;

        public Line(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Line o) {
            if (right == o.right) {
                return left - o.left;
            }
            return right - o.right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        lines = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            lines.add(new Line(left, right));
        }

        Collections.sort(lines);

        int cnt = 0;
        int tmp = 0;
        for (Line line : lines) {
            if (line.left > tmp) {
                cnt++;
                tmp = line.right;
            }
        }

        System.out.println(cnt);
    }

}