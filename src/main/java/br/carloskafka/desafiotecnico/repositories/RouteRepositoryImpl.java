package br.carloskafka.desafiotecnico.repositories;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.carloskafka.desafiotecnico.domain.Route;
import br.carloskafka.desafiotecnico.tools.Registry;

@Repository
public class RouteRepositoryImpl implements RouteRepository {

	private File databaseFile = Registry.getDatabaseFile();
	
	@Override
	public boolean save(Route route) {
		boolean saved = false;

		try {
			if (!containsRoute(route)) {
				Files.write(Paths.get(databaseFile.getAbsolutePath()), route.toCompleteInfo().getBytes());				
			}
			saved = true;
		} catch (Exception e) {
			saved = false;
			e.printStackTrace();
		}

		return saved;
	}

	@Override
	public Route fetchBestRoute(Route route) {
		return fetchRoutesFromSourceFile().stream().sorted(Comparator.comparing(Route::getPrice))
				.filter(routeFound -> 
							routeFound.getSource().equals(route.getSource()) && routeFound.getDestination().equals(route.getDestination()))
				.findFirst().orElse(null);
	}

	private List<Route> fetchRoutesFromSourceFile() {
		List<Route> routes = new ArrayList<>();

		try {
			List<String> routesString = Files.readAllLines(Paths.get(databaseFile.getAbsolutePath()));

			routesString.stream().forEach(newRoute -> {
				routes.add(new Route(newRoute));
			});

		} catch (IOException e) {
			e.printStackTrace();
		}

		return routes;
	}

	private boolean containsRoute(Route route) {
		return fetchRoutesFromSourceFile().contains(route);
	}

}
