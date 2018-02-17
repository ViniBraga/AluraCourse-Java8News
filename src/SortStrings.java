import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class SortStrings {

	public static void main(String[] args) {

		List<String> words = new ArrayList<>();
		words.add("alura online");
		words.add("editora casa do codigo");
		words.add("caelum");

		System.out.println(words);

		Collections.sort(words);
		System.out.println(words);

		// --Sort by size

		// sort is a default method implemented inside the interface List
		// (News of Java 8)
		Comparator<String> comparator = new ComparatorBySize();
		words.sort(comparator);

		System.out.println(words);
		System.out.println();

		// --Going through the lists with the interface Consumer

		// forEach is also a default method of the interface Iterable
		Consumer<String> consumer1 = new PrintOnLine();
		words.forEach(consumer1);
		System.out.println();

		// Another way, initializing as an anonymous class
		Consumer<String> consumer2 = new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		};
		words.forEach(consumer2);
		System.out.println();

		// Another way, implementing straight in the argument passed through forEach
		words.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
		System.out.println();

		// Another way, using the new feature called lambda:
		words.forEach(p -> {
			System.out.println(p);
		});
		System.out.println();
		//  Since you only have one argument and one line of code,
		// so it could be words.forEach(p -> System.out.println(p));

		// --Sort of lists also can be run with lambda

		//  implementing straight in the argument passed by parameter:
		words.sort(new ComparatorBySize());

		// OR
		words.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() > o2.length()) {
					return 1;
				} else if (o1.length() < o2.length()) {
					return -1;
				}
				return 0;
			}
		});

		// Lambda
		words.sort((s1, s2) -> {
			if (s1.length() > s2.length()) {
				return 1;
			} else if (s1.length() < s2.length()) {
				return -1;
			}
			return 0;
		});
		// Improving:
		words.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

		// Improving further:
		words.sort(Comparator.comparing(s -> s.length()));// Factory of comparators
		
		// Also can be written:
		Function<String, Integer> callback = s -> s.length();
		// Or Function<String, Integer> callback = String::length; //Reference method
		Comparator<String> comp = Comparator.comparing(callback);
		words.sort(comp);
		
		// Better:
		words.sort(Comparator.comparing(String::length));
		
		
		// --Going through with reference method
		words.forEach(System.out::println);
		//Reference methods can only be used when you need to invoke a single method
		//passing only one argument. Otherwise, solutions with lambda become better
		//(Or even solutions with anonymous class, if the implementation of the method is very large,
		//so it is not advantageous to use the new lambda and reference features)
		
		System.out.println();
	}

}

class ComparatorBySize implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		if (s1.length() > s2.length()) {
			return 1;
		} else if (s1.length() < s2.length()) {
			return -1;
		}
		return 0;
	}

}

class PrintOnLine implements Consumer<String> {

	@Override
	public void accept(String t) {
		System.out.println(t);
	}

}