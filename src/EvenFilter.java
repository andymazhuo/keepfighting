import com.google.common.base.Predicate;

public class EvenFilter implements Predicate<Integer> {

	@Override
	public boolean apply(Integer number) {
		return (number%2) == 0;
	}
}
