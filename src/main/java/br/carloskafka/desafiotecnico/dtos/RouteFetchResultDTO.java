package br.carloskafka.desafiotecnico.dtos;

import java.util.ArrayList;
import java.util.List;

import br.carloskafka.desafiotecnico.domain.Route;

public class RouteFetchResultDTO {
	private boolean success;
	private List<String> errors;
	private Route bestRoute;

	public RouteFetchResultDTO() {
		errors = new ArrayList<>();
	}

	public String getBestRoute() {
		return bestRoute != null ? bestRoute.toOutputInfo() : "";
	}

	public void setBestRoute(Route bestRoute) {
		this.bestRoute = bestRoute;
		success = true;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		success = false;
		this.errors = errors;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
