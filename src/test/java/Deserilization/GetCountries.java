package Deserilization;

public class GetCountries {

	private static String instructor;
	private static String url;
	private static String services;
	private static String expertise;
	private static Countries countries;

	
	public static Countries getCountries() {
		return countries;
	}
	public static void setCountries(Countries countries) {
		GetCountries.countries = countries;
	}

	public static String getInstructor() {
		return instructor;
	}
	public static void setInstructor(String instructor) {
		GetCountries.instructor = instructor;
	}
	public static String getUrl() {
		return url;
	}
	public static void setUrl(String url) {
		GetCountries.url = url;
	}
	public static String getServices() {
		return services;
	}
	public static void setServices(String services) {
		GetCountries.services = services;
	}
	public static String getExpertise() {
		return expertise;
	}
	public static void setExpertise(String expertise) {
		GetCountries.expertise = expertise;
	}
	
}
