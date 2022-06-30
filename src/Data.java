import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Data {
	private final String[] CITIES = {"Los Angeles", "San Diego", "San Jose", "SF", "Fresno", "Sacramento", "Long Beach", "Oakland", "Bakersfield", "Anaheim", "Santa Ana", "Riverside", "Stockton", "Irvine", "Chula Vista", "Fremont", "San Bernardino", "Modesto", "Fontana", "Santa Clarita", "Moreno Valley", "Oxnard", "Huntington Beach", "Glendale", "Santa Rosa", "Ontario", "Rancho Cucamonga", "Oceanside", "Elk Grove", "Cupertino"};
	private final double[][] LOCATIONS = {{34.0522, 118.2437}, {32.7157, 117.1611}, {37.3387, 121.8853}, {37.7749, 122.4194}, {36.7378, 119.7871}, {38.5816, 121.4944}, {33.7701, 118.1937}, {37.8044, 122.2712}, {35.3733, 119.0187}, {33.8366, 117.9143}, {33.7455, 117.8677}, {33.9806, 117.3755}, {37.9577, 121.2908}, {33.6846, 117.8265}, {32.6401, 117.0842}, {37.5485, 121.9886}, {34.1083, 117.2898}, {37.6393, 120.9970}, {34.0922, 117.4350}, {34.3917, 118.5426}, {33.9425, 117.2297}, {34.1975, 119.1771}, {38.4192, 82.4452}, {34.1425, 118.2551}, {38.4404, 122.7141}, {34.0633, 117.6509}, {34.1064, 117.5931}, {33.1959, 117.3795}, {38.4088, 121.3716}, {37.3230, 122.0322}};
	
	public String[] getCities() {return CITIES;}
	
	public int[] temperatureData() {
		int[] temperatures = new int[30];
		for(int i = 0; i < CITIES.length; i++) {
			try {
				URL url = new URL("https://api.weather.gov/points/" + LOCATIONS[i][0] + ",-" + LOCATIONS[i][1]);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				for(int j = 0; j < 61; j++) {
					in.readLine();
				}
				String input = in.readLine();
				input = input.substring(input.indexOf(":") + 3, input.length() - 2);
				in.close();
				con.disconnect();
				url = new URL(input);
				con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				for(int j = 0; j < 57; j++) {
					in.readLine();
				}
				input = in.readLine();
				input = input.substring(input.indexOf(":") + 2, input.length() - 1);
				temperatures[i] = Integer.parseInt(input);
				in.close();
				con.disconnect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(CITIES[i]);
			}
		}
		return temperatures;
	}
}
