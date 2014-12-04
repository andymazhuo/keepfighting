import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 */


public class HelloWorld {

	public HelloWorld() {}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(1000, new Comparator<Integer>() {  
			   
            public int compare(Integer w1, Integer w2) {                         
                return w1.compareTo(w2);  
            }      
        });  
                      
         pQueue.add(12);  
         pQueue.add(1);   
         pQueue.add(5);  
         pQueue.add(22);  
         pQueue.add(3);  
         pQueue.add(2);  
         pQueue.add(124);  
         pQueue.add(14);  
         pQueue.add(111);  
         pQueue.add(9);  
         pQueue.add(30);  
          
        Object[] ar = pQueue.toArray();  
        for (int i = 0; i< ar.length; i++){  
            System.out.println(ar[i].toString());  
        } 
	}
	
	public static int trap(int[] A) {
        //what decides the volumn of water
        int[] maxFromLeft = new int[A.length];
        int max = A[0];
        for(int i=0; i<A.length; i++) {
            max = Math.max(max, A[i]);
            maxFromLeft[i] = max;
        }
        
        int[] maxFromRight = new int[A.length];
        max = A[A.length-1];
        for(int i=A.length-1; i>=0; i--) {
            max = Math.max(max, A[i]);
            maxFromRight[i] = max;
        }
        
        int ans = 0;
        
        for(int i=0; i<A.length; i++) {
            ans += Math.max(0,Math.min(maxFromLeft[i], maxFromRight[i])-A[i]);
        }
        return ans;
    }

}
