package philosopherAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// TODO: Create a DB so we can store stuff. For now just ignore it.
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PhilosopherApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhilosopherApiApplication.class, args);
	}


}
