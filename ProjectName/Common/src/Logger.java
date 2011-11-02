import java.io.*;
import java.text.DecimalFormat;
import java.util.Calendar;

public class Logger {

	public Logger() {
		writeOut();
	}

	public void writeOut() {
		try {
			String filename = formatTime() + ".txt";
			FileWriter outFile = new FileWriter(filename);
			PrintWriter out = new PrintWriter(outFile);

			// Also could be written as follows on one line
			// Printwriter out = new PrintWriter(new FileWriter(args[0]));

			// Write text to file
			out.println("Hello World");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String formatTime() {
		String output = "";
		DecimalFormat fmt = new DecimalFormat("00");
		Calendar now = Calendar.getInstance();
		int yyyy = now.get(Calendar.YEAR);
		int mm = now.get(Calendar.MONTH) + 1;
		int dd = now.get(Calendar.DAY_OF_MONTH);
		int h = now.get(Calendar.HOUR_OF_DAY);
		int m = now.get(Calendar.MINUTE);
		int s = now.get(Calendar.SECOND);
		output = String.format("%s_%s_%s_%s_%s_%s", yyyy, fmt.format(mm),
				fmt.format(dd), fmt.format(h), fmt.format(m), fmt.format(s));
		return output;
	}

	public static void main(String[] args) {

		new Logger();
	}
}
