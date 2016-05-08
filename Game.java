/*Ali Ýpekyüz(150112006)
 *Zeynep Çamurdan(150113042)*/

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Game extends JFrame implements Runnable, KeyListener {

	JLabel b1, b2, b3, b4, b5; // b1,b2,b3,b4 are blocks and b5 is ball
	JPanel p1, p2;
	JTextField t1;
	static int a ; //score = a
	static int t=2;
	int score =0 ;
	int x = 240, bx1, bx2, bx3, bx4, bx5;
	int y = 400, by1, by2, by3, by4, by5 = by3;
	int scroll = 8; // Maze's speed of move up toward
	int move = 8; // Ball's speed of move
	Random r = new Random();
	boolean contact, dead, wait; // contact:Between ball and block's contact
	Thread game; // Thread supllies to move together ball and blocks
	ImageIcon ic1, ic2, ic3;

	public void positionBlocks() {
		// position of blocks in x-axis
		bx1 = 10;
		bx2 = 50;
		bx3 = 100;
		bx4 = 140;
		bx5 = bx4; //position of ball in x-axis when the game begin.

		// position of block in y-axis
		by1 = 0;
		by2 = 66;
		by3 = 130;
		by4 = 196;
		by5 = by4; //position of ball in y-axis when the game begin.
	}

	public Game() {

		setTitle("Ball Mania");
		setSize(246, 429);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(510, 140, 246, 429);

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"images\\ballicon.png"));

		positionBlocks();

		b1 = new JLabel();
		b2 = new JLabel();
		b3 = new JLabel();
		b4 = new JLabel();
		b5 = new JLabel();

		ic1 = new ImageIcon("images\\block.jpg");
		ic2 = new ImageIcon("images\\ball.gif");

		b1.setIcon(ic1);
		b2.setIcon(ic1);
		b3.setIcon(ic1);
		b4.setIcon(ic1);
		b5.setIcon(ic2);

		t1 = new JTextField("Score==>	" + score, 10);
		t1.setBackground(Color.BLACK);
		t1.setEnabled(false);

		p1 = new JPanel();
		p2 = new JPanel();

		p1.setBackground(Color.WHITE);
		p1.setBounds(0, 0, x, 20);
		p1.setLayout(new GridLayout(1, 1));
		p1.add(t1);

		p2.setBackground(Color.CYAN);
		p2.setBounds(0, 20, x, y);
		p2.setLayout(null);
		BallMania();

		p2.add(b1);
		p2.add(b2);
		p2.add(b3);
		p2.add(b4);
		p2.add(b5);

		getContentPane().add(p1);
		getContentPane().add(p2);

		show();
		addKeyListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		game = new Thread(this);
		game.start();

	}

	public void BallMania() {
		// length and width of blocks
		b1.setBounds(bx1, by1 + 10, 60, 10);
		b2.setBounds(bx2, by2 + 10, 50, 10);
		b3.setBounds(bx3, by3 + 10, 45, 10);
		b4.setBounds(bx4, by4 + 10, 40, 10);
		b5.setBounds(bx5, by5, 10, 10);

		if (bx5 <= 0 || bx5 >= x || by5 >= y || by5 <= 0) {
			dead = true;
			t1.setText("              >>>>>GAME OVER<<<<<");

			new Score();
			Button button5 = new Button("NEW GAME");
			button5.setForeground(Color.WHITE);
			button5.setBounds(80, 200, 75, 25);
			p2.removeAll();
			p2.repaint();
			p2.add(button5);
			button5.setFont(new Font("Times New Roman",
					Font.BOLD | Font.ITALIC, 12));
			button5.setBackground(Color.BLACK);
			button5.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					new Game();
					setVisible(false);
				}
			});
			
			Button button6 = new Button("SCORE");
			button6.setBounds(80,235,75,25);
			p2.add(button6);
			button6.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
			button6.setBackground(Color.BLACK);
			button6.setForeground(Color.WHITE);
			
			String txt = "";
			Scanner sc = null;

			try {
				sc = new Scanner(new File("HighScore.txt"));
				
					txt = txt.concat(sc.nextLine());

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				sc.close();
			}
			
			if(Integer.parseInt(txt)<score){
				HighScore asd = new HighScore(score+"");
			}
			
			button6.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

					String txt = "";
					Scanner sc = null;
					String txt1= "";
					Scanner sc1 = null;

					try {
						sc = new Scanner(new File("HighScore.txt"));
						while (sc.hasNextLine()) {
							txt = txt.concat(sc.nextLine() + "\n");
						
						}

						
						

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						sc.close();
					}
					
					try {
						sc1 = new Scanner(new File("Score.txt"));
						while (sc1.hasNextLine()) {
							txt1 = txt1.concat(sc1.nextLine() + "\n");
						
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						sc1.close();
					}
					JOptionPane.showMessageDialog(null, "High Score: " + txt + "\n" + txt1);

				}
			});
			try {
				game.join();
			} catch (InterruptedException e) {
			}

		}

		if (((by5 == by1) && (bx1 - 10 < bx5 && bx5 < (bx1 + 60)))
				|| ((by5 == by2) && (bx2 - 10 < bx5 && bx5 < (bx2 + 50)))
				|| ((by5 == by3) && (bx3 - 10 < bx5 && bx5 < (bx3 + 45)))
				|| ((by5 == by4) && (bx4 - 10 < bx5 && bx5 < (bx4 + 40)))) {
			contact = true;
		} else {
			contact = false;
		}

		if (by1 <= 0) { // by1 musnt'n equal zero or smaller than zero
			by1 = 350;
			bx1 = 5 + r.nextInt(x - 60);
			if (dead == true) {
				bx5 = bx1 + 36;
				by5 = by1 - 12;
				dead = false;
			}

		} else if (by2 <= 0) { // by2 musnt'n equal zero or smaller than zero
			by2 = 350;
			bx2 = 5 + r.nextInt(x - 50);
			if (dead == true) {
				bx5 = bx2 + 36;
				by5 = by2 - 12;
				dead = false;
				contact = true;
			}

		} else if (by3 <= 0) { // by3 musnt'n equal zero or smaller than zero
			by3 = 350;
			bx3 = 5 + r.nextInt(x - 45);
			if (dead == true) {
				bx5 = bx3 + 36;
				by5 = by3 - 12;
				dead = false;
			}

		} else if (by4 <= 0) { // by4 musnt'n equal zero or smaller than zero
			by4 = 350;
			bx4 = 5 + r.nextInt(x - 40);
			if (dead == true) {
				bx5 = bx4 + 36;
				by5 = by4 - 12;
				dead = false;
			}

		}

		// If number decrease, block moves up and if number not decrease, block
		// doesn't move
		by1 -= 1;
		by2 -= 1;
		by3 -= 1;
		by4 -= 1;

		if (contact == false) {
			by5++;
			if (by5 == by1 || by5 == by2 || by5 == by3 || by5 == by4){
				a=score+(move / 8* 10);
				score+=(move / 8* 10); //calculation of score.
				t1.setText("Score==>	" + (score)); 
				
			}
		}

		if (contact == true) {
			by5--;
		}
		repaint();
	}

	public void run() {
		for (;;) {
			BallMania();
			try {
				if (wait == true) {

					Thread.sleep(2000); // The game's speed of begin

				} else {
					Thread.sleep(scroll);
				}
			} catch (Exception e) {
			}
		}
	}

	// If we press right and left arrow keys, ball moves
	public void keyPressed(KeyEvent ke) {
		int key = ke.getKeyCode();
		if (wait == false) {
			if (ke.getKeyCode() == 37) {
				bx5 -= move; // left move
			}

			if (ke.getKeyCode() == 39) {
				bx5 += move; // right move
			}
		}
		if (key == KeyEvent.VK_P) {
			wait = true;
			t1.setText("                     >>>>>PAUSE<<<<<");
		}
		if (key == KeyEvent.VK_ENTER && wait) {
			final Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				public void run() {
					t1.setText("               The Game will begin===> " + t);
					t--;
					if (t == -2) {
						timer.cancel();
						t = 2;
						t1.setText("Score==>	" + (score));
					}
				}
			};
			timer.schedule(task, 0, 500);

			wait = false;
		}
		repaint();
	}

	public void keyReleased(KeyEvent ke) {
	}

	public void keyTyped(KeyEvent ke) {
	}
}
