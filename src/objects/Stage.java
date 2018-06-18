package objects;

import java.util.ArrayList;
import java.util.List;

public class Stage {

	private String name;
	private int workDays;
	private boolean complete = false;
	private List<Activity> activities = new ArrayList<>();
	
	public Stage(String stageName) {
        name = stageName;
	}

	public boolean endDay() {
		return calcComplete();
	}

	public boolean calcComplete() {
		for(Activity a : activities){
			if(!a.isComplete()){
				return false;
			}
		}

        complete = true;
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
