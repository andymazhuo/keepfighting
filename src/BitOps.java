
public class BitOps {

	public BitOps() {
		// TODO Auto-generated constructor stub
	}

	public static int singleNumber(int[] A) {
        int ans = 0;
        for(int i=0; i<32; i++) {
            int sum = 0;
            for(int a : A) {
                int temp = (a >> i);
                sum += (temp & 1);
            }
            ans += ((sum%3)<<i);
        }
        return ans;
    }
	
	public static boolean isPalindrome(int x) {
		if(x<0) return false;
        int base = 10;
        while(x/base>=10) {
            base = base * 10;
        }

        while(x >= 10) {
            int first = x/base;
            int last = x%10;
            if(first == last) {
                x = (x%base)/10;
                base = base/100;
            } else {
                return false;
            }
        }
        return true;
    }
}
