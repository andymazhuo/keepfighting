import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class StringProblems {

	public StringProblems() {
	}

	
	public static String minWindow(String S, String T) {
		int[] counts = buildCounts(T);
		
		int[] current = new int[256];

		Deque<Integer> d = new ArrayDeque<>();
		int total = 0;
		String ans = "";
		int min = Integer.MAX_VALUE;
		boolean first = true;

		for(int i=0; i<S.length(); i++) {
			char c = S.charAt(i);
			
			if(counts[c] != 0) {
				d.addLast(i);
				if(current[c] != 0) {
					if(current[c] < counts[c]) {
						total++;
					}
				} else {
					total++;
				}
				current[c]+=1;
			}

			if(total == T.length() && (first || S.charAt(d.getFirst())==c )) {
				while(counts[S.charAt(d.getFirst())]<current[S.charAt(d.getFirst())]) {
					current[S.charAt(d.pollFirst())] -= 1;
				}
				int tl = d.getLast()-d.getFirst()+1;
				if(min>tl) {
					min = tl;
					ans = S.substring(d.getFirst(), d.getLast()+1);
				}
				first = false;
			}
		}
		return ans;
    }

	private static int[] buildCounts(String t) {
		int[] counts = new int[256];
		for(char c : t.toCharArray()) {
			counts[c] += 1;
		}
		return counts;
	}
	
	public static void solve(char[][] board) {
        int rows = board.length;
        int columns = board[0].length;
        
        for(int i=0; i<columns; i++) {
            if(board[0][i] == 'O') dfs(board, 0, i);
        }

        for(int i=1; i<rows; i++) {
            if(board[i][0] == 'O') dfs(board, i, 0);
        }

        for(int i=0; i<columns; i++) {
            if(board[rows-1][i] == 'O') dfs(board, rows-1, i);
        }
        
        for(int i=1; i<rows; i++) {
            if(board[i][columns-1] == 'O') dfs(board, i, columns-1);
        }
        
        cleanup(board);
    }
    
    private static void cleanup(char[][] board) {
        int rows = board.length;
        int columns = board[0].length;
        for(int r=0; r<rows; r++) {
            for(int c=0; c<columns; c++) {
                if(board[r][c]=='M') {
                    board[r][c] = 'O';
                } else {
                    board[r][c] = 'X';
                }
            }
        }
    }

    private static void dfs(char[][] board, int row, int col) {
        if(row>=0 && col>=0 && row<board.length && col<board[0].length && board[row][col]=='O') {
            board[row][col] = 'M';
            dfs(board, row+1, col);
            dfs(board, row-1, col);
            dfs(board, row, col+1);
            dfs(board, row, col-1);
        }
    }
    
    public static int numDecodings(String s) {
        int first =0, second =1;
        for(int i=0; i<s.length(); i++) {
            int temp =0;
            if(isGood(s, i, i)) {
                temp += second;
            }
            if(isGood(s, i-1, i)) {
                temp += first;
            }
            first = second;
            second = temp;
        }
        return second;
    }
    
    private static boolean isGood(String s, int start, int end) {
        if(start<0) return false;
        if(start==end) {
            int temp = s.charAt(start) - '0';
            return temp>0 && temp<10;
        } else {
            int temp = Integer.parseInt(s.substring(start, end+1));
            return temp > 9 && temp<27;
        }
    }
    
    public static List<String> wordBreak(String s, Set<String> dict) {
        List<String> ans = new ArrayList<>();
        wordBreak(s, dict, new StringBuilder(), ans);
        return ans;
    }
    
    private static void wordBreak(String s, Set<String> dict, StringBuilder sb, List<String> ans) {
        if(s.length()==0) {
            ans.add(new String(sb.substring(0, sb.length()-1)));
        }
        for(int end=1; end<=s.length(); end++) {
            String temp = s.substring(0, end);
            if(dict.contains(temp)){
                sb.append(temp).append(" ");
                wordBreak(s.substring(end), dict, sb, ans);
                sb.delete(sb.length()-temp.length()-1, sb.length()+1);
            }
        }
    }
	
	public String addBinary(String a, String b) {
        int aIndex = a.length()-1;
        int bIndex = b.length()-1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while(aIndex>=0 || bIndex>=0 || carry>0) {
            int temp = carry;
            if(aIndex>=0) {
                temp += (a.charAt(aIndex) - '0');
                aIndex--;
            }
            if(bIndex>=0) {
                temp += (b.charAt(bIndex) - '0');
                bIndex--;
            }
            sb.insert(0, temp%2);
            carry = temp/2;
        }
        return sb.toString();
    }
}
