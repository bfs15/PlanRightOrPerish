package objects;

import java.util.ArrayList;
import java.util.List;

public class Project {

	private String description;
	private String lifeCycleModel;
	private double budget;
	private double money;
	private int day = 0;
	private Stage currentStage;
	private List<Stage> stages = new ArrayList<Stage>();
	private List<Dev> devs =  new ArrayList<Dev>();
	private List<Computer> computers =  new ArrayList<Computer>();
	private List<Activity> activities =  new ArrayList<Activity>();
	private List<Dev> dailyDevs = new ArrayList<Dev>();


	public boolean estabilishExpenses(double money) {
		return false;
	}

	public boolean setSchedule(int stageID, int workDays) {
		boolean success = false;
		try{
			Stage s = getStage(stageID);
			success = s.setWorkDays(workDays);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return success;
	}

	public boolean nextStage() {
		return false;
	}

	public Stage getStage(int ID) {
		return null;
	}

	public void penalty() {

	}

	public void endDay() {

	}

	public boolean addDailyDev(Dev dev) {
		boolean success = false;
		try {
			success = this.dailyDevs.add(dev);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}

	public boolean addDev(int ID) {
		boolean success = false;
		try {
			Dev dev = this.dailyDevs.get(ID);
			success = devs.add(dev);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return success;
		
	}

	public boolean rmDev(int devID) {
		return false;
	}

	public List<Dev> getIdleDevs() {
		return null;
	}

	public boolean addDevOnActivity(int ActivityID, int DevID) {
		return false;
	}

	public boolean rmDevOnActivity(int ActivityID, int DevID) {
		return false;
	}

	public double addComputer(int quantity) {
		for (int i=0;i<quantity;++i) {
			computers.add(new Computer());
			money -= Computer.getPrice();

		}
		return money;
	}

	public double rmComputer(int quantity) {
		while (quantity != 0 ) {
			Computer c = computers.get(0);
			money += Computer.getPricePenalized();
			Activity a = c.getActivity();
			if (!a.equals(null)){
				a.rmComputer(c);
			}
			--quantity;
		}
		return 0;
	}

	public Activity getActivity(int ID) {
		Activity a;
		try {
			a = activities.get(ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			a = null;
		}


		return a;
	}

	public Dev getDev(int ID) {
		return null;
	}

	public List<Dev> getActivityDevs(int actID) {
		return null;
	}

	public int setComputer(int actID, int qnt) { //actID = id de uma atividade, qnt = numero DESEJADO de computadores para uma atividade
		int success = 0;
		try {
			Activity a  = getActivity(actID);
			int computerNo = a.getComputerNo();
			if(computerNo > qnt) {
				qnt = computerNo - qnt; //remover o delta
				a.rmComputers(qnt);
				success = -qnt;
			}

			else if (computerNo < qnt) {
				qnt = qnt - computerNo; //adicionar o delta 
				List<Computer> idleComp = getIdleComputers(); //obtem lista de todos os idle computers
				if(idleComp.size() >= qnt) { //se existem idle computers o suficiente
					a.setComputers(qnt,idleComp);
					success = qnt; 
				}
			}
		} 
		catch (Exception e){
			e.printStackTrace();
		}

		return success;
	}

	public List<Computer> getIdleComputers() {
		List<Computer> idleComputers = new ArrayList<Computer>();
		for (Computer c: computers){ 
			if(!c.getActivity().equals(null)) {
				idleComputers.add(c);
			}
		}
		return idleComputers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLifeCycleModel() {
		return lifeCycleModel;
	}

	public void setLifeCycleModel(String lifeCycleModel) {
		this.lifeCycleModel = lifeCycleModel;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Stage getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(Stage currentStage) {
		this.currentStage = currentStage;
	}

	public List<Stage> getStages() {
		return stages;
	}

	public void setStages(List<Stage> stages) {
		this.stages = stages;
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

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	
	public List<Dev> getDailyDevs() {
		return dailyDevs;
	}
	
	public List<Dev> generateDailyDevs() {
		List<Dev> dailyDevs = new ArrayList<Dev>(); 
		for (int i = 0; i < MAX_DAILY_DEVS ; ++i) {
			Dev dev = new Dev();
			dev = dev.rollDev(); //sim, ta meio bizarro
			addDailyDev(dev);
			
		}
		return dailyDevs;
	}

	public void setDailyDevs(List<Dev> dailyDevs) {
		this.dailyDevs = dailyDevs;
	}

}
