package objects;

public class Computer {

	private static double price;
	private Activity activity;
	private Event status;

	public static double getPrice() {
		return price;
	}

	public Computer() {

	}

	/*public ~Computer() {

	}*/

	public void endDay() {

	}

	public void setActivity(Activity a) {
		activity = a;
	}
	
	public Activity getActivity() {
		return activity;
	}

	public void unsetActivity() {

	}

	public Event getStatus() {
		return status;
	}

	public void setStatus(Event status) {
		this.status = status;
	}

}
