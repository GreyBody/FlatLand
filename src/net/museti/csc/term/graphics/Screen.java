package net.museti.csc.term.graphics;

public class Screen {

	public int[] pixels;
	public int width;
	public int height;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderSquare(int color, int xc, int yc) {
		for (int y = 0; y < 16; y++) {
			int ya = y + yc;
			for (int x = 0; x < 16; x++) {
				int xa = x + xc;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				pixels[xa + ya * width] = color;
			}
		}
	}
	
	public void renderBack() {
		for(int i = 0; i < pixels.length; i++) {
				pixels[i] = 0xFFFF000F;
		}
	}
}
