package br.carloskafka.desafiotecnico.controllers;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.carloskafka.desafiotecnico.dtos.RouteDTO;
import br.carloskafka.desafiotecnico.dtos.RouteEditionResultDTO;
import br.carloskafka.desafiotecnico.dtos.RouteFetchResultDTO;
import br.carloskafka.desafiotecnico.services.rest.RestContract;
import br.carloskafka.desafiotecnico.tools.HttpUtils;

@SpringBootTest
public class ServiceRouteTests {

	private TestRestTemplate restTemplate;

	public ServiceRouteTests() {
		restTemplate = new TestRestTemplate();
	}

	private String getSaveRouteUrl() {
		return RestContract.URL_BASE + RestContract.URL_ROUTES;
	}

	private String getFetchBestRouteUrl(String infoRoute) {
		return RestContract.URL_BASE
				+ RestContract.URL_FETCH_BEST_ROUTE.replace(RestContract.URL_PARAMETER_INFOROUTE, infoRoute);
	}

	@Test
	public void given_a_client_when_fetching_best_route_passing_valid_info_route_then_returns_success() {
		String infoRoute = "GRU-BRC";

		RouteFetchResultDTO routeFetchResultDTO = restTemplate
				.getForEntity(getFetchBestRouteUrl(infoRoute), RouteFetchResultDTO.class).getBody();

		Assert.assertNotNull(routeFetchResultDTO.getBestRoute());
	}

	@Test
	public void given_a_client_when_fetching_best_route_passing_invalid_info_route_then_returns_error() {
		String infoRoute = ",,,,";

		RouteFetchResultDTO routeFetchResultDTO = restTemplate
				.getForEntity(getFetchBestRouteUrl(infoRoute), RouteFetchResultDTO.class).getBody();

		Assert.assertNotNull(routeFetchResultDTO.getBestRoute());
	}

	@Test
	public void given_a_client_when_saving_new_route_passing_valid_info_route_then_returns_success() {
		String infoRoute = "GRU,BRC,CDG,15";

		RouteDTO newRouteDto = new RouteDTO();

		newRouteDto.setInfoRoute(infoRoute);

		RouteEditionResultDTO routeEditionResultDTO = restTemplate
				.postForEntity(getSaveRouteUrl(), HttpUtils.criar(newRouteDto), RouteEditionResultDTO.class).getBody();

		Assert.assertTrue(routeEditionResultDTO.isSuccess());
	}

	@Test
	public void given_a_client_when_saving_new_route_passing_invalid_info_route_then_returns_success() {
		RouteDTO newRouteDto = new RouteDTO();

		newRouteDto.setInfoRoute(null);

		RouteEditionResultDTO routeEditionResultDTO = restTemplate
				.postForEntity(getSaveRouteUrl(), HttpUtils.criar(newRouteDto), RouteEditionResultDTO.class).getBody();

		Assert.assertFalse(routeEditionResultDTO.isSuccess());
	}
}
