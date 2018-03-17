package fr.imt.fa20.kangourou.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import fr.imt.fa20.kangourou.map.Map;

public class Player {

	private static final int SPRITE_Y_INDEX = 0;
	private float x = 50, y = 50;
	private PlayerDirection direction = PlayerDirection.RIGHT;
	private PlayerState state = PlayerState.STANDING_BY;
	private PlayerAnimations animations;

	private Map map;

	public Player(Map map) {
		this.map = map;
	}

	public void init() throws SlickException {
		SpriteSheet spriteSheet = new SpriteSheet("sprites/characters.png", 32, 32);
		this.animations = new PlayerAnimations(spriteSheet, SPRITE_Y_INDEX);
	}

	public void render(Graphics g) {
		g.drawAnimation(this.getAnimation(), (int) x - 16, (int) y - 32);
	}

	public void update(int delta) {
		if (this.state == PlayerState.STANDING_BY)
			return;

		float futurX = getFuturX(delta);
		float futurY = getFuturY(delta);
		
		boolean collision = this.map.isCollision(futurX, futurY);
		if (collision) {
			//this.moving = false;
		} else {
			this.x = futurX;
			this.y = futurY;
		}

	}

	private float getFuturX(int delta) {
		float futurX = x;
		switch (this.direction) {
		case RIGHT:
			futurX = this.x + .1f * delta;
			break;
		case LEFT:
			futurX = this.x - .1f * delta;
			break;
		}

		return futurX;
	}

	private float getFuturY(int delta) {
		// TODO
		float futurY = y;
		return futurY;

	}

	private Animation getAnimation() {
		Animation animation = new Animation();
		switch (this.state) {
		case PREPARING_JUMP:
			animation = this.animations.getJumpPreparation(this.direction);
			break;
		case JUMPING:
			animation = this.animations.getJump(this.direction);
			break;
		case FALLING:
			animation = this.animations.getFall(this.direction);
			break;
		case LANDING:
			animation = this.animations.getLanding(this.direction);
			break;
		case STANDING_BY:
			animation = this.animations.getStandgBy(this.direction);
			break;
		case SUFFERING:
			animation = this.animations.getSuffer(this.direction);
			break;
		case SLASHING:
			animation = this.animations.getSlash(this.direction);
			break;
		case PUNCHING:
			animation = this.animations.getPunch(this.direction);
			break;
		case RUNNING:
			animation = this.animations.getRun(this.direction);
			break;
		case CLIMBING:
			animation = this.animations.getClimb();
			break;
		case FROM_BEHIND:
			animation = this.animations.getBack();
			break;
		}
		return animation;
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

	public PlayerDirection getDirection() {
		return direction;
	}

	public void setDirection(PlayerDirection direction) {
		this.direction = direction;
	}

	public PlayerState getState() {
		return state;
	}

	public void setState(PlayerState state) {
		this.state = state;
	}

}
