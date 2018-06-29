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
		int compNo = 0;
		for(Computer c : computers){
			if(c.isAvailable()){
				++compNo;
			}
		}
		double work = productivity + productivity*Math.log(compNo+1);
		String formattedMyWork = String.format("%.2f", work);
		System.out.println(formattedMyWork);
		this.workDone += work;
		//System.out.println(name+" "+this.workDone+"<-"+productivity);
		return workDone >= cost;
	}

	public boolean isComplete() {
		return workDone >= cost;
	}

	public boolean addDev(Dev dev) {
		if(devs.indexOf(dev) != -1){
			return false;
		}

		dev.setActivity(this);
		devs.add(dev);
		return true;
	}

	public boolean rmDev(Dev d) {
		int idx;
		if((idx = devs.indexOf(d)) == -1){
			return false;
		}

		devs.remove(idx);
		d.setActivity(null);
		return true;
	}

	public boolean rmComputer(Computer c) {
		return computers.remove(c);
	}

	public int getComputerNo() {
		return computers.size();
	}

	public boolean setComputers(int qnt, List<Computer> idleComp) {

		if(qnt > maxComputerNo){
			System.out.println("~Exceeded Computer max limit: "+maxComputerNo);
			return false;
		}


		for(int i = 0; i < qnt; i++){
			boolean succ = addComputer(idleComp.get(i));
			if( ! succ)
				return false;
		}
		System.out.println("~Added "+qnt+" computers to Activity "+this.getName());
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
			c.setActivity(null);
			computers.remove(c);
		}
		catch (Exception e){
			System.out.print("Couldn't remove computer");
		}
	}

	public boolean addComputer(Computer c) {
		if(maxComputerNo <= computers.size() )
			return false;

		computers.add(c);
		// associate computer to activity
		c.setActivity(this);
		//System.out.println("comp+act");
		return true;
	}

	public double getWorkDone() {return workDone;}

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
