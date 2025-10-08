package tp.springJersey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJerseyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJerseyApplication.class, args);
		System.out.println("http://localhost:8080/springJersey/index.html");
	}

}
