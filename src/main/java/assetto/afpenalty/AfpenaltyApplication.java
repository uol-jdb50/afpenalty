package assetto.afpenalty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan("assetto.afpenalty")
public class AfpenaltyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AfpenaltyApplication.class, args);
	}

}
