package br.carloskafka.desafiotecnico.repositories;

import br.carloskafka.desafiotecnico.domain.Route;

public interface RouteRepository {
	public boolean save(Route route);
	public Route fetchBestRoute(Route route);
}
