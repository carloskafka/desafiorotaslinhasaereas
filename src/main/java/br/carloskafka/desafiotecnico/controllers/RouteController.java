package br.carloskafka.desafiotecnico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.carloskafka.desafiotecnico.dtos.RouteDTO;
import br.carloskafka.desafiotecnico.dtos.RouteEditionResultDTO;
import br.carloskafka.desafiotecnico.dtos.RouteFetchResultDTO;
import br.carloskafka.desafiotecnico.services.rest.RestContract;
import br.carloskafka.desafiotecnico.services.rest.ServiceRoute;

@RestController
public class RouteController {

	@Autowired
	private ServiceRoute serviceRoute;

	@PostMapping(RestContract.URL_ROUTES)
	public RouteEditionResultDTO saveNewRoute(@RequestBody RouteDTO routeDto) {
		return serviceRoute.save(routeDto);
	}

	@GetMapping(RestContract.URL_FETCH_BEST_ROUTE)
	public RouteFetchResultDTO fetchBestRoute(@PathVariable(RestContract.PARAMETER_INFO_ROUTE) String text) {
		return serviceRoute.fetchBestRoute(new RouteDTO(text));
	}
}
