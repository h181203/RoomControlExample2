package no.hvl.dat159.roomcontrol;

import java.io.IOException;

public class Heating implements Runnable {

	private Room room;
	private SubHeater subHeat = new SubHeater();
		
	public Heating (Room room) {
		this.room = room;
	}
	
	public void write (boolean newvalue) {
		room.actuate(newvalue);
	}
	
	public void run(){
		while (true) {
			try {
				write(subHeat.receive());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
