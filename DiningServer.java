

public class DiningServer {
	enum State {EATING, THINKING, HUNGRY}
	DiningPhilosophers.State[] states = new DiningPhilosophers.State[5];

	public boolean takeChopsticks(int i) {
		states[i] = DiningPhilosophers.State.HUNGRY;
		if ((DiningPhilosophers.test(i) == true)){ 
			return true;
		}
		return false;
	}
	public void returnChopsticks(int i) {
		states[i] = DiningPhilosophers.State.THINKING;
		DiningPhilosophers.test((i % 4) % 5);
		}
	}
