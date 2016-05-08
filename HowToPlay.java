/*Ali Ýpekyüz(150112006)
 *Zeynep Çamurdan(150113042)*/

import javax.swing.*;  
import javax.swing.plaf.synth.ColorType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HowToPlay extends JFrame {
	
	public HowToPlay() {
		Button button5 = new Button("BACK");
		button5.setForeground(Color.WHITE);
		button5.setBounds(0, 40, 50, 20);
		this.add(button5);
		button5.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		button5.setBackground(Color.BLACK);
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				BackListener();
			}
		});

		JLabel background = new JLabel(new ImageIcon("images\\screen2.png"));
		setTitle("       Ball Mania");
		setSize(246, 429);
		getContentPane().add(background);
		setLocationRelativeTo(null); // Center the frame
		setVisible(true);
		setResizable(false);
		setBounds(510, 140, 246, 429);

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"images\\ballicon.png"));

	}
	
    public void BackListener(){
    	
    	this.setVisible(false);
    }
    
    
    public static void main(String args[]){
    	
    	new HowToPlay();
    }
}
       