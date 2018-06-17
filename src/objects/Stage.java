package objects;

import java.util.ArrayList;
import java.util.List;

public class Stage {

	private String name;
	private int workDays;
	private boolean complete;
	private List<Activity> activities = new ArrayList<Activity>();
	
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

	public boolean setWorkDays(int workDays) {
		boolean succ = false;
		try {
			this.workDays = workDays;
			succ = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		return succ;
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
