
public class ThreadsWithLambda {

	public static void main(String[] args) {

		Thread bar = new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				System.out.println("Progress bar...");
			}
		});

		Thread copy = new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				System.out.println("Copying...");
			}
		});

		bar.start();
		copy.start();

	}

}