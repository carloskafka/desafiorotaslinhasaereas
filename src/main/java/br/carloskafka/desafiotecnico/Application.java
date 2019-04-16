package br.carloskafka.desafiotecnico;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import br.carloskafka.desafiotecnico.tools.Registry;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private ApplicationContext context;

	@PostConstruct
	public void inicializar() {
		Registry.setContext(context);
	}

	@Override
	public void run(String... args) throws Exception {
		Registry.initializeContext();
	}

	public static void main(String[] args) throws Exception {
		new SpringApplication(Application.class).run(args);
	}
}
