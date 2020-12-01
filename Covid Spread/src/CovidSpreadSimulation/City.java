package CovidSpreadSimulation;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import java.awt.BasicStroke;

public class City {

	private final int popsize = 200; // population size
	private int width, height; // city

	private Person[] population;

	private boolean isquarantine = false;
	private int[] xWalls; // boundaries
	private int[] yWalls;

	/**
	 * Constructor
	 * 
	 * @param w - width of the city
	 * @param h - height of the city
	 */
	public City(int w, int h) {
		setSize(w, h);
		setWalls(w, h);

		population = new Person[popsize];

		for (int i = 0; i < population.length; i++)
			population[i] = new Person(w, h);

		population[0].setSick(); // patient zero
	}

	/**
	 * size of the city
	 * 
	 * @param w - width of the city
	 * @param h - height of the city
	 */
	public void setSize(int w, int h) {
		width = w;
		height = h;
	}

	/**
	 * set the demensions
	 * 
	 * @param w - width of the city
	 * @param h - height of the city
	 */
	private void setWalls(int w, int h) {

		if (isquarantine) {
			xWalls = new int[6];
			xWalls[0] = 0;
			xWalls[1] = w / 5;
			xWalls[2] = w / 2;
			xWalls[3] = w;

			yWalls = new int[6];
			yWalls[0] = 0;
			yWalls[1] = h / 5;
			yWalls[2] = h / 2;
			yWalls[3] = h;

		} else {
			xWalls = new int[2];
			xWalls[0] = 0;
			xWalls[1] = w;

			yWalls = new int[2];
			yWalls[0] = 0;
			yWalls[1] = h;
		}

	}

	/**
	 * Draw the city
	 * 
	 * @param g - tool to draw with
	 */
	public void draw(Graphics g) {
		drawWalls(g);

		// draw all the people
		for (int i = 0; i < population.length; i++)
			population[i].draw(g);

	}

	/**
	 * Draw all the walls
	 * 
	 * @param g - tool used to draw with
	 */
	private void drawWalls(Graphics g) {
		// Dotted lines
		g.setColor(Color.black);
		Graphics2D g2d = (Graphics2D) g; // a more complex Graphics class used to draw Objects
		// How to make a dotted line:
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 10 }, 0);
		g2d.setStroke(dashed);

		// draw lines
		for (int i = 0; i < xWalls.length; i++)
			g.drawLine(xWalls[i], 0, xWalls[i], height);

		for (int i = 0; i < yWalls.length; i++)
			g.drawLine(0, yWalls[i], width, yWalls[i]);
	}

	/**
	 * update every single person (movement and collisions)
	 */
	public void update() {
		for (int i = 0; i < population.length; i++) {
			population[i].update(xWalls, yWalls, width, height);

			for (int j = 0; j < population.length; j++)
				if (i != j) {
					Person p1 = population[i];
					Person p2 = population[j];

					boolean collided = p1.collided(p2);

					if (collided) {
						float xv1 = p1.getxVel();
						float yv1 = p1.getyVel();

						float xv2 = p2.getxVel();
						float yv2 = p2.getyVel();

						p1.setxVel(xv2);
						p1.setyVel(yv2);

						p2.setxVel(xv1);
						p2.setyVel(yv1);

					}

				}

		}
	}

}