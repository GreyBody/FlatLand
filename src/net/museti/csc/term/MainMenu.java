package net.museti.csc.term;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu {

	public static final String title = "Matt Szczuka's FlatLand 0.01 Alpha";
	public static int sizeIndex;

	private JFrame frame;
	private JPanel window;

	public MainMenu() {
		frame = new JFrame(title);
		window = new JPanel();
		frame.setSize(new Dimension(MenuSettings.sizesWidth[sizeIndex], MenuSettings.sizesHeight[sizeIndex]));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().add(window);
		window.setLayout(null);
		frame.setVisible(true);
		add();
	}

	private void add() {
		int bwidth = 180;
		int bheight = 45;
		JButton bview = new JButton("Enter World");
		JButton bsettings = new JButton("Settings");
		JButton bexit = new JButton("Exit");
		bview.setBounds(frame.getWidth() / 2 - bwidth / 2, frame.getHeight() / 2 - bheight / 2 - bheight - 5, bwidth, bheight);
		bsettings.setBounds(frame.getWidth() / 2 - bwidth / 2, frame.getHeight() / 2 - bheight / 2, bwidth, bheight);
		bexit.setBounds(frame.getWidth() / 2 - bwidth / 2, frame.getHeight() / 2 - bheight / 2 + bheight + 5, bwidth, bheight);
		bview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameEngine g = new GameEngine(MenuSettings.sizesWidth[sizeIndex], MenuSettings.sizesHeight[sizeIndex]);
				g.frame.setResizable(false);
				g.frame.add(g);
				g.frame.pack();
				g.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				g.frame.setLocationRelativeTo(null);
				g.frame.setVisible(true);
				g.start();
				frame.dispose();
			}
		});
		bsettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuSettings();
				frame.dispose();
			}
		});
		bexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		window.add(bview);
		window.add(bsettings);
		window.add(bexit);
	}

	public static void main(String[] args) {
		new MainMenu();
	}
}
