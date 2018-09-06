package priorityqueue;

public class Student {
	private String name = "";
	private String email = "";
	private int redId = 0;

	private int units;
	private float gpa;
	private float priority;

	public Student(int units, float gpa) {
		this.units = units;
		this.gpa = gpa;
		this.priority = (units * 0.7f) + (gpa * 0.3f);
	}

	public int getUnits() {
		return units;
	}

	public float getGPA() {
		return gpa;
	}
	
	public float getPriority() {
		return priority;
	}

	public String toString() {
		return new StringBuilder("Name: ")
				.append(name)
				.append(", Red Id: ")
				.append(Integer.toString(redId))
				//.append(", Priority: ")
				//.append(Float.toString(priority))
				.toString();
	}
}
