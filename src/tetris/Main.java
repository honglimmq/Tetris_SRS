package tetris;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Main extends JFrame {
	public Main() {
		MainPanel mpanel = new MainPanel();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		add(mpanel);
		pack();
	}

	public static void main(String[] a) {
		new Main().setVisible(true);
	}
}
