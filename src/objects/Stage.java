package objects;

import java.util.ArrayList;
import java.util.List;

public class Stage {

	private String name;
	private int workDays;
	private boolean complete;
	private List<Activity> activities = new ArrayList<Activity>();
	
	public Stage(String stageName) {
		// TODO Auto-generated constructor stub
	}



	public void endDay() {

	}



	public boolean calcComplete() {
		return false;
	}

	public boolean setSchedule(int workDays) {
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWorkDays() {
		return workDays;
	}

	public void setWorkDays(int workDays) {
		this.workDays = workDays;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public List<Activity> getActivities() {
		return activities;
	}
	
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

}
