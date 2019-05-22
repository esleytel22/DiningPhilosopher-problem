import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;

public class DiningPhilosophers implements Runnable {
	
	private int NumEat;
	private int chairNum;
	private int MaxEat;
	public long waitTime;
	public long signalTime;
	enum State { THINKING, HUNGRY, EATING}
	private State curr_state = State.THINKING;
	public static State[] states = new State[5];
	Condition[] self  = new Condition[5];  
	public volatile DiningServer chopstick = new DiningServer();
	private volatile long eatingTime, thinkingTime;
	

	public DiningPhilosophers(int chair, DiningServer left, int eat) {
		
		chairNum = chair;
		chopstick = left;
		MaxEat = eat;
		curr_state = State.THINKING;
		setRandomTimes();
		 
	}
	
	
	private void setRandomTimes() {
		eatingTime = ThreadLocalRandom.current().nextLong(5000);
		thinkingTime = ThreadLocalRandom.current().nextLong(eatingTime);
		waitTime = ThreadLocalRandom.current().nextLong(1000);
	}
	
	public void run() {
		for (int i= 0; i < 5; i++)
			states[i] = State.THINKING;
		print.printstate();
		while (NumEat < MaxEat) {
			think();
			changeState(State.HUNGRY);
			try {
				if (pickChopsticks() == false) {
					System.out.println("Philosopher "+ chairNum + " released its left and right chopsticks");
					continue;
				}
				}
				catch(NullPointerException e) {
					
			}
			System.out.println("Philosopher "+ chairNum + " acquired its left and right chopsticks");
			
			eat();
			System.out.println("Philosopher "+ chairNum + " released its left and right chopsticks");
			
		}
	}
	
	private void eat() {
		changeState(State.EATING);
		try {
			Factory x = new Factory(eatingTime);
			//Thread.sleep(eatingTime);;
		}
		catch (InterruptedException e) {
			
		}
		try {
			states[chairNum] = State.THINKING;
		}
		catch (NullPointerException e ){
			
		}
		NumEat++;
	}
	public static boolean test(int i) {
		
		if ((states[(i + 4) % 5] != State.EATING ) && (states[(i + 1) % 5] != State.EATING)) {
			states[i] = State.EATING;
			return true;
		}
		return false;
	}
	
	private boolean pickChopsticks() {
		if (chopstick.takeChopsticks(chairNum) == false ) {
			chopstick.returnChopsticks(chairNum);
			
			return false;
		}
		else 
			return true;
	}
	
	private void think() {
		changeState(State.THINKING);
		setRandomTimes();
		
		try {
			Thread.sleep(thinkingTime);
		}
		catch (InterruptedException e ) { }
		
	}
	
	
	private void changeState(State NewState) {
		if (curr_state != NewState) {
			curr_state = NewState;
			print.printstate();
		}
		else 
			return; 
		

	}
	
	public String getState() {
		String str = "";
		if (curr_state == State.HUNGRY)
			str = "Hungry";
		else if (curr_state == State.THINKING)
			str = "Thinking";
		if (curr_state == State.EATING)
			str = "Eating";
		return str;
	}
}
