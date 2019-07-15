package pcd.exam;

/**
 * Testing the sensor
 * 
 * @author aricci
 *
 */
public class TestSensor {

	public static void main(String[] args) {
		
		/* 
		 * create a sensor producing values between 50 and 75, 
		 * with probability of getting spikes = 0.01
		 */
		TempSensor sensor = new TempSensor(50,75,0.01);

		/*
		 * Reading the sensor every 250 ms for 5 seconds
		 */
		long t0 = System.currentTimeMillis();
		while (System.currentTimeMillis() - t0 < 5000){
			System.out.println(sensor.getCurrentValue());
			try {
				Thread.sleep(250);
			} catch (Exception ex){}
		}
		
	}

}
