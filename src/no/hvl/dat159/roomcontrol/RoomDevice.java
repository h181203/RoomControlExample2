package no.hvl.dat159.roomcontrol;

public class RoomDevice {

	public static void main(String[] args) throws InterruptedException {
		
		Room room = new Room(20);
		
		
		TemperatureSensor sensor = new TemperatureSensor(room);
		Heating heater = new Heating(room);
		Controller control = new Controller();
		Display display = new Display();
	
		Thread sensorThread = new Thread(sensor);
		Thread heaterThread = new Thread(heater);
		Thread controlThread = new Thread(control);
		Thread displayThread = new Thread(display);
		
		sensorThread.start();
		heaterThread.start();
		controlThread.start();
		displayThread.start();
		
		sensorThread.join();
		heaterThread.join();
		controlThread.join();
		displayThread.join();

	}

}
