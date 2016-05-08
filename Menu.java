/*Ali Ýpekyüz(150112006)
 *Zeynep Çamurdan(150113042)*/

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Menu extends JFrame {

	private static Menu frame;
public static void main(String[] args) {
		
		frame = new Menu();
		JLabel background = new JLabel(new ImageIcon("images\\screen.jpg"));
		frame.setTitle("       Ball Mania");
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setSize(246, 429);
		frame.getContentPane().add(background);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

	public Menu() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"images\\ballicon.png"));
		setBounds(50, 50, 350, 450);
		
	    Button button1 = new Button("PLAY"); 
		button1.setBounds(70,250,100,25);
		this.add(button1);
		button1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		button1.setBackground(Color.RED);
		button1.addActionListener(new PlayListener());

		Button button2 = new Button("HOW TO PLAY?");
		button2.setBounds(70,280,100,25);
		this.add(button2);
		button2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 10));
		button2.setBackground(Color.RED);
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new HowToPlay();
			}
		});

		Button button3 = new Button("EXIT");
		button3.setBounds(70,310,100,25);
		this.add(button3);
		button3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 10));
		button3.setBackground(Color.RED);
		button3.addActionListener(new ExitListener());
		
		
	}
	class ExitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}	
	}

	
	class PlayListener implements ActionListener {

		@Override
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new Game();
		    frame.setVisible(false);
		}
	}
}
