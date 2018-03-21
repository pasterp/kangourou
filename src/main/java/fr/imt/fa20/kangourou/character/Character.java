package fr.imt.fa20.kangourou.character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import fr.imt.fa20.kangourou.character.state.HorizontalState;
import fr.imt.fa20.kangourou.character.state.VerticalState;
import fr.imt.fa20.kangourou.map.Map;

public abstract class Character {
	public static final float GRAVITY = 0.001f;

	private float x = 90, y = 70;
	float velocityY; // Velocity of the character

	private HorizontalState horizontalState = HorizontalState.STANDING_BY;
	private VerticalState verticalState = VerticalState.NONE;
	private Direction direction = Direction.RIGHT;
	private Map map;

	protected Rectangle hitbox;

	public Character(Map map) {
		this.map = map;

		this.hitbox = new Rectangle(this.getX() - 8, this.getY() - 20, 12, 17);

	}

	public abstract void init() throws SlickException;

	public abstract void update(int delta);

	protected abstract float getFuturX(int delta);

	protected abstract float getFuturY(int delta);

	protected abstract Animation getAnimation();

	public void render(Graphics g) {
		// coordonnées d'affichage correspondant aux pieds du personnage
		g.drawAnimation(this.getAnimation(), (int) x - 16, (int) y - 32);
		
//		g.draw(hitbox);
		
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

	public HorizontalState getHorizontalState() {
		return horizontalState;
	}

	public void setHorizontalState(HorizontalState horizontalState) {
		this.horizontalState = horizontalState;
	}

	public VerticalState getVerticalState() {
		return verticalState;
	}

	public void setVerticalState(VerticalState verticalState) {
		this.verticalState = verticalState;
	}

	public float getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}

}
