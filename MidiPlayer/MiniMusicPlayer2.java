import javax.sound.midi.*;

public class MiniMusicPlayer2 implements ControllerEventListener {
	//监听ControllerEvent
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MiniMusicPlayer2 mini = new MiniMusicPlayer2();
		mini.go();
	}
	public void go() {
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			//向sequencer注册事件。注册的方法取用监听者与代表想要监听的事件的int数组
			int[] eventsIWant = {127};
			sequencer.addControllerEventListener(this, eventsIWant);
			
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			
			for (int i = 5; i < 60; i += 4) {
				track.add(makeEvent(144,1,i,100,i));
				//插入事件编号127的自定义ControllerEvent(176)，不会做任何事情，只是让我们知道有音符被播放
				track.add(makeEvent(176,1,127,0,i));
				
				track.add(makeEvent(128, 1, i,100, i+2));
			}
			
			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(220);
			sequencer.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void controlChange(ShortMessage event) {
		System.out.println("la");//获知事件时在命令打印出字符串的事件处理程序
	}
	
	public static MidiEvent makeEvent(int comd, int chan, int one ,int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a,tick);
		} catch(Exception e) {}
		return event;
	}
}
