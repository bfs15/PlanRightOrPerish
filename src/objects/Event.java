package objects;

public class Event {

	private int type;
	private int duration;
	private String description;

	public boolean isAvailable() {
		return false;
	}

	public boolean roll() {
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
