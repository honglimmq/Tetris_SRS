package tetris;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;


public class MainPanel extends JPanel{	
	JPanel playingGrid;
	JPanel leftPanel;
	JPanel rightPanel;
	JPanel topPanel;
	
	
	MainPanel(){
		playingGrid = new TetrisBoard();
		leftPanel = new SidePanel(4, 4);
		rightPanel = new SidePanel(4, 10);
		topPanel = new SidePanel(20, 1);
		
		this.setLayout(new BorderLayout(30, 0));
		this.setBackground(new Color(0x000000));
		

		this.add(rightPanel, BorderLayout.EAST);
		this.add(topPanel, BorderLayout.NORTH);
		this.add(leftPanel, BorderLayout.WEST);
		this.add(playingGrid, BorderLayout.CENTER);
	}
}
