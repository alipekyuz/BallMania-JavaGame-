import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Score {

	public Score() {

		String txt = "";
		PrintWriter pw = null;
		File Checker = null;
		Scanner sc = null;
		try {
			Checker = new File("Score.txt");
			Checker.createNewFile();
			sc = new Scanner(new File("Score.txt"));
			
			while (sc.hasNextLine()) {
				txt = txt.concat(sc.nextLine() + "\n");
			}
			
			String str = JOptionPane.showInputDialog(null, "Enter your name: ");
			String s = String.valueOf(Game.a);
			txt = txt.concat(str +" : ");
			txt = txt.concat(s + " point ");
			pw = new PrintWriter(Checker);
			pw.write(txt + "\n");

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