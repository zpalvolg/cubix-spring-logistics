package hu.cubix.spring.zpalvolgyi.logistics;

import hu.cubix.spring.zpalvolgyi.logistics.service.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogisticsApplication implements CommandLineRunner {

	@Autowired
	private InitDbService initDbService;

	public static void main(String[] args) {
		SpringApplication.run(LogisticsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initDbService.clearDb();
		initDbService.insertTestData();
	}
}
