package no.hvl.dat159.roomcontrol;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SubHeater {
	
	


	private JsonParser jsonParser = new JsonParser();
	private Gson gson = new Gson();
	
	public boolean receive() throws IOException {
			
		URL url = new URL("http://localhost:8080/heating/current");
				
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestMethod("GET");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		
		JsonObject response = readResponse(connection.getInputStream());

		connection.disconnect();

		Heat heat = gson.fromJson(response, Heat.class);
		
		return heat.getHeat();
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
