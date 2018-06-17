package objects;

import java.util.Random;

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
		Random r = new Random();
		this.name = getPossibleName(r.nextInt(possibleNames.size())); //obtem um nome aleatoriamente
		this.productivity  = r.nextDouble() * MAX_PRODUCTIVITY; //(numero de 0 a 1) * valor limitante
		//			   salarioBase +  (salarioBase * gananciaDoDev * produtividade)

		this.salary = MIN_SALARY + (MIN_SALARY * r.nextDouble() *  this.productivity); //salario varia de acordo com a produtividade, mas sempre dentro de um maximo
		//			Exemplo 1: 1000.0 + (1000.0* 0.5 * 0.5) = 1250.00
		//			Exemplo 2: 1000.0 + (1000.0* 0.5 * 10) = 6000.00
		this.role = getPossibleRole(r.nextInt(possibleRoles.size())); //obtem um papel aleatoriamente
		
		return this;
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
