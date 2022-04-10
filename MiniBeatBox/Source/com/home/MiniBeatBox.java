package com.home;


import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class MiniBeatBox {
	ArrayList<JCheckBox> checkList = new ArrayList<JCheckBox>();
	boolean[] checkBox;
	JFrame frame;
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	String[] inst = new String[] {"Bass Drum","Closed Hi-Hat","Open Hi-Hat","Acoustic Snare","Crash Cymbal",
									"Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle",
									"Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
	int[] instNumb = new int[] {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};


	public static void main(String[] args) {
		MiniBeatBox bb = new MiniBeatBox();
		bb.start();
	}
	public void start(){
		
		frame = new JFrame();
		JPanel back = new JPanel(new BorderLayout());
		back.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		Box listInst = new Box(BoxLayout.Y_AXIS);
		for(int i = 0; i < 16; i++){
			listInst.add(new JLabel(inst[i]));
		}
		back.add(BorderLayout.WEST, listInst);

		GridLayout grid = new GridLayout(16,16);
		grid.setVgap(1);
		grid.setHgap(2);
		JPanel panel = new JPanel(grid);
		back.add(BorderLayout.CENTER, panel);
		for (int i = 0; i < 256; i++){
			JCheckBox c = new JCheckBox();
			checkList.add(c);
			c.setSelected(false);
			panel.add(c);
		}

		Box buttons = new Box(BoxLayout.Y_AXIS);

		JButton start = new JButton("Start");
		start.addActionListener(new StartListener());

		JButton stop = new JButton("Stop");
		stop.addActionListener(new StopListener());

		JButton upTempo = new JButton("Up tempo");
		upTempo.addActionListener(new UpTempoListener());

		JButton downTempo = new JButton("Down tempo");
		downTempo.addActionListener(new DownTempoListener());


		buttons.add(start);
		buttons.add(stop);
		buttons.add(upTempo);
		buttons.add(downTempo);

		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("File");

		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new Serializator());
		JMenuItem load = new JMenuItem("Load");
		load.addActionListener(new Deserializator());

		menu.add(save);
		menu.add(load);
		menuBar.add(menu);

		back.add(BorderLayout.EAST,buttons);


		try{
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ,4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);
		} catch(Exception ex) {}
		
		frame.setBounds(50,50,300,300);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(back);
		frame.pack();
	
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	class StartListener implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			startAction();
		}
	}
	class StopListener implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			sequencer.stop();
		}
	}
	class UpTempoListener implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			float tempo = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float) (tempo * 1.03));
		}
	}
	class DownTempoListener implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			float tempo = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float) (tempo * 0.97));
		}
	}
	class Serializator implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			checkBox = new boolean[256];

			for(int i = 0; i < 256; i++){
				if (((JCheckBox) checkList.get(i)).isSelected()) {
					checkBox[i] = true;
				}
			}
			try {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filt = new FileNameExtensionFilter("Serialize", "ser");
				fc.setFileFilter(filt);
				fc.showSaveDialog(frame);
				ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fc.getSelectedFile()));
				os.writeObject(checkBox);
				os.close();
			} catch(Exception ex){}
			
		}
	}
	class Deserializator implements ActionListener{
		public void actionPerformed(ActionEvent ae){

			try{
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filt = new FileNameExtensionFilter("Serialize", "ser");
				fc.setFileFilter(filt);
				fc.showOpenDialog(frame);
				ObjectInputStream is = new ObjectInputStream(new FileInputStream(fc.getSelectedFile()));
				checkBox = (boolean[]) is.readObject();
				is.close();
			}catch(Exception ex){}

			for (int i = 0; i < 256; i++){
				JCheckBox box = (JCheckBox) checkList.get(i);
				if (checkBox[i]){
					box.setSelected(true);
				} else {
					box.setSelected(false);
				}
			}
			sequencer.stop();
			startAction();
		}
	}

	public void startAction(){
		int[] trackList;

		sequence.deleteTrack(track);
		track = sequence.createTrack();

		for (int i = 0; i < 16; i++){
			trackList = new int[16];
			int instrument = instNumb[i];

			for(int j = 0; j < 16; j++){
				JCheckBox cb = checkList.get(j + (16*i));

				if (cb.isSelected()){
					trackList[j] = instrument;
				} else {
					trackList[j] = 0;
				}
			}

			for (int k = 0; k < 16; k++){
				if(trackList[k] != 0) {
					track.add(makeEvent(144,9,trackList[k],100,k));
					track.add(makeEvent(128,9,trackList[k],100,k+2));
				}
			}
		}

		try{
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);
		} catch(Exception ex) {}
	}

	public MidiEvent makeEvent(int com, int chan, int note, int power, int place){
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(com, chan, note, power);
			return new MidiEvent(a, place);
		} catch (Exception ex) {};
		return null;
	}
}
