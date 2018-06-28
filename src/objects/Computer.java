package objects;

public class Computer {

	private static double price = 2000;
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
		status = new Event();
	}

    public void destroy(){
        if (activity != null){
            boolean removed = activity.rmComputer(this);
            if(!removed){
                System.err.print("Associated computer->activity not found in activity->computer");
            }
        }
    }

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
