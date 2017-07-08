
public class ThreadsComLambda {

	public static void main(String[] args) {

		Thread barra = new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				System.out.println("Barra de progresso...");
			}
		});

		Thread copia = new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				System.out.println("Copiando...");
			}
		});

		barra.start();
		copia.start();

	}

}