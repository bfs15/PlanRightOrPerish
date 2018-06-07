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
	private List<Computer> computers =  new ArrayList<Computer>();;
	private List<Activity> activities =  new ArrayList<Activity>();;
	private List<Dev> dailyDevs = new ArrayList<Dev>();
	
	public void setLifeCycleModel() {

	}

	public boolean estabilishExpenses(double money) {
		return false;
	}

	public boolean setSchedule(int stageID, int workDays) {
		return false;
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

	public boolean addDailyDev() {
		return false;
	}

	public boolean addDev(int ID) {
		return false;
	}

	public boolean rmDev() {
		return false;
	}

	public Dev getIdleDevs() {
		return null;
	}

	public boolean addDevOnActivity(int ActivityID, int DevID) {
		return false;
	}

	public boolean rmDevOnActivity(int ActivityID, int DevID) {
		return false;
	}

	public double addComputer(int quantity) {
		return 0;
	}

	public double rmComputer(int quantity) {
		return 0;
	}

	public Activity getActivity(int ID) {
		return null;
	}

	public Dev getDev(int ID) {
		return null;
	}

	public List<Dev> getActivityDevs(int actID) {
		return null;
	}

	public int setComputer(int actID, int qnt) {
		return 0;
	}

	public List<Computer> getIdleComputers() {
		return null;
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

	public void setDailyDevs(List<Dev> dailyDevs) {
		this.dailyDevs = dailyDevs;
	}

}
