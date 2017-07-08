import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class ExemploDatas {

	public static void main(String[] args) {
		
		LocalDate hoje = LocalDate.now();
		System.out.println(hoje);
		
		LocalDate copaRussia = LocalDate.of(2018, Month.JUNE, 5);
		
		Period periodo = Period.between(hoje, copaRussia);
		System.out.println(periodo.getDays());
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyy");
		System.out.println(hoje.format(formatador));
		
		DateTimeFormatter formatadorComHoras = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");
		
		LocalDateTime agora = LocalDateTime.now();
		System.out.println(agora.format(formatadorComHoras));

		
		
	}

}
