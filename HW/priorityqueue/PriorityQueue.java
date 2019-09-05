import java.util.*;

public class PriorityQueue<PriorityQueueItem> {
	private List<PriorityQueueItem> items;
	private float maxPriority = 0.0f;
	private float minPriority = Integer.MIN_VALUE;
	private int maxSize = Integer.MAX_VALUE;

	public PriorityQueue() {
		items = new ArrayList();
	}

	public PriorityQueue(int size) {
		items = new ArrayList(maxSize = size);
	}

	/**
	 * method takes an input PriorityQueueItem object and performs an insertion sort
	 * to store it using a binary search
	 */
	public synchronized void add(PriorityQueueItem item) {
		int max = 0, min = items.size() - 1, position = min / 2;
		float priority = item.getPriority();

		// Add student at front of queue if priority is greater than current maximum
		if (priority > maxPriority && items.size() < maxSize) {
			maxPriority = priority;
			items.add(0, item);
			return;
		}

		// Add the item when the size is 1. The algorithm hasn'PriorityQueueItem scaled
		// yet
		if (items.size() == 1) {
			items.add(item);
			return;
		}

		PriorityQueueItem ref = null;
		boolean insertAfter = false;
		do {
			ref = items.get(position);

			// Add in upper half of data
			if (priority < ref.getPriority()) {
				max = position;
				int val = (int) Math.ceil((double) (min - max) / 2.0);
				val = val == 0 ? val++ : val;
				position = max + val;
				insertAfter = position >= items.size() ? true : false;

			} else { // Add in lower half
				min = position;
				int val = (int) Math.ceil((double) (min - max) / 2.0);
				val = val == 0 ? val++ : val;
				position = min - val;
			}
		} while ((min - max) > 1);

		if (insertAfter) {
			items.add(item);
		} else {
			position += (items.get(position).getPriority() > priority) ? 1 : 0;
			if (items.size() == maxSize && position != maxSize) {
				items.remove(maxSize-1);
				items.add(position, item);
			}
		}
	}

	public int getMaxSize() {
		return maxSize;
	}

	public synchronized PriorityQueueItem remove() {
		return items.size() > 0 ? items.remove(0) : null;
	}

	public synchronized List<PriorityQueueItem> getItems() {
		return items;
	}

	public static interface PriorityQueueItem {
		public float getPriority();
	}

	public static void main(String ...args) {
		PriorityQueue<PriorityQueueItem> queue = new PriorityQueue(128);
		
		for (int x = 0; x < 1000; x++) {
			float i = (float) (Math.random() * 1000000);
			PriorityQueueItem pqi  = new PriorityQueueItem() {
				public float getPriority() {
					return (float) i;
				}
			};
			queue.add(pqi);
		}
		int counter = 0;
		for (PriorityQueueItem pqi : queue.getItems()) {
			System.out.println(counter++ + " : " + pqi.getPriority());
		}
	}
}

