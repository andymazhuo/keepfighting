
public class DFS {

	public DFS() {
		// TODO Auto-generated constructor stub
	}
	
    public static int totalNQueens(int n) {
        int[] buffer = new int[n+1];
        int[] ans = new int[1];
        totalNQueens(ans, buffer, 1, n);
        return ans[0];
    }
    
    private static void totalNQueens(int[] ans, int[] buffer, int sofar, int n) {
        if(sofar>n) {
            ans[0]++;
        } else {
            for(int i=1; i<buffer.length; i++) {
                if(buffer[i] == 0) {
                    buffer[i] = sofar;
                    if(isGood(buffer, i)) {
                        totalNQueens(ans, buffer, sofar+1, n);
                    }
                    buffer[i] = 0;
                }
            }
        }
    }
    
    private static boolean isGood(int[] buffer, int target) {
        for(int i=1; i<buffer.length; i++) {
            if(i!=target && buffer[i]!=0) {
                if(Math.abs((target-i)/(buffer[target]-buffer[i]))==1) {
                    return false;
                }
            }
        }
        return true;
    }

}
