package br.carloskafka.desafiotecnico.dtos;

public class RouteDTO {
	private String infoRoute;

	public RouteDTO() {
	}
	
	public RouteDTO(String infoRoute) {
		super();
		this.infoRoute = infoRoute;
	}

	public String getInfoRoute() {
		return infoRoute;
	}

	public void setInfoRoute(String infoRoute) {
		this.infoRoute = infoRoute;
	}

}
