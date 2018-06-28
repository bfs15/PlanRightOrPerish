package objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dev {

	private String name = "";
	private double productivity = 0;
	private double salary = 0;
	private String role = "";
	private Activity activity = null;
	private Event status = new Event();

    private static List<String> nameList = new ArrayList<>();

    //static initializer
    static {
        readNames();
    }

    public static void readNames(){
        String fileName = "database/NameDB.txt";
        String line;

        try {
            FileReader fileReader =  new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                nameList.add(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void destroy() {
		if(activity != null){
			activity.rmDev(this);
		}
	}

	public boolean endDay() {
		boolean isWorking = status.isAvailable();
        boolean completedAct = false;
		boolean roll = false;
		
		if(isWorking && activity !=null){
			double myWork = productivity;
			if(activity.getType() != role)
				myWork *= 0.7;
			
			System.out.println("Work done by"+this.getName()+": "+(myWork + myWork*Math.log(activity.getComputerNo()+1)));
            completedAct = activity.work(myWork + Math.log10(myWork*activity.getComputerNo()));
			roll = status.roll();
		}

		return roll;
	}

	public double getSalary() {
		return salary;
	}
	
	public void rollDev() {
		Random r = new Random();
		// obtem um nome aleatoriamente
		this.name = nameList.get(r.nextInt(nameList.size()));
		// (0,1) * valor limitante
		double prodMin = 100;
		double prodMax = 400 - prodMin;
		this.productivity  = r.nextDouble() * prodMax + prodMin;
		this.salary = Math.floor(this.productivity);

		// salary is a multiple of productivity with some noise
		this.salary = productivity*2; // deserving salary
		this.salary = Math.floor(this.salary);
		// salary is between (0.8, 1.2) times of the dev's deserving salary
        double noiseMin = 0.8;
        double noiseMax = 1.2 - noiseMin;
		double noiseMult = r.nextDouble()*noiseMax + noiseMin;
		salary *= noiseMult;

		// obtem um papel aleatoriamente
        ArrayList<String> possibleRoles = new ArrayList<>();
		possibleRoles.add("Network engineer");
		possibleRoles.add("Software engineer");
        possibleRoles.add("Software tester");
		possibleRoles.add("Web developer");
		possibleRoles.add("Database Specialist");
		possibleRoles.add("Super Hacker");
		this.role = possibleRoles.get(r.nextInt(possibleRoles.size()));
	}

	public void setActivity(Activity act) {
		activity = act;
	}
	
	public Activity getActivity() {
		return activity;
	}

	public void unsetActivity() {
        activity = null;
	}

	public String getName() {
		return name;
	}

	public double getProductivity() {
		return productivity;
	}

	public String getRole() {
		return role;
	}

	public Event getStatus() {
		return status;
	}

	public String getEvent() {
		return status.getDescription();
	}
}
