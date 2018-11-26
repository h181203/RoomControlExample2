package no.hvl.dat159.roomcontrol;

import java.io.IOException;

public class Controller implements Runnable {
	
	private SubTemperature subTemp = new SubTemperature();
	private PubHeater pubHeat = new PubHeater();
		
	
	public void run() {
		double temp = 0;
		while(true) {
			try {
				temp = subTemp.receive();
				if(temp < 15) {
					pubHeat.publish(true);
				} else if(temp > 23) {
					pubHeat.publish(false);
				}
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
