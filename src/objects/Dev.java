package objects;

public class Dev {

	private String name;
	private double productivity;
	private double salary;
	private int role;
	private Activity activity;
	private Event status;

	public Dev() {

	}

	public void endDay() {

	}

	public double getSalary() {
		return salary;
	}

	public Dev rollDev() {
		return null;
	}

	public void setActivity(Activity act) {
		activity = act;
	}
	
	public Activity getActivity() {
		return activity;
	}

	public void unsetActivity() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getProductivity() {
		return productivity;
	}

	public void setProductivity(double productivity) {
		this.productivity = productivity;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Event getStatus() {
		return status;
	}

	public void setStatus(Event status) {
		this.status = status;
	}

}
