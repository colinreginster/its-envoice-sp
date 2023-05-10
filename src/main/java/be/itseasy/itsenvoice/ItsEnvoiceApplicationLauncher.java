package be.itseasy.itsenvoice;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("be.itseasy.itsenvoice.model.repositories")
@EntityScan("be.itseasy.itsenvoice.model.entities")
public class ItsEnvoiceApplicationLauncher {

	public static void main(String[] args) {
		Application.launch(ItsEnvoiceApplication.class, args);
	}

}
