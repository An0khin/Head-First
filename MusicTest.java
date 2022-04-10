import javax.sound.midi.*;

public class MusicTest{
	public void play(int instrument, int note){
		try{
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();

			Sequence seq = new Sequence(Sequence.PPQ, 4);

			Track tr = seq.createTrack();

			ShortMessage a1 = new ShortMessage();
			a1.setMessage(192, 1, instrument, 100);
			MidiEvent changeInst = new MidiEvent(a1, 1);
			tr.add(changeInst);

			ShortMessage a = new ShortMessage();
			a.setMessage(144, 1, note, 100);
			MidiEvent noteOn = new MidiEvent(a, 1);
			tr.add(noteOn);

			ShortMessage b = new ShortMessage();
			b.setMessage(128, 1, note, 100);
			MidiEvent noteOff = new MidiEvent(b, 16);
			tr.add(noteOff);

			sequencer.setSequence(seq);

			sequencer.start();
		} catch (MidiUnavailableException e) {
			System.out.println(e);
		} catch(InvalidMidiDataException e) {
			System.out.println(e);
		} finally {	//Этот блок кода выполниться, не важно было исключение или нет
			System.out.println("The end"); 
		}
	}
	public static void main(String[] args) {
		MusicTest m = new MusicTest();
		m.play(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
	}
}