
public class print extends Assignment2 {
	public print() {
		printstate();
	}

	public static synchronized void printstate() {
		
		System.out.println("\n-----------------------------------------");
		for (int i = 0; i < Assignment2.Thread_count; i++) {
			System.out.println("Philosopher " + i + " is " + p[i].getState());
		}
		
	}

}
