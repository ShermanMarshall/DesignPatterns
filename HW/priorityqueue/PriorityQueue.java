package priorityqueue;

import java.util.List;
import java.util.ArrayList;

public class PriorityQueue {
	private List<Student> students = new ArrayList();
	private float maxPriority = 0.0f;

	public PriorityQueue() {}

	/**
	 * method takes an input Student object and performs an
	 * insertion sort to store it using a binary search
	 */
	public void add(Student student) {
		int max = 0, min = students.size() - 1, position = min / 2;
		float priority = student.getPriority();

		//Add student at front of queue if priority is greater than current maximum
		if (priority > maxPriority) {
			maxPriority = priority;
			students.add(0, student);
			return;
		}

		//Add the student when the size is 1. The algorithm hasn't scaled yet
		if (students.size() == 1) {
			students.add(student);
			return;
		}

		Student ref = null;
		boolean insertAfter = false;
		do {
			ref = students.get(position);
		
			//Add in upper half of data
			if (priority < ref.getPriority()) {
				max = position;
				int val = (int) Math.ceil((double) (min - max) / 2.0);
				val = val == 0 ? val++ : val;
				position = max + val;
				insertAfter = position >= students.size() ? true : false;

			} else { //Add in lower half
				min = position;
				int val = (int) Math.ceil((double) (min - max) / 2.0);
				val = val == 0 ? val++ : val;
				position = min - val;
			}
		} while ((min - max) > 1);
	
		if (insertAfter) {
			students.add(student);
		} else {
			//If position located is greater, add after
			if (students.get(position).getPriority() > priority) {
				students.add(position + 1, student);
			} else {
				students.add(position, student);
			}
		}
	}

	public Student remove() {
		return students.size() > 0 ? students.remove(0) : null;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void output() {
		for (Student s : students) {
			System.out.println(s.toString());
		}
	}
}
