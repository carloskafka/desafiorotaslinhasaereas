package br.carloskafka.desafiotecnico.services.console;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.carloskafka.desafiotecnico.dtos.RouteDTO;
import br.carloskafka.desafiotecnico.dtos.RouteFetchResultDTO;
import br.carloskafka.desafiotecnico.services.rest.ServiceRoute;
import br.carloskafka.desafiotecnico.tools.Registry;

@Component
public class RouteConsoleManager implements CommandLineRunner {
	@Autowired
	private ServiceRoute serviceRoute;

	@Override
	public void run(String... args) {
		for (String arg: args) {
			System.out.println(arg);
		}
		
		if (args.length > 0) {
			Scanner input = new Scanner(System.in);
			int option = 0;

			do {
				Registry.changeDatabaseFile(args[0]);

				System.out.println("please enter the route");
				String route = input.next();

				RouteFetchResultDTO routeFetchResultDto = serviceRoute.fetchBestRoute(new RouteDTO(route));

				if (routeFetchResultDto.isSuccess()) {
					System.out.println(routeFetchResultDto.getBestRoute());
				} else {
					System.out.println(routeFetchResultDto.getErrors());
				}

				System.out.println("1 - Continue\n2 - Stop");
				option = input.nextInt();
			} while (option != 2);

			input.close();
		} else {
			Registry.restoreDatabaseFile();
		}
	}
}
