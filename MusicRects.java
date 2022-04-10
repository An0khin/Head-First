import javax.sound.midi.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MusicRects{
	JFrame frame;
	JButton but;
	DrawPanel cn;
	public static void main(String[] args){
		MusicRects mr = new MusicRects();
		mr.start();
	}
	public void start() {

		frame = new JFrame();
		cn = new DrawPanel();
		
		but = new JButton("Press");
		but.addActionListener(new Action());

		frame.getContentPane().add(cn);
		frame.getContentPane().add(BorderLayout.SOUTH, but);


		frame.setSize(500,500);
		frame.setVisible(true);

	}

	class Action implements ActionListener{
		public void actionPerformed(ActionEvent event){
			try {
				Sequencer sequencer = MidiSystem.getSequencer();
				sequencer.open();
				sequencer.addControllerEventListener(cn, new int[] {127});

				Sequence seq = new Sequence(Sequence.PPQ, 4);

				Track tr = seq.createTrack();

				for (int i = 0; i < 60; i++) {
					int note = (int) (Math.random()*120);
					tr.add(makeEvent(144,1,note,100,i));
					tr.add(makeEvent(176,1,127,0,i));	
					tr.add(makeEvent(128,1,note,100,i+2));
				}

				sequencer.setSequence(seq);

				sequencer.start();
			} catch (Exception e) {} 

		}
	}
	public MidiEvent makeEvent(int com, int thr, int one, int two, int freq){
		try {
			ShortMessage sm = new ShortMessage();
			sm.setMessage(com, thr, one, two);
			return new MidiEvent(sm, freq);
		} catch (Exception e) {}
		return null;
		
	}
	class DrawPanel extends JPanel implements ControllerEventListener{
		boolean get = false;
		public void controlChange(ShortMessage sm) {
			get = true;
			frame.repaint();
		}
		public void paintComponent(Graphics g){
			if (get) {
				int x =(int) (Math.random() * frame.getWidth());
				int y =(int) (Math.random() * frame.getHeight());
				int width =(int) (Math.random() * 200);
				int height =(int) (Math.random() * 200);

				int red = (int) (Math.random() * 255);
				int green = (int) (Math.random() * 255);
				int blue = (int) (Math.random() * 255);

				g.setColor(new Color(red,green,blue));

				g.fillRect(x,y,width,height);
			}
		}
	}

}