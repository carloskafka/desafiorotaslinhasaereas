package br.carloskafka.desafiotecnico.services.rest;

import br.carloskafka.desafiotecnico.dtos.RouteDTO;
import br.carloskafka.desafiotecnico.dtos.RouteEditionResultDTO;
import br.carloskafka.desafiotecnico.dtos.RouteFetchResultDTO;

public interface ServiceRoute {
	public RouteEditionResultDTO save(RouteDTO route);
	public RouteFetchResultDTO fetchBestRoute(RouteDTO route);
}
