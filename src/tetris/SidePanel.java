package tetris;

import java.awt.Dimension;

import javax.swing.JPanel;

public class SidePanel extends JPanel{
	final int sizeBox = 30;
	SidePanel(int w, int h){
		this.setPreferredSize(new Dimension(w*sizeBox, h*sizeBox));
	}
}
