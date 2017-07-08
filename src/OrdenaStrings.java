import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class OrdenaStrings {

	public static void main(String[] args) {

		List<String> palavras = new ArrayList<>();
		palavras.add("alura online");
		palavras.add("editora casa do codigo");
		palavras.add("caelum");

		System.out.println(palavras);

		Collections.sort(palavras);
		System.out.println(palavras);

		// --Ordenando por tamanho

		// sort é um default method implementado dentro da interface List
		// (novidade do Java 8)
		Comparator<String> comparador = new ComparadorPorTamanho();
		palavras.sort(comparador);

		System.out.println(palavras);
		System.out.println();

		// --Percorrendo Listas com a interface Consumer

		// forEach também é um default method da interface Iterable
		Consumer<String> consumidor1 = new ImprimeNaLinha();
		palavras.forEach(consumidor1);
		System.out.println();

		// Outra forma, inicializando como classe anônima:
		Consumer<String> consumidor2 = new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		};
		palavras.forEach(consumidor2);
		System.out.println();

		// Outra forma, implementando direto no argumento passado no forEach:
		palavras.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
		System.out.println();

		// Outra forma, utilizando novo recurso chamado lambda:
		palavras.forEach(p -> {
			System.out.println(p);
		});
		System.out.println();
		// Como só possui um argumento e uma linha de sódigo,
		// então poderia ser palavras.forEach(p -> System.out.println(p));

		// --Ordenação de listas também pode ser executada com lambda

		// implementando direto no argumento passado por parâmetro:
		palavras.sort(new ComparadorPorTamanho());

		// OU
		palavras.sort(new Comparator<String>() {
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
		palavras.sort((s1, s2) -> {
			if (s1.length() > s2.length()) {
				return 1;
			} else if (s1.length() < s2.length()) {
				return -1;
			}
			return 0;
		});
		// Melhorando:
		palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

		// Melhorando mais ainda:
		palavras.sort(Comparator.comparing(s -> s.length()));// Factory de comparators
		
		// Também pode ser escrito:
		Function<String, Integer> callback = s -> s.length();
		// Ou Function<String, Integer> callback = String::length; //Método reference
		Comparator<String> comp = Comparator.comparing(callback);
		palavras.sort(comp);
		
		// Melhor ainda
		palavras.sort(Comparator.comparing(String::length));
		
		
		// --Percorrendo com método reference
		palavras.forEach(System.out::println);
		//Métodos reference só podem ser utilizados quando se precisa invocar um único método
		//passando apenas um argumento. Caso contrário, soluções com lambda se torna melhor
		//(Ou até mesmo soluções com classe anônima, caso a implementação do método seja muito grande,
		//dessa forma, não é vantagem utilizar os novos recursos de lambda e reference)
		
		System.out.println();
	}

}

class ComparadorPorTamanho implements Comparator<String> {

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

class ImprimeNaLinha implements Consumer<String> {

	@Override
	public void accept(String t) {
		System.out.println(t);
	}

}