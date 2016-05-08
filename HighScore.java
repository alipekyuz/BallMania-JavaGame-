import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class HighScore {

	public HighScore(String str) {

		String txt = "";
		PrintWriter pw = null;
		File Checker = null;
		Scanner sc = null;
		try {
			Checker = new File("HighScore.txt");
			Checker.createNewFile();
			sc = new Scanner(new File("HighScore.txt"));
			String s = String.valueOf(Game.a);
			pw = new PrintWriter(Checker);
			pw.write(str);

		} catch (FileNotFoundException fnfe) {

			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			sc.close();
			pw.close();
		}
	}
}