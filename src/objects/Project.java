package objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Project {

	private String description;
	private String lifeCycleModel;
	private double budget;
	private double money;
	private int day = 0;
	private Stage currentStage;
	private List<Stage> stages = new ArrayList<>();
	private List<Dev> devs =  new ArrayList<>();
	private List<Computer> computers =  new ArrayList<>();
	private List<Activity> activities =  new ArrayList<>();
	private List<Dev> dailyDevs = new ArrayList<>();

	public boolean estabilishExpenses(double money) {
		if(money <= 0){
			return false;
		}
		this.budget = money;	
		this.money = money;
		return true;
	}

	public void setSchedule(int stageID, int workDays) {
		try{
			Stage s = getStage(stageID);
			s.setWorkDays(workDays);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public boolean nextStage() {
		int idx = stages.indexOf(currentStage);

		if (idx < 0 || idx+1 == stages.size())
			return false;

		currentStage = stages.get(idx + 1);
		return true;
	}

	public Stage getStage(int ID) {
		return null;
	}

	public void penalty() {

	}

	public boolean endDay() {
		for(Dev d : devs){
			boolean event = d.endDay();
			money -= d.getSalary();
		}
		for(Computer c : computers){
			c.endDay();
		}
		var complete = currentStage.endDay();
		if(complete){
			nextStage();
		} else if (currentStage.getWorkDays() == 0){
			penalty();
		}
		return complete;
	}

	public boolean addDev(int ID) {
		boolean success = false;
		try {
			Dev dev = this.dailyDevs.get(ID);
			success = devs.add(dev);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return success;

	}

	public boolean rmDev(int devID) {
		return false;
	}

	public List<Dev> getIdleDevs() {
		return null;
	}

	public boolean addDevOnActivity(int activityID, int devID) {
        Activity a;
        Dev d;
        try {
            a = activities.get(activityID);
            d = devs.get(devID);
        } catch (Exception e){
            return false;
        }

        return a.addDev(d);
	}

	public boolean rmDevOnActivity(int activityID, int devID) {
        Activity a;
        Dev d;
        try {
            a = activities.get(activityID);
            d = devs.get(devID);
        } catch (Exception e){
            return false;
        }

        return a.rmDev(d);
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
		Activity a = null;
		try {
			a = activities.get(ID);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return a;
	}

	public Dev getDev(int ID) {
		return null;
	}

	public List<Dev> getActivityDevs(int actID) {
	    Activity act;
        try {
            act = activities.get(actID);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

		return act.getDevs();
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
		List<Computer> idleComputers = new ArrayList<>();
		for (Computer c: computers){
			if(c.getActivity() == null) {
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
			
		if(dailyDevs.size() == 0){
			generateDailyDevs();
		}
		return dailyDevs;
	}

	public void generateDailyDevs() {
		final int DAILY_DEVS_NO = 10;

		for (int i = 0; i < DAILY_DEVS_NO ; ++i) {
			Dev dev = new Dev();
			dev.rollDev();
			this.dailyDevs.add(dev);
		}
	}

	public void setDailyDevs(List<Dev> dailyDevs) {
		this.dailyDevs = dailyDevs;
	}

	public void openFile(String string) throws IOException {
		// TODO Colocar no diagrama
		boolean ReadingActivities = false;
		int cont = 0;
		String line = "";
		String s = "";
		Activity act = null ;
		BufferedReader br = new BufferedReader(new FileReader(string));
		try {
			StringBuilder sb = new StringBuilder();
		    line =br.readLine();

		    while (line != null) {
		    	//System.out.println(line);
		        if(!line.equals("Activities:")){
		        	this.description = sb.toString();
		        	sb.delete(0, sb.length()-1);
		        	ReadingActivities = true;
		        }
		        line = br.readLine();
		        if(!(line.equals("Name:") || line.equals("Description:"))){
			        sb.append(line);
			        sb.append(System.lineSeparator());
		        }


		        if(ReadingActivities){

		        	switch((cont%4)){
			        	case 0:
			        		act = new Activity();
			        		act.setName(line);
			        		++cont;
			        		break;
		        		case 1:
		        			act.setCost(Double.parseDouble(line));
		        			++cont;
		        			break;
		        		case 2:
		        			act.setType(line);
		        			++cont;
		        			break;
		        		case 3:
		        			activities.add(act);
		        			++cont;
		        			break;
		        		default:
		        			break;
		        	}
		        }

		    }
		    s = sb.toString();

		} finally {
		    br.close();
		}
		// nome
		// descrição
		// atividades
	}

	public int newStage(String stageName) {
		Stage s = new Stage(stageName);
		stages.add(s);
		return stages.size()-1;
	}

	public boolean addActivity(int stageID, int actID) {
        Stage s;
        Activity a;
	    try {
            s = stages.get(stageID);
            a = activities.get(actID);
        } catch (Exception e){
	        return false;
        }

	    return s.addActivity(a);
	}
}
