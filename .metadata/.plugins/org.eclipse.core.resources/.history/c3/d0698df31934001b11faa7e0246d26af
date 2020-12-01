package CovidSpreadSimulation;



import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Simulation extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public final static int WIDTH = 1500;
	public final static int HEIGHT = 1500;

	public boolean running = false;
	private Thread thread;

	City city;

	/**
	 * Constructor
	 */
	public Simulation() {
		canvasSetup();
		initialize();
		newWindow();

	}

	/**
	 * Setup JFrame where the canvas will be in
	 */
	private void newWindow() {
		JFrame frame = new JFrame("Covid Spread Simulator");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		start();
	}

	/**
	 * initialize all our game objects
	 */
	private void initialize() {

		city = new City(getWidth(), getHeight());
	}

	/**
	 * just to setup the canvas to our desired settings and sizes, setup events
	 */
	private void canvasSetup() {
		this.setSize(new Dimension(WIDTH, HEIGHT));

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();

				if (code == KeyEvent.VK_R)
					initialize();

			}

		});

		// refresh size of city when this canvas changes size
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
//				city.setSize(getWidth(), getHeight());
				initialize();
			}
		});

		this.setFocusable(true);
	}

	@Override
	public void run() {
		// Full video explaining this game loop on my YouTube channel Coding Heaven

		this.requestFocus();
		// game timer

		final double MAX_FRAMES_PER_SECOND = 60.0;
		final double MAX_UPDATES_PER_SECOND = 60.0;

		long startTime = System.nanoTime();
		final double uOptimalTime = 1000000000 / MAX_UPDATES_PER_SECOND;
		final double fOptimalTime = 1000000000 / MAX_FRAMES_PER_SECOND;
		double uDeltaTime = 0, fDeltaTime = 0;
		int frames = 0, updates = 0;
		long timer = System.currentTimeMillis();

		while (running) {

			long currentTime = System.nanoTime();
			uDeltaTime += (currentTime - startTime) / uOptimalTime;
			fDeltaTime += (currentTime - startTime) / fOptimalTime;
			startTime = currentTime;

			while (uDeltaTime >= 1) {
				update();
				updates++;
				uDeltaTime--;
			}

			if (fDeltaTime >= 1) {
				render();
				frames++;
				fDeltaTime--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
			//	System.out.println("UPS: " + updates + ", FPS: " + frames);
				frames = 0;
				updates = 0;
				timer += 1000;
			}
		}

		stop();
	}

	/**
	 * start the thread and the game
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	/**
	 * Stop the thread and the game
	 */
	public void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * draw the background and all the objects
	 */
	public void render() {

		BufferStrategy buffer = this.getBufferStrategy();
		if (buffer == null) {
			this.createBufferStrategy(3);

			return;
		}

		Graphics g = buffer.getDrawGraphics(); // extract drawing tool from the buffers

		// draw background
		drawBackground(g);

		city.draw(g);

		g.dispose();
		buffer.show();

	}

	/**
	 * draw the background
	 * 
	 * @param g - tool to draw
	 */
	private void drawBackground(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * update settings and move all objects
	 */
	public void update() {

		city.update();

	}

	/**
	 * 
	 * Main function, creates the canvas
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Simulation();
	}

}
