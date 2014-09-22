package net.museti.csc.term;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuSettings {

	public static final String[] sizes = { "Default", "800x600" };
	public static final int[] sizesWidth = { 500, 800 };
	public static final int[] sizesHeight = { 500, 600 };

	private JFrame frame;
	private JPanel window;

	public MenuSettings() {
		frame = new JFrame(MainMenu.title + " - Settings");
		window = new JPanel();
		frame.setSize(new Dimension(sizesWidth[MainMenu.sizeIndex], sizesHeight[MainMenu.sizeIndex]));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(window);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		window.setLayout(null);
		frame.setVisible(true);
		add();
	}

	private void add() {
		JButton bsave = new JButton("Save");
		final JComboBox cbsizes = new JComboBox(sizes);
		cbsizes.setSelectedIndex(MainMenu.sizeIndex);
		bsave.setBounds(frame.getWidth() - 135, frame.getHeight() - 65, 120, 30);
		cbsizes.setBounds(5, 5, 120, 30);
		bsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.sizeIndex = cbsizes.getSelectedIndex();
				new MainMenu();
				frame.dispose();
			}
		});
		window.add(bsave);
		window.add(cbsizes);
	}
}
