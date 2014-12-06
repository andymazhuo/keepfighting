package Google;
public class CoinProblem {

	/**
	 * 硬币问题是典型的dps问题 中间穿插着dp以提高效率
	   1）判断是不是存在
	   2）求出最小的个数
	   3）求出一共多少种不重复组合
       4）每种钱币有数量限制
       1和2可以用一维dp，3却不行，必须2维
	        经常的追加问题：用dp不是p的复杂度么？为什么还是np完全问题
	   http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
	 */
	public static void main(String[] args) {
		int[] input = {5, 10};	
		long startTime = System.nanoTime();
		System.out.println(isThereASolution(input, 10010));
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Total time in nano second: " + totalTime);		
	}

	/**
	 * Given a target to see if there is a solution for given candidates
	 * @param input positive and distinct integers
	 * @param target the positive integer
	 */
	public static boolean isThereASolution(int[] input, int target) {
		return isThereASolution(0, input, target);
	}

	private static boolean isThereASolution(int start, int[] input, int target) {
		//base cases
		if(target==0) return true;
		if(target<0) return false;
		for(int k=start; k<input.length; k++) {
			if(isThereASolution(k, input, target-input[k])) return true;
		}
		return false;
	}
}
