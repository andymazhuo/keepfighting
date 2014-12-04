import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Predicate;


/**
 * http://grepcode.com/file/repository.springsource.com/org.apache.commons/com.springsource.org.apache.commons.collections/2.1.1/org/apache/commons/collections/iterators/FilterIterator.java
 * @author andyma
 *
 * @param <T>
 */
public class FilterIterator<T> implements Iterator<T> {
	
	private T nextElement;
	private boolean hasNextElement;
	private Predicate<T> filter;
	private Iterator<T> it;
	
	public FilterIterator(Iterator<T> it, Predicate<T> f) {
		this.filter = f;
		this.it = it;
	}
	
	@Override
	public boolean hasNext() {
		if(hasNextElement) {
			return true;
		} else {
			return setNextElement();
		}
	}

	private boolean setNextElement() {
		while(it.hasNext()) {
			T temp = it.next();
			if(filter.apply(temp)) {
				hasNextElement = true;
				nextElement = temp;
				return true;
			}
		}
		return false;
	}

	@Override
	public T next() {
		if(!hasNextElement) {
			if(!setNextElement()) {
				return null;
			}
		}
		hasNextElement = false;
		return nextElement;
	}

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>();
		input.add(1);input.add(2);input.add(3);input.add(4);
		
		FilterIterator<Integer> fi = new FilterIterator<>(input.iterator(), new EvenFilter());
		while(fi.hasNext()) {
			System.out.println(fi.next());
		}
		
		List<String> input2 = new ArrayList<>();
		input2.add("abc");input2.add("bc"); input2.add("aaac");
		FilterIterator<String> fi2 = new FilterIterator<>(input2.iterator(), new Predicate<String>() {{}
					@Override
					public boolean apply(String arg0) {
						return arg0.startsWith("a");
					}});
		while(fi2.hasNext()) {
			System.out.println(fi2.next());
		}
 	}
}
