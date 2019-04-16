package br.carloskafka.desafiotecnico.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Route {
	private final String INPUT_DELIMITER_COMMA = ",";
	private final String INPUT_DELIMITER_HYPHEN = "-";

	private String infoRoute;
	private List<String> places;
	private Double price;
	private boolean valid;
	private List<String> errors;

	public Route() {
		errors = new ArrayList<>();
		places = new ArrayList<>();
		valid = true;
	}

	public Route(String infoRoute) {
		this();
		setInfoRoute(infoRoute);
		init();
	}

	private void init() {
		if (infoRoute != null && !infoRoute.isEmpty()) {
			String[] infoRouteSplitted = null;

			if (infoRoute.contains(INPUT_DELIMITER_COMMA)) {
				infoRouteSplitted = infoRoute.split(INPUT_DELIMITER_COMMA);
			} else if (infoRoute.contains(INPUT_DELIMITER_HYPHEN)) {
				infoRouteSplitted = infoRoute.split(INPUT_DELIMITER_HYPHEN);
			} else {
				valid = false;
				errors.add("Best route was not found. Given input: " + infoRoute + " is invalid. Add a valid input, for example: GRU-CDG or GRU-BRC-CDG");
			}

			if (infoRouteSplitted != null) {
				for (String infoRoute : infoRouteSplitted) {
					infoRoute = infoRoute.replaceAll("\\s+", "");

					if (infoRoute.matches("[0-9]+")) {
						price = Double.parseDouble(infoRoute);
					} else {
						places.add(infoRoute);
					}
				}
				updateInfoRouteWithCorrectedPrice();
			}
		}
	}

	private void updateInfoRouteWithCorrectedPrice() {
		this.infoRoute = toCompleteInfo().replaceAll("\n", "");
	}

	public String getInfoRoute() {
		return infoRoute;
	}

	public void setInfoRoute(String infoRoute) {
		this.infoRoute = infoRoute != null && !infoRoute.isEmpty() ? infoRoute.toUpperCase() : "";
	}

	public String getSource() {
		return places.stream().findFirst().orElse("");
	}

	public void setSource(String source) {
		this.places.set(0, source);
	}

	public String getDestination() {
		return places.stream().skip(places.stream().count() - 1).findFirst().orElse("");
	}

	public void setDestination(String destination) {
		this.places.set(places.size() - 1, destination);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getErrorsDescription() {
		return errors.stream().map(String::toString).findFirst().orElse("");
	}

	public boolean valid() {
		return valid;
	}

	public void validateMandatoryFields() {
		validateInfoRoute();
		if (price == null) {
			valid = false;
			errors.add("Add a valid price");
		}
	}

	public void validateInfoRoute() {
		if (infoRoute == null || infoRoute.isEmpty() || infoRoute.chars().noneMatch(Character::isLetterOrDigit)) {
			valid = false;
			errors.add("Add a valid route");
		}
	}

	public String toOutputInfo() {
		return places.stream().map(String::toString).collect(Collectors.joining(",")) + " > $" + price;
	}

	public String toCompleteInfo() {
		return "\n" + places.stream().map(String::toString).collect(Collectors.joining(",")) + "," + price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((infoRoute == null) ? 0 : infoRoute.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (infoRoute == null) {
			if (other.infoRoute != null)
				return false;
		} else if (!infoRoute.equals(other.infoRoute))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Route [infoRoute=" + infoRoute + ", valid=" + valid + ", errors=" + errors + "]";
	}

}
