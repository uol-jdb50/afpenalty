package assetto.afpenalty.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("assetto.afpenalty.web")
public class AfpenaltyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AfpenaltyApplication.class, args);
	}

}
