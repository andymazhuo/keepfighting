package Google;

import java.util.Random;

public class Utility {

	public Utility() {
		// TODO Auto-generated constructor stub
	}

	public static int[][] generateMatrix(int r, int c) {
		Random rand = new Random();
		int[][] ans = new int[r][c];
		for(int i=0; i<r; i++) {
			for(int m=0; m<c; m++) {
				ans[i][m] = rand.nextInt(10);
			}
		}
		return ans;
	}
	
	public static String printMatrix(int[][] matrix) {
		StringBuilder sb = new StringBuilder();
		for(int[] i : matrix) {
			for(int t : i) {
				sb.append(t);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
