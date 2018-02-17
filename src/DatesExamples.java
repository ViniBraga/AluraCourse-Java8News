import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DatesExamples {

	public static void main(String[] args) {
		
		LocalDate today = LocalDate.now();
		System.out.println(today);
		
		LocalDate russiaWorldCup = LocalDate.of(2018, Month.JUNE, 5);
		
		Period period = Period.between(today, russiaWorldCup);
		System.out.println(period.getDays());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
		System.out.println(today.format(formatter));
		
		DateTimeFormatter formatterWithHours = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");
		
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now.format(formatterWithHours));
		
	}

}
