public class Project {

	private char[] description;

	private char[] lifeCycleModel;

	private double budget;

	private double money;

	private int day = 0;

	private Stage currentStage;

	private Stage[] stages;

	private Dev[] devs;

	private Computer[] computers;

	private Activity[] activities;

	private Dev[] dailyDevs;

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

	public Activity getActivities() {
		return null;
	}

	public Dev dailyDevs() {
		return null;
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

	public Dev getActivityDevs(int actID) {
		return null;
	}

	public int setComputer(int actID, int qnt) {
		return 0;
	}

	public Computer getIdleComputers() {
		return null;
	}

}
