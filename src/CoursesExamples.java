import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CoursesExamples {

	public static void main(String[] args) {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Python", 45));
		courses.add(new Course("JavaScript", 150));
		courses.add(new Course("Java 8", 113));
		courses.add(new Course("C", 55));

		courses.sort(Comparator.comparing(Course::getStudents));
		courses.forEach(System.out::println);
		
		System.out.println();
		
		// --Filter:
		
		//Courses of at least 100 students
		courses.stream()
			.filter(c -> c.getStudents() >= 100)
			.forEach(System.out::println);

		System.out.println();
		
		//Number of students in each course of at least 100 students
		courses.stream()
			.filter(c -> c.getStudents() >= 100)
			.map(Course::getStudents)
			.forEach(System.out::println);
		
		//Sum students from courses of at least 100 students
		int sum = courses.stream().filter(c -> c.getStudents() >= 100).mapToInt(Course::getStudents).sum();
		System.out.println(sum);
		
		//Pick up some of these courses
		Optional<Course> optionalCourse = courses.stream().filter(c -> c.getStudents() >= 100).findAny();
		
		Course curso = optionalCourse.orElse(null);
		System.out.println(curso);
		
		//Or
		optionalCourse.ifPresent(System.out::println);
		optionalCourse.ifPresent(c->System.out.println(c.getName()));
		
		//Concatenating arguments
		courses.stream()
			.filter(c -> c.getStudents() >= 100)
			.findAny()
			.ifPresent(System.out::println);
		
		//Filter and save the result in the original list:
		courses = courses
				.stream().filter(c -> c.getStudents() >= 100)
				.collect(Collectors.toList());
		
		System.out.println(courses);
		
		//Converter return to Map
		Map<String, Integer> mapa = courses.stream()
				.filter(c -> c.getStudents() >= 100)
				.collect(Collectors.toMap(
						c -> c.getName(), 
						c -> c.getStudents()));
		
		System.out.println(mapa);
		
		courses.stream()
			.filter(c -> c.getStudents() >= 100)
			.collect(Collectors.toMap(
					c -> c.getName(), 
					c -> c.getStudents()))
			.forEach((name,students)->System.out.println(name+" has "+students+" students"));
		
		//PS: For very large lists, the interesting thing is to use .parallelStream ()
		// Therefore, java uses parallelism features to perform the processing
		courses.parallelStream().filter(c -> c.getStudents() >= 100);
		
	}

}

class Course {
	private String name;
	private int students;
	
	public Course(String name, int students) {
		if(name == null){
			throw new NullPointerException("Name can't be null");
		}
		this.name = name;
		this.students = students;
	}

	public String getName() {
		return name;
	}

	public int getStudents() {
		return students;
	}

	@Override
	public String toString() {
		return "[Course: " + name + " , Number of students: " + students + "]";
	}

}