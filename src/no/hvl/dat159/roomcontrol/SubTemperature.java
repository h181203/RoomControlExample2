package no.hvl.dat159.roomcontrol;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class SubTemperature {
	
	private JsonParser jsonParser = new JsonParser();
	private Gson gson = new Gson();
	
	public double receive() throws IOException {
			
		URL url = new URL("http://localhost:8080/tempsensor/current");
				
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestMethod("GET");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		
		JsonObject response = readResponse(connection.getInputStream());

		connection.disconnect();

		Temperature temp = gson.fromJson(response, Temperature.class);
		
		return temp.getTemperature();

	}
	
	private JsonObject readResponse(InputStream in) {
			
			Scanner scan = new Scanner(in);
			StringBuilder sn = new StringBuilder();
			
			while (scan.hasNext())
				sn.append(scan.nextLine()).append('\n');
			
			scan.close();
			
			return jsonParser.parse(sn.toString()).getAsJsonObject();
}

}
