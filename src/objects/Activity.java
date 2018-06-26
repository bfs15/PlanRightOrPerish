package objects;

import java.util.ArrayList;
import java.util.List;

public class Activity {

	private double cost;
	private double workDone = 0;

	private int maxComputerNo;
	private String type;
	private String name;
	private List<Dev> devs =  new ArrayList<>();
	private List<Computer> computers =  new ArrayList<>();

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
		dev.setActivity(this);
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
		return computers.size();
	}

	public boolean setComputers(int qnt, List<Computer> idleComp) {
		if(qnt <= maxComputerNo)
			return false;

		for(int i = 0; i <= qnt; i++){
			boolean succ = addComputer(idleComp.get(i));
			if( ! succ)
				return false;
		}
		return true;
	}

	public void rmComputers(int qnt) {
		for(int i = 0; i < qnt; ++i) {
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
			System.out.print("Couldn't remove computer");
		}
	}

	public boolean addComputer(Computer c) {
		if(computers.size() < maxComputerNo)
			return false;

		computers.add(c);
		// associate computer to activity
		c.setActivity(this);
		return true;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getMaxComputerNo() {
		return maxComputerNo;
	}

	public void setMaxComputerNo(int maxComputerNo) {
		this.maxComputerNo = maxComputerNo;
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
}
