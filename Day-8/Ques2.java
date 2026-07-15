import java.io.*;
import java.util.*;

public class Ques2 {

    static class Solution {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] box = new boolean[9][10];

        int[][] empty = new int[81][2];
        int emptyCount = 0;

        public void solveSudoku(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    char ch = board[i][j];
                    if (ch == '.' || ch == '0') {
                        empty[emptyCount][0] = i;
                        empty[emptyCount][1] = j;
                        emptyCount++;
                    } else {
                        int num = ch - '0';
                        row[i][num] = true;
                        col[j][num] = true;
                        box[(i / 3) * 3 + (j / 3)][num] = true;
                    }
                }
            }
            solve(board, 0);
        }

        private boolean solve(char[][] board, int idx) {
            if (idx == emptyCount) return true;

            int best = idx;
            int bestChoices = 10;

            for (int k = idx; k < emptyCount; k++) {
                int r = empty[k][0];
                int c = empty[k][1];
                int b = (r / 3) * 3 + (c / 3);

                int choices = 0;
                for (int num = 1; num <= 9; num++) {
                    if (!row[r][num] && !col[c][num] && !box[b][num]) {
                        choices++;
                    }
                }

                if (choices < bestChoices) {
                    bestChoices = choices;
                    best = k;
                    if (choices == 1) break;
                }
            }

            if (bestChoices == 0) return false;

            swap(idx, best);

            int r = empty[idx][0];
            int c = empty[idx][1];
            int b = (r / 3) * 3 + (c / 3);

            for (int num = 1; num <= 9; num++) {
                if (!row[r][num] && !col[c][num] && !box[b][num]) {
                    board[r][c] = (char) (num + '0');
                    row[r][num] = true;
                    col[c][num] = true;
                    box[b][num] = true;

                    if (solve(board, idx + 1)) return true;

                    board[r][c] = '.';
                    row[r][num] = false;
                    col[c][num] = false;
                    box[b][num] = false;
                }
            }

            swap(idx, best);
            return false;
        }

        private void swap(int i, int j) {
            if (i == j) return;
            int tr = empty[i][0], tc = empty[i][1];
            empty[i][0] = empty[j][0];
            empty[i][1] = empty[j][1];
            empty[j][0] = tr;
            empty[j][1] = tc;
        }
    }

    static class FastScanner {
        private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st;

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        char[][] board = new char[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String token = fs.next();
                board[i][j] = token.charAt(0);
            }
        }

        Solution sol = new Solution();
        sol.solveSudoku(board);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
                if (j < 8) sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}