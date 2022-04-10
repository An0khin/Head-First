package test.gui;

import javax.swing.*; //Для GUI
import java.awt.event.*; //Для ActionListener
import java.awt.*;

public class GUI{

	int x = 5;
	int y = x;

	JButton but;
	JFrame frame;
	JLabel label;

	public static void main(String[] args) {
		GUI g = new GUI();
		g.start();
	}
	public void start(){
		frame = new JFrame();

		Panel pan = new Panel();

		//frame.setContentPane(pan);

		but = new JButton("HELLO");
		but.addActionListener(new Draw()); //Кнопка добавляет слушателя(только объект реализующий ActionListener)

		label = new JLabel("JAYCOB");

		JButton butLab = new JButton("Change");
		butLab.addActionListener(new Change());

		frame.getContentPane().add(BorderLayout.EAST,label);
		frame.getContentPane().add(BorderLayout.WEST,butLab);
		frame.getContentPane().add(BorderLayout.NORTH,but);
		frame.getContentPane().add(BorderLayout.CENTER,pan);

		frame.setSize(300,300);
		frame.setVisible(true);
	}
	class Draw implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			but.setText("HIIII");
			for (int i = 0; i < 100; i++){
				frame.repaint();
				x++;
				y = x;
				try {
					Thread.sleep(50);
				} catch (Exception e) {};
			}
		}
	}
	class Change implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			label.setText("WILLIAM");
		}
	}
	class Panel extends JPanel{
		public void paintComponent(Graphics g) {
			// Image image = new ImageIcon("maxresdefault.jpg").getImage();

			// int width = (int) (Math.random() * 1000);


			// g.drawImage(image, 10, 10, width, width/2, this);

			g.setColor(Color.white);
			g.fillRect(0,0,this.getWidth(),this.getHeight());

			g.setColor(Color.blue);
			g.fillOval(x,y,100,100);
		}
	}
	
}