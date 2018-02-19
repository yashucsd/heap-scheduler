

package hw6;

import java.io.*;
import java.util.Scanner;

public class EDF {

	/**
	 * prints a status report for the EDF output based on the type of operation
	 * that has occurred
	 * 
	 * @param choice
	 *            identifies the type of operation that precedes the printing
	 * @param current_time
	 *            the time at which this is printed
	 * @param r
	 *            the record that can be referred to to print
	 */
	public static void print(int choice, long current_time, Record r) {
		if (choice == 1)
			System.out.println(current_time + ": adding " + r.toString());
		else if (choice == 2)
			System.out.println(current_time + ": busy with " + r.toString());
		else
			System.out.println(current_time + ": done with " + r.toString(current_time));
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			// reacts to a lack of proper filename input
			System.err.println("Incorrect number of arguments: " + args.length);
			System.err.println("java hw6.EDF <input-file>");
			System.exit(1);
		}
		File file = new File(args[0]);
		MyPriorityQueue<Record> queue = new MyPriorityQueue<Record>(10);
		long current_time = 0;

		try {
			Scanner scnr = new Scanner(file);
			while (scnr.hasNextLine()) {
				// iterates through the lines of the file
				String cmd = scnr.next();
				if (cmd.equals("schedule")) {
					Record r = new Record(scnr.next(),
							scnr.nextLong(), scnr.nextLong());
					queue.add(r);
					// adds this scheduling record to the heap
					print(1, current_time, r);
				} else {
					long run_time = scnr.nextLong();
					Record r = null;

					while (current_time < run_time) {
						// attempts to perform records until time is out
						r = queue.poll();
						if (r == null)
							break;
						print(2, current_time, r);

						current_time += r.GetDuration();
						if (current_time <= run_time) {
							print(3, current_time, r);
							// marks the completion of a task
						}
					}

					long diff = current_time - run_time;
					current_time = run_time;

					if (diff > 0) {
						// notes if a task is incomplete and reinserts it in to
						// heap
						r = new Record(r, diff);
						queue.add(r);
						print(1, current_time, r);
					}
				}
			}
			scnr.close();

		} catch (FileNotFoundException e) {
			System.err.println("Failed to open " + file);
			System.exit(1);
		}
		System.exit(0);

	}

}
