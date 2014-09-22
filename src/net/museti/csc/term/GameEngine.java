package net.museti.csc.term;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import net.museti.csc.term.graphics.Screen;
import net.museti.csc.term.world.Level;

public class GameEngine extends Canvas implements Runnable {

	public static int width;
	public static int height;

	private Thread thread;
	public JFrame frame;
	private Screen screen;
	private Level level;
	private boolean running = false;

	private BufferedImage image;
	private int[] pixels;

	public GameEngine(int dwidth, int dheight) {
		width = dwidth;
		height = dheight;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		screen = new Screen(width, height);

		frame = new JFrame();
		level = level.world;
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println(""+frames);
				updates = 0;
				frames = 0;
			}
		}
	}

	int x = 0, y = 0;

	public void update() {
		level.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();

		// Render World Here
		screen.renderBack();
		screen.renderSquare(0xFF00FFFF, 400, 200);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		// Draw canvas graphics here
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		GameEngine game = new GameEngine(300, 180);
		game.frame.setResizable(false);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);

		game.frame.setVisible(true);
		game.start();
	}
}
