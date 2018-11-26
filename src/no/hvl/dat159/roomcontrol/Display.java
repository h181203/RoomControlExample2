package no.hvl.dat159.roomcontrol;

import java.io.IOException;

public class Display implements Runnable {
	
	private SubTemperature subTemp = new SubTemperature();
	private SubHeater subHeat = new SubHeater();
		
	public void write(String message) {
		System.out.println("DISPLAY: " + message);
	}
	
	public void run() {
		String status = "OFF";
		while (true) {
			try {
				write("Temperature in room: " + subTemp.receive());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if(subHeat.receive()) {
					status = "ON";
				} else {
					status = "OFF";
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			write("Heater status: " + status + "\n");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
