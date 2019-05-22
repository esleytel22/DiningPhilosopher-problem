
public class Assignment2 extends Thread {
	
	
	private static final int PHILOSOPHERS = 5;
	protected static int Thread_count = 0;
	public static DiningServer[] chopsticks  = new DiningServer[PHILOSOPHERS];
	public static DiningPhilosophers[] p  = new DiningPhilosophers[PHILOSOPHERS];
	public static Thread[] thread  = new Thread[PHILOSOPHERS];
	
	public static void main (String [] args) {
		int meals = 5;
		if (args.length != 0) {
			meals = Integer.parseInt(args[0]);
		
		}
		for (int i = 0; i < PHILOSOPHERS; i++ ) {
			chopsticks[i] = new DiningServer();
			
			if (i < 4) {
				p[i] = new DiningPhilosophers(i, chopsticks[i], meals);
			
			}
			else 
				p[i] = new DiningPhilosophers(i, chopsticks[i], meals);
			 
		
			thread[i] = new Thread(p[i], Integer.toString(i));
			Thread_count++;
			thread[i].start();
		}
		
	}
	
	

}
