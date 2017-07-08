import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ExemploCursos {

	public static void main(String[] args) {
		List<Curso> cursos = new ArrayList<>();
		cursos.add(new Curso("Python", 45));
		cursos.add(new Curso("JavaScript", 150));
		cursos.add(new Curso("Java 8", 113));
		cursos.add(new Curso("C", 55));

		cursos.sort(Comparator.comparing(Curso::getAlunos));
		cursos.forEach(System.out::println);
		
		System.out.println();
		
		// --Filtro:
		
		//Cursos com no mínimo 100 alunos
		cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.forEach(System.out::println);

		System.out.println();
		
		//Quantidade de alunos em cada um dos cursos de no mínimo 100 alunos
		cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.map(Curso::getAlunos)
			.forEach(System.out::println);
		
		//Soma alunos dos cursos de no mínimo 100 alunos
		int sum = cursos.stream().filter(c -> c.getAlunos() >= 100).mapToInt(Curso::getAlunos).sum();
		System.out.println(sum);
		
		//Pegar algum desses cursos
		Optional<Curso> optionalCurso = cursos.stream().filter(c -> c.getAlunos() >= 100).findAny();
		
		Curso curso = optionalCurso.orElse(null);
		System.out.println(curso);
		
		//Ou
		optionalCurso.ifPresent(System.out::println);
		optionalCurso.ifPresent(c->System.out.println(c.getNome()));
		
		//Concatenando as informações:
		cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.findAny()
			.ifPresent(System.out::println);
		
		//Filtrar e guardar o resultado na lista original:
		cursos = cursos
				.stream().filter(c -> c.getAlunos() >= 100)
				.collect(Collectors.toList());
		
		System.out.println(cursos);
		
		//Converter retorno para Map
		Map<String, Integer> mapa = cursos.stream()
				.filter(c -> c.getAlunos() >= 100)
				.collect(Collectors.toMap(
						c -> c.getNome(), 
						c -> c.getAlunos()));
		
		System.out.println(mapa);
		
		cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.collect(Collectors.toMap(
					c -> c.getNome(), 
					c -> c.getAlunos()))
			.forEach((nome,alunos)->System.out.println(nome+" tem "+alunos+" alunos"));
		
		//OBS: Para listas muito grandes, o interessante é usar .parallelStream()
		// dessa forma, o java utiliza recursos de paralelismo para realizar o processamento
		cursos.parallelStream().filter(c -> c.getAlunos() >= 100);
		
	}

}

class Curso {
	private String nome;
	private int alunos;
	
	public Curso(String nome, int alunos) {
		if(nome == null){
			throw new NullPointerException("Nome não pode ser nulo");
		}
		this.nome = nome;
		this.alunos = alunos;
	}

	public String getNome() {
		return nome;
	}

	public int getAlunos() {
		return alunos;
	}

	@Override
	public String toString() {
		return "[Curso: " + nome + " , Quantidade de alunos: " + alunos + "]";
	}

}