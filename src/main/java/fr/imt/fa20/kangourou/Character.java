package fr.imt.fa20.kangourou;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public abstract class Character {

	private float x = 100, y = 100;
	private Direction direction;
	private boolean moving = false;
	private boolean jumping;
	private Map map;

	public Character(Map map) {
		this.direction = Direction.RIGHT;
		this.map = map;
	}

	public abstract void init() throws SlickException;

	public abstract void render(Graphics g) throws SlickException;

	public void update(int delta) {
		if (this.moving) {
			float futurX = getFuturX(delta);
			float futurY = getFuturY(delta);
			boolean collision = this.map.isCollision(futurX, futurY);
			if (collision) {
				this.moving = false;
			} else {
				this.x = futurX;
				this.y = futurY;
			}
		}
	}

	private float getFuturX(int delta) {
		float futurX = this.x;
		switch (this.direction) {
		case LEFT:
			futurX = this.x - .1f * delta;
			break;
		case RIGHT:
			futurX = this.x + .1f * delta;
			break;
		}
		return futurX;
	}

	private float getFuturY(int delta) {
		// TODO
		return 0;
	}

	public void teleport(int id) {
		this.x = Float.parseFloat(this.map.getObjectProperty(id, "dest-x", Float.toString(this.x)));
		this.y = Float.parseFloat(this.map.getObjectProperty(id, "dest-y", Float.toString(this.y)));
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

}
