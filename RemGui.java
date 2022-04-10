import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RemGui{
	JButton bt1;
	JLabel lab1;
	JEditorPane ep1;
	JTextArea ta1;	
	public static void main(String[] args) {
		RemGui rg = new RemGui();
		rg.start();
	}
	public void start(){
		JFrame frame = new JFrame("Simple GUI"); //Изначально диспетчер компоновки BorderLayout(есть 5 зон)
		JPanel pan = new JPanel(); //Изначально диспетчер компоновки FlowLayout(все размещается по горизонтали)
		pan.setBackground(Color.green);
		pan.add(new JButton());
		pan.add(new JButton());
		pan.add(new JButton());

		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS)); //Изменяем диспетчер компоновки на BoxLayout(можно выбирать как размещать компоненты)

		bt1 = new JButton("Press me");
		bt1.addActionListener(new Button1());

		lab1 = new JLabel("Hello");

		ep1 = new JEditorPane();
		ta1 = new JTextArea();

		frame.getContentPane().add(BorderLayout.SOUTH,bt1);
		frame.getContentPane().add(BorderLayout.WEST,lab1);
		frame.getContentPane().add(BorderLayout.CENTER,ep1);
		frame.getContentPane().add(BorderLayout.NORTH,ta1);
		frame.getContentPane().add(BorderLayout.EAST,pan);

		frame.setSize(484,484);
		frame.setVisible(true);
	}
	class Button1 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			lab1.setText(ep1.getText());
		}
	}
}