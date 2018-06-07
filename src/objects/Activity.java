package objects;

import java.util.ArrayList;
import java.util.List;

public class Activity {

	private double cost;
	private String type;
	private String name;
	private List<Dev> devs =  new ArrayList<Dev>();
	private List<Computer> computers =  new ArrayList<Computer>();;
	
	public void work(double productivity) {

	}

	public boolean addDev(Dev dev) {
		return false;
	}

	public boolean rmDev(Dev d) {
		return false;
	}

	public boolean rmComputer(Computer c) {
		return false;
	}

	public int getComputerNo() {
		return 0;
	}

	public void setComputers(int qnt, Computer idleComp) {

	}

	public void rmComputers(int qnt) {

	}

	public void rmComputerAny() {

	}

	public void addComputer(Computer c) {

	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Dev> getDevs() {
		return devs;
	}

	public void setDevs(List<Dev> devs) {
		this.devs = devs;
	}

	public List<Computer> getComputers() {
		return computers;
	}

	public void setComputers(List<Computer> computers) {
		this.computers = computers;
	}

}
