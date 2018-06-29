package objects;

import java.util.Random;

public class Event {

	private int type;
	private int duration;
	private String description = "";

	public boolean isAvailable() {
		if(type == 1)
			return false;

		return true;
	}

	public boolean roll() {
		Random r = new Random();
		double randNum  = r.nextDouble();
		if(randNum < 0.1){
			description = "is sick at home";
			type = 1;

			int maxDuration = 3;
			duration = 1 + r.nextInt(maxDuration);
			return true;
		}

		return false;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
