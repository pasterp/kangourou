package fr.imt.fa20.kangourou.character.player;

import fr.imt.fa20.kangourou.character.Character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import fr.imt.fa20.kangourou.character.AnimationsLoader.PlayerAnimations;
import fr.imt.fa20.kangourou.character.state.HorizontalState;
import fr.imt.fa20.kangourou.character.state.VerticalState;
import fr.imt.fa20.kangourou.map.Map;

public class Player extends Character {

	private static final int SPRITE_Y_INDEX = 1;
	private static final int SPRITE_WIDTH = 32;
	private static final int SPRITE_HEIGHT = 32;

	private PlayerAnimations animations;

	public Player(Map map) {
		super(map);
	}

	public void init() throws SlickException {
		SpriteSheet spriteSheet = new SpriteSheet("sprites/characters.png", SPRITE_WIDTH, SPRITE_HEIGHT);
		this.animations = new PlayerAnimations(spriteSheet, SPRITE_Y_INDEX);
	}

	public void update(int delta) {

		float futurX = getFuturX(delta);
		float futurY = getFuturY(delta);

		float vX = futurX - getX();
		float vY = futurY - getY();

		this.setX(futurX);
		this.hitbox.setX(futurX - 7);
		if (isCollision()) {
			this.setX(getX() - vX);
			this.hitbox.setX(hitbox.getX() - vX);
		}

		this.setY(futurY);
		this.hitbox.setY(futurY - 21);
		if (isCollision()) { // Collision
			this.setY(getY() - vY);
			this.hitbox.setY(hitbox.getY() - vY);
			this.handelingJumpWhenCollision();
		} else {// No collision
			this.handelingJumpWhenNoCollision();
		}
	}

	private void handelingJumpWhenCollision() {
		switch (this.getVerticalState()) {
		case PREPARING_JUMP:
			this.setVelocityY(BOOST_VELOCITY_Y);
			break;
		case JUMPING:
			this.setVerticalState(VerticalState.FALLING);
			this.setVelocityY(GRAVITY);
			break;
		case FALLING:
			this.setVerticalState(VerticalState.LANDING);
			this.setVelocityY(0);
			break;
		case LANDING:
			this.setVerticalState(VerticalState.NONE);
			break;
		case NONE:
			break;
		}
	}

	private void handelingJumpWhenNoCollision() {
		switch (this.getVerticalState()) {
		case PREPARING_JUMP:
			this.setVerticalState(VerticalState.JUMPING);
			break;
		case JUMPING:
		case FALLING:
			this.setVelocityY(this.getVelocityY() + GRAVITY);
			this.setVerticalState(this.getVelocityY() < 0 ? VerticalState.JUMPING : VerticalState.FALLING);
			break;
		case LANDING:
			this.setVerticalState(VerticalState.NONE);
			this.setVelocityY(0);
			break;
		case NONE:
			if (this.getVelocityY() == 0) {
				this.setVelocityY(GRAVITY);
			} else {
				this.setVerticalState(VerticalState.FALLING);
			}
			break;
		}
	}

	private boolean isCollision() {
		return this.getMap().isCollision(this.hitbox);
	}

	protected float getFuturX(int delta) {
		float futurX = this.getX();
		if (this.getHorizontalState() == HorizontalState.RUNNING) {
			switch (this.getDirection()) {
			case RIGHT:
				futurX = this.getX() + .15f * delta;
				break;
			case LEFT:
				futurX = this.getX() - .15f * delta;
				break;
			}
		}
		return futurX;
	}

	protected float getFuturY(int delta) {
		return this.getY() + this.getVelocityY();

	}

	protected Animation getAnimation() {
		Animation animation = new Animation();
		if (this.getVerticalState() != VerticalState.NONE) {
			switch (this.getVerticalState()) {
			case PREPARING_JUMP:
				animation = this.animations.getJumpPreparation(this.getDirection());
				break;
			case JUMPING:
				animation = this.animations.getJump(this.getDirection());
				break;
			case FALLING:
				animation = this.animations.getFall(this.getDirection());
				break;
			case LANDING:
				animation = this.animations.getLanding(this.getDirection());
				break;
			default:
				break;
			}
		} else {
			switch (this.getHorizontalState()) {
			case STANDING_BY:
				animation = this.animations.getStandgBy(this.getDirection());
				break;
			case RUNNING:
				animation = this.animations.getRun(this.getDirection());
				break;
			case PUNCHING:
				animation = this.animations.getSlash(this.getDirection());
				break;
			}
		}
		return animation;
	}

}
