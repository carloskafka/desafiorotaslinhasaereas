package br.carloskafka.desafiotecnico.services.rest;

public class RestContract {
	public static final String PARAMETER_INFO_ROUTE = "text";
	public static final String URL_PARAMETER_INFOROUTE = "{" + PARAMETER_INFO_ROUTE + "}";

	public static final String URL_BASE = "http://localhost:8080";
	
	public static final String URL_ROUTES = "/";
	public static final String URL_FETCH_BEST_ROUTE = "/" + URL_PARAMETER_INFOROUTE;

}
