package objects;

import java.util.Random;

public class Dev {

	private String name = "";
	private double productivity = 0;
	private double salary = 0;
	private String role = "";
	private Activity activity = null;
	private Event status = new Event();

//	public Dev() {
//
//	}

	public boolean endDay() {
		boolean isWorking = status.isAvailable();
		boolean roll = false;
		if(isWorking){
			activity.work(productivity);
			roll = status.roll();
		}

		return roll;
	}

	public double getSalary() {
		return salary;
	}
	
	public void rollDev() {
		Random r = new Random();
		// obtem um nome aleatoriamente
		this.name = getPossibleName(r.nextInt(possibleNames.size()));
		// (0,1) * valor limitante
		double prodMin = 100;
		double prodMax = 400 - prodMin;
		this.productivity  = r.nextDouble() * prodMax + prodMin;
		// salarioBase +  (salarioBase * gananciaDoDev * produtividade)
		// salario varia de acordo com a produtividade, mas sempre dentro de um maximo
		this.salary = MIN_SALARY + (MIN_SALARY * r.nextDouble() *  this.productivity);
		// ex 1: 1000.0 + (1000.0* 0.5 * 0.5) = 1250.00
		// ex 2: 1000.0 + (1000.0* 0.5 * 10) = 6000.00

		//obtem um papel aleatoriamente
		this.role = getPossibleRole(r.nextInt(possibleRoles.size()));
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
