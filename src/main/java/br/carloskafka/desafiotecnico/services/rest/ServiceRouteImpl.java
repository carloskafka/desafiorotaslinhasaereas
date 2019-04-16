package br.carloskafka.desafiotecnico.services.rest;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.carloskafka.desafiotecnico.domain.Route;
import br.carloskafka.desafiotecnico.dtos.RouteDTO;
import br.carloskafka.desafiotecnico.dtos.RouteEditionResultDTO;
import br.carloskafka.desafiotecnico.dtos.RouteFetchResultDTO;
import br.carloskafka.desafiotecnico.repositories.RouteRepository;

@Component
public class ServiceRouteImpl implements ServiceRoute {

	@Autowired
	private RouteRepository routeRepository;

	@Override
	public RouteEditionResultDTO save(RouteDTO routeDto) {
		RouteEditionResultDTO routeEditionResult = new RouteEditionResultDTO();

		Route route = new Route(routeDto.getInfoRoute());

		route.validateMandatoryFields();

		if (route.valid()) {
			if (routeRepository.save(route)) {
				routeEditionResult.markAsSuccessful();
			} else {
				routeEditionResult.addError("Error while saving the " + route + " to file");
			}
		} else {
			routeEditionResult.addError(route.getErrorsDescription());
		}

		return routeEditionResult;
	}

	@Override
	public RouteFetchResultDTO fetchBestRoute(RouteDTO routeDto) {
		RouteFetchResultDTO routeFetchResult = new RouteFetchResultDTO();

		Route routeToBeMatchAgainstBestRoute = new Route(routeDto.getInfoRoute());

		routeToBeMatchAgainstBestRoute.validateInfoRoute();

		if (routeToBeMatchAgainstBestRoute.valid()) {
			Route bestRoute = routeRepository.fetchBestRoute(routeToBeMatchAgainstBestRoute);

			if (bestRoute != null) {
				routeFetchResult.setBestRoute(bestRoute);
			} else {
				routeFetchResult.setErrors(Arrays.asList("Route was not found with " + routeToBeMatchAgainstBestRoute.getSource() + " - " + routeToBeMatchAgainstBestRoute.getDestination()));
			}
		} else {
			routeFetchResult.setErrors(routeToBeMatchAgainstBestRoute.getErrors());
		}

		return routeFetchResult;
	}

}
