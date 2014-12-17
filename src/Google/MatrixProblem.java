package Google;

public class MatrixProblem {

	public MatrixProblem() {
	}
	
	public static String printMatrixInDiagonalOrder(int[][] matrix) {
		StringBuilder sb = new StringBuilder();
		int row = matrix.length, col = matrix[0].length;
		
		for(int r=0; r<row; r++) sb.append(printFrom(r, 0, matrix));
		for(int c=1; c<col; c++) sb.append(printFrom(row-1, c, matrix));
		
		return sb.toString();
	}

	private static String printFrom(int r, int c, int[][] matrix) {
		StringBuilder sb = new StringBuilder();
		int colLimit = matrix[0].length;
		while(r>=0 && c<colLimit) {
			sb.append(matrix[r--][c++]);
			sb.append(" ");
		}
		sb.append("\n");
		return sb.toString();
	}

	public static void main(String[] args) {
		int[][] input = Utility.generateMatrix(5, 4);
		System.out.println(Utility.printMatrix(input));
		System.out.println(printMatrixInDiagonalOrder(input));
	}
}
