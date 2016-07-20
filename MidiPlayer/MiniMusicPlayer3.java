import javax.sound.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class MiniMusicPlayer3 {
	static JFrame f = new JFrame("My First Music Video");
	static MyDrawPanel ml;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MiniMusicPlayer3 mini = new MiniMusicPlayer3();
		mini.go();
	}
	
	public void setUpGui() {
		ml = new MyDrawPanel();
		f.setContentPane(ml);
		f.setBounds(30,30,300,300);
		f.setVisible(true);
	}
	
	public void go() {
		setUpGui();
		
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			//向sequencer注册事件。注册的方法取用监听者与代表想要监听的事件的int数组
			//int[] eventsIWant = {127};
			sequencer.addControllerEventListener(ml, new int[] {127});
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			
			int r = 0;
			for (int i = 0; i < 60; i += 4) {
				
				r = (int) ((Math.random() * 50) +1);
				track.add(makeEvent(144,1,r,100,i));
				//插入事件编号127的自定义ControllerEvent(176)，不会做任何事情，只是让我们知道有音符被播放
				track.add(makeEvent(176,1,127,0,i));
				
				track.add(makeEvent(128, 1, r,100, i+2));
			}
			
			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(220);
			sequencer.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public MidiEvent makeEvent(int comd, int chan, int one ,int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a,tick);
		} catch(Exception e) {}
		return event;
	}
	
	class MyDrawPanel extends JPanel implements ControllerEventListener {
		boolean msg = false;
		
		public void controlChange(ShortMessage event) {
			msg = true;
			repaint();
		}
		
		public void paintComponent(Graphics g) {
			
			if (msg) {
				Graphics2D g2d = (Graphics2D)g;
				
				int red = (int) (Math.random() * 255);
				int green = (int)(Math.random() * 255);
				int blue = (int)(Math.random() * 255);
				g2d.setColor(new Color(red, green, blue));
				
				int ht = (int) ((Math.random() * 120) + 10);
				int width = (int)((Math.random() * 120) + 10);
				int x = (int)((Math.random() * 40) +10);
				
				int y = (int)((Math.random() * 40) +10);
				g2d.fillRect(x, y, ht, width);
				
				msg = false;
			}
			
		}
	}

}
