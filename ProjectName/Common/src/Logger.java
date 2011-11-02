import java.io.*;
import java.util.Calendar;

public class Logger {

	public Logger(String fileName) {
		writeOut(fileName);
	}

	public void writeOut(String filename) {
		try {
			FileWriter outFile = new FileWriter(filename);
			PrintWriter out = new PrintWriter(outFile);

			// Also could be written as follows on one line
			// Printwriter out = new PrintWriter(new FileWriter(args[0]));

			// Write text to file
			out.println("This is line 1");
			out.println("This is line 2");
			out.print("This is line3 part 1, ");
			out.println("this is line 3 part 2");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String formatTime() {
		String s = "";
		
		double time = System.currentTimeMillis();
		
		Calendar cal = Calendar.getInstance();
		int mm = cal.get(Calendar.MONTH) + 1;
		int dd = cal.get(Calendar.DATE);
		int yyyy = cal.get(Calendar.YEAR);
		
		
		return s;
	}

	public static void main(String[] args) {
		String out = Double.toString(System.currentTimeMillis());
		new Logger(out);
	}
}
