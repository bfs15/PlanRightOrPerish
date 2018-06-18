package objects;

public class Computer {

	private static double price;
	private Activity activity;
	private Event status;

	public static double getPrice() {
		return price;
	}
	
	public static double getPricePenalized() {
		return getPrice() * 0.75;
	}
	public Computer() {
		activity = null;
	}

	/*public ~Computer() {

	}*/

	public boolean endDay() {
		return status.roll();
	}

	public void setActivity(Activity a) {
		activity = a;
	}

	public Activity getActivity() {
		return activity;
	}

	public void unsetActivity() {
		activity = null;
	}

	public Event getStatus() {
		return status;
	}

	public void setStatus(Event status) {
		this.status = status;
	}


}
