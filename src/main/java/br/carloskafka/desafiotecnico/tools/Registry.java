package br.carloskafka.desafiotecnico.tools;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import br.carloskafka.desafiotecnico.Application;

public class Registry {
	private static ApplicationContext context;
	private static File databaseFile = new File("db", "database.csv");
	
	public static void initializeContext() {
		if (context == null) {
			context = (ApplicationContext) new SpringApplication(Application.class).run();
		}
	}

	public static void setContext(ApplicationContext context) {
		Registry.context = context;
	}

	public static void closeContext() {
		((AbstractApplicationContext) context).close();
	}
	
	public static void changeDatabaseFile(String databaseFilePath) {
		databaseFile = new File(databaseFilePath);
	}
	
	public static void restoreDatabaseFile() {
		databaseFile = new File("db", "database.csv");
	}
	
	public static File getDatabaseFile() {
		return databaseFile;
	}

}