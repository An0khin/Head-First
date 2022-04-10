package com.home;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class QuizBuilder{
	JFrame frame;
	ArrayList<QuizCard> cardList = new ArrayList<QuizCard>();
	JTextArea question;
	JTextArea answer;

	public static void main(String[] args){
		QuizBuilder qb = new QuizBuilder();
		qb.start();
	}
	public void start(){
		frame = new JFrame();

		Box panel = new Box(BoxLayout.Y_AXIS);

		JLabel lbl1 = new JLabel("Question");

		question = new JTextArea(6,20);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		JScrollPane quest = new JScrollPane(question);
		quest.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		quest.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JLabel lbl2 = new JLabel("Answer");

		answer = new JTextArea(6,20);
		JScrollPane answ = new JScrollPane(answer);
		answ.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		answ.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JButton nextQuiz = new JButton("Next Quiz");
		nextQuiz.addActionListener(new NextQuiz());

		panel.add(lbl1);
		panel.add(quest);
		panel.add(lbl2);
		panel.add(answ);
		panel.add(nextQuiz);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");

		JMenuItem newMenu = new JMenuItem("New");
		newMenu.addActionListener(new NewMenu());
		JMenuItem saveMenu = new JMenuItem("Save");
		saveMenu.addActionListener(new SaveMenu());
		JMenuItem quizGo = new JMenuItem("Quiz Go");
		quizGo.addActionListener(new QuizGo());

		menu.add(newMenu);
		menu.add(saveMenu);
		menu.add(quizGo);
		menuBar.add(menu);

		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, panel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(500,500);
		frame.setVisible(true);
	}
	public void clearCard(){
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}
	public void saveFile(File file){
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			for (QuizCard quiz : cardList){
				writer.write(quiz.getQuestion() + "/");
				writer.write(quiz.getAnswer() + "\n");
			}

			writer.close();
		}catch(Exception ex) {}
	}

	class NextQuiz implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			cardList.add(new QuizCard(question.getText(), answer.getText()));
			clearCard();
		}
	}
	class NewMenu implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			cardList.clear();
			clearCard();
		}
	}
	class SaveMenu implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			cardList.add(new QuizCard(question.getText(), answer.getText()));
			clearCard();

			JFileChooser fc = new JFileChooser();
			fc.showSaveDialog(frame);
			saveFile(fc.getSelectedFile());
		}
	}
}

class QuizCard{
	private String question;
	private String answer;
	public QuizCard(String a, String b){
		question = a;
		answer = b;
	}
	public String getQuestion(){
		return question;
	}
	public String getAnswer(){
		return answer;
	}
}

class QuizGo implements ActionListener{
	JButton nextQuiz;
	JFrame frame;
	JTextArea display;
	QuizCard currentCard;
	boolean showAnswer;
	ArrayList<QuizCard> list;
	int currentCardIndex;

	public void actionPerformed(ActionEvent ae){
		QuizGo qg = new QuizGo();
		qg.start();
	}

	public void start(){
		frame = new JFrame();

		Box panel = new Box(BoxLayout.Y_AXIS);

		display = new JTextArea(6,20);
		display.setLineWrap(true);
		display.setEditable(false);

		JScrollPane sp = new JScrollPane(display);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		nextQuiz = new JButton("Next Card");
		nextQuiz.addActionListener(new Quiz());

		JMenuBar mb = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem setQuiz = new JMenuItem("Choose quiz");
		setQuiz.addActionListener(new SetQuiz());

		file.add(setQuiz);
		mb.add(file);

		panel.add(display);
		panel.add(nextQuiz);
		
		frame.getContentPane().add(panel);
		frame.setJMenuBar(mb);

		frame.setSize(500,500);
		frame.setVisible(true);
	}
	class Quiz implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			if(showAnswer){
				display.setText(currentCard.getAnswer());
				nextQuiz.setText("Next Card");
				showAnswer = false;
			}else{
				if (currentCardIndex < list.size()){
					showNextCard();
				} else {
					nextQuiz.setEnabled(false);
				}
			}
		}
	}
	class SetQuiz implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(frame);
			loadFile(fc.getSelectedFile());
		}
	}
	public void loadFile(File file){
		list = new ArrayList<QuizCard>();

		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;

			while ((line = reader.readLine()) != null){
				makeCard(line);
			}
			reader.close();
		}catch(Exception ex){}
	}
	public void makeCard(String line){
		String[] temp = line.split("/");
		QuizCard qc = new QuizCard(temp[0],temp[1]);
		list.add(qc);
	}
	public void showNextCard(){
		currentCard = list.get(currentCardIndex);
		currentCardIndex++;
		display.setText(currentCard.getQuestion());
		nextQuiz.setText("Show Answer");
		showAnswer = true;

	}
}