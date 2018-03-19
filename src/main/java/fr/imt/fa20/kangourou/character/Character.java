package fr.imt.fa20.kangourou.character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import fr.imt.fa20.kangourou.character.AnimationsLoader.AnimationsLoader;
import fr.imt.fa20.kangourou.map.Map;

public abstract class Character {
	private float x = 50, y = 50;
	private Direction direction = Direction.RIGHT;
	private Map map;

	public Character(Map map) {
		this.map = map;
	}

	public abstract void init() throws SlickException;

	public abstract void update(int delta);

	protected abstract float getFuturX(int delta);

	protected abstract float getFuturY(int delta);

	protected abstract Animation getAnimation();

	public void render(Graphics g) {
		// coordonnées d'affichage correspondant aux pieds du personnage
		g.drawAnimation(this.getAnimation(), (int) x - 16, (int) y - 32);
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

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

}
