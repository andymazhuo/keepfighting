package Google;

import java.util.Iterator;

public class IteratorProblems {

	public IteratorProblems() {
	}

	private static class ArrayIterator implements Iterator<Integer> {
		private int currFeq;
		private int currIndex;
		private int[] input;
	
		public ArrayIterator(int[] input) {
			this.input = input;
			currIndex = 0;
			currFeq = currIndex != input.length ? input[currIndex] : 0;
		}

		@Override
		public boolean hasNext() {
			return currIndex != input.length;
		}

		@Override
		public Integer next() {
			int temp = input[currIndex+1];
			if(--currFeq == 0) {
				currIndex += 2;
				currFeq = currIndex != input.length ? input[currIndex] : 0;
			}
			return temp;
		}
		
	}
	
	public static void main(String[] args) {
		int[] input = {5,2};
		ArrayIterator ai = new ArrayIterator(input);
		while(ai.hasNext()) {
			System.out.println(ai.next());
		}
	}
}
