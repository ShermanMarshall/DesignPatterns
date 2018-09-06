package priorityqueue;

import java.util.List;

public class PriorityQueueTest {
	public static PriorityQueue queue = new PriorityQueue();

	public static void initRandomQueue(int size) {

		for (int x = 0; x < size; x++) {
			int units = (int) (Math.random() * 150);

			float gpa = (float) (Math.random() * 4);
			Student	student = new Student(units, gpa);
	
			queue.add(student);
			if (x > 1) {
				//Assert 
					verifyQueueIsOrdered();
			}
		}

	}

	public static boolean verifyQueueIsOrdered() {
		List<Student> students = queue.getStudents();
		Student before = students.remove(0);

		for (Student after : students) {
			if (before.getPriority() < after.getPriority()) {
				return false;
			}
		}
		return true;
	}

	public static void output() { 
		queue.output();
	}

	public static void main(String[] args) {
		initRandomQueue(50);
	}
}
