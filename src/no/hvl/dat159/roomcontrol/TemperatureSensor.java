package no.hvl.dat159.roomcontrol;

import java.io.IOException;

public class TemperatureSensor implements Runnable {

	private Room room;
	private PubTemperature pubTemp = new PubTemperature();

	public TemperatureSensor(Room room) {

		this.room = room;
	}

	public double read() {

		return room.sense();
	}
	
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				pubTemp.publish(read());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
