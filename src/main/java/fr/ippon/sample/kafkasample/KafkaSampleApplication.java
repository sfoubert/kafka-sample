package fr.ippon.sample.kafkasample;

import fr.ippon.sample.kafkasample.service.MailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KafkaSampleApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(KafkaSampleApplication.class, args);
		MailService service = context.getBean(MailService.class);
		service.sendMessage("hello world");
	}
}
