package no.hvl.dat159.roomcontrol;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class PubTemperature {
	
	private JsonParser jsonParser = new JsonParser();
	
	public void publish(double temp) throws IOException{
		JsonObject content = new JsonObject();
		
		content.addProperty("temperature", temp);
				
		
		URL url = new URL("http://localhost:8080/tempsensor/current");
				
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		connection.setRequestMethod("PUT");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		
		PrintWriter out = new PrintWriter(connection.getOutputStream());
		
		out.println(content.toString());
		
		out.flush();
		out.close();
		
		JsonObject response = readResponse(connection.getInputStream());
		
		connection.disconnect();

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
