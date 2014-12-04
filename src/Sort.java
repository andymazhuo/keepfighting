import java.util.Arrays;


public class Sort {

	public static int[] topDownMergeSort(int[] input) { 
		return mergeSort(0, input.length-1, input);
	}

	private static int[] mergeSort(int start, int end, int[] input) {
		if(start==end) return new int[]{input[start]};
		int middle = (start+end)/2;
		int[] left = mergeSort(start, middle, input);
		int[] right = mergeSort(middle+1, end, input);
		return merge(left, right);
	}

	private static int[] merge(int[] left, int[] right) {
		int[] result = new int[left.length + right.length];
		int iLeft = 0, iRight = 0;
		for(int i=0; i<result.length; i++) {
			if(iRight==right.length) {
				result[i] = left[iLeft++];
			} else if(iLeft==left.length) {
				result[i] = right[iRight++];
			} else if(left[iLeft]>right[iRight]) {
				result[i] = right[iRight++];
			} else {
				result[i] = left[iLeft++];
			}
		}
		return result;
	}
	
	public static void bottomUpMergeSort(int[] input) {
		for(int w=1; w<input.length; w=w<<1) {
			for(int start=0; start<input.length; start+=2*w) {
				bottomUpMerge(input, start, start+w-1, start+w, start+2*w-1);
			}
		}
	}

	private static void bottomUpMerge(int[] input, int leftStart, int leftEnd, int rightStart, int rightEnd) {
		int limit = input.length-1;
		if(leftEnd>=limit) return;
		rightEnd = Math.min(rightEnd, limit);
		int start = leftStart, end = rightEnd;
		int[] temp = new int[rightEnd-leftStart+1];
		for(int i=0; i<temp.length; i++) {
			if(leftStart>leftEnd) {
				temp[i] = input[rightStart++];
			} else if(rightStart>rightEnd) {
				temp[i] = input[leftStart++];
			} else if(input[leftStart]<input[rightStart]) {
				temp[i] = input[leftStart++];
			} else {
				temp[i] = input[rightStart++];
			}
		}
		for(int i=start; i<=end; i++) input[i] = temp[i-start];
	}
	
	public static void quickSort(int[] input, int iLeft, int iRight) {
		if(iLeft < iRight) {
			int p = partition(input, iLeft, iRight, iLeft);
			quickSort(input, iLeft, p-1);
			quickSort(input, p+1, iRight);
		}
	}
	
	public static int partition(int[] input, int iLeft, int iRight, int pivot) {
		int p = input[pivot], end = iRight;
		swap(input, iRight--, pivot);
		while(iLeft<=iRight) {
			if(input[iLeft]<=p) {
				iLeft++;
			} else {
				swap(input, iLeft, iRight--);
			}
		}
		swap(input, iLeft, end);
		return iLeft;
	}
	
	private static void swap(int[] input, int f, int t) {
		int temp = input[f];
		input[f] = input[t];
		input[t] = temp;
	}

	public static void main(String[] args) {
		int[] input = new int[]{3,2,1,-1,0,4,5,1,2,3,4};
		quickSort(input, 0, input.length-1);
		System.out.print(Arrays.toString(input));
	}
}
