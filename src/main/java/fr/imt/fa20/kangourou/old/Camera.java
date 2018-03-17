package fr.imt.fa20.kangourou.old;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Camera {
	private Character character;
	private float xCamera, yCamera;

	public Camera(Character character) {
		this.character = character;
		this.xCamera = this.character.getX();
		this.yCamera = this.character.getY();
	}

	public void place(GameContainer container, Graphics g) {
		g.translate(container.getWidth() / 2 - (int) this.xCamera, container.getHeight() / 2 - (int) this.yCamera);
	}

	public void update(GameContainer container) {
		int w = container.getWidth() / 4;
		if (this.character.getX() > this.xCamera + w) {
			this.xCamera = this.character.getX() - w;
		} else if (this.character.getX() < this.xCamera - w) {
			this.xCamera = this.character.getX() + w;
		}
		int h = container.getHeight() / 4;
		if (this.character.getY() > this.yCamera + h) {
			this.yCamera = this.character.getY() - h;
		} else if (this.character.getY() < this.yCamera - h) {
			this.yCamera = this.character.getY() + h;
		}
	}
}
