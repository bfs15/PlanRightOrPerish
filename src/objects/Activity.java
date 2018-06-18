package objects;

import java.util.ArrayList;
import java.util.List;

public class Activity {

	private double cost;
	private double workDone = 0;
	private String type;
	private String name;
	private List<Dev> devs =  new ArrayList<Dev>();
	private List<Computer> computers =  new ArrayList<Computer>();;

	public boolean work(double productivity) {
		workDone += productivity;
		return workDone >= cost;
	}

	public boolean isComplete() {
		return workDone >= cost;
	}

	public boolean addDev(Dev dev) {
		if(devs.indexOf(dev) != -1){
			return false;
		}

		devs.add(dev);
		return true;
	}

	public boolean rmDev(Dev d) {
		int idx;
		if((idx = devs.indexOf(d)) == -1){
			return false;
		}

		devs.remove(idx);
		d.unsetActivity();
		return true;
	}

	public boolean rmComputer(Computer c) {
		return computers.remove(c);
	}

	public int getComputerNo() {
		int computerNo =  computers.size();
		return computerNo;
	}

	public void setComputers(int qnt, List<Computer> idleComp) {
		for(int i = 0; i <= qnt; i++){
			addComputer(idleComp.get(i));
		}
	}

	public void rmComputers(int qnt) {
		for(int i = 0;i < qnt; ++i) {
			rmComputerAny();
		}
	}

	public void rmComputerAny() {
		try {
			int lastIdx = computers.size()-1;
			Computer c = computers.get(lastIdx);
			c.unsetActivity();
			computers.remove(c);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public void addComputer(Computer c) {
		computers.add(c);
		// associate computer to activity
		c.setActivity(this);
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
