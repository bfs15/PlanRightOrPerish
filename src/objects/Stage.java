package objects;

import java.util.ArrayList;
import java.util.List;

public class Stage {

	private String name;
	private int workDays;
	private boolean complete;
	private List<Activity> activities = new ArrayList<>();
	
	public Stage(String stageName) {
        name = stageName;
	}

	public boolean endDay() {
		var complete = calcComplete();
		return complete;
	}

	public boolean calcComplete() {
		for(Activity a : activities){
			if(!a.isComplete()){
				return false;
			}
		}

		return true;
	}

	public boolean addActivity(Activity a){
        if(activities.indexOf(a) != -1){
            return false;
        }

        activities.add(a);
	    return true;
    }

	public String getName() {
		return name;
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
