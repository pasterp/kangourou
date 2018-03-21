package fr.imt.fa20.kangourou.character.player;

import fr.imt.fa20.kangourou.character.Character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import fr.imt.fa20.kangourou.character.AnimationsLoader.PlayerAnimations;
import fr.imt.fa20.kangourou.character.state.HorizontalState;
import fr.imt.fa20.kangourou.character.state.VerticalState;
import fr.imt.fa20.kangourou.map.Map;

public class Player extends Character {

	private static final int SPRITE_Y_INDEX = 0;
	private PlayerAnimations animations;

	public Player(Map map) {
		super(map);
	}

	public void init() throws SlickException {
		SpriteSheet spriteSheet = new SpriteSheet("sprites/characters.png", 32, 32);
		this.animations = new PlayerAnimations(spriteSheet, SPRITE_Y_INDEX);
	}

	public void update(int delta) {

		float futurX = getFuturX(delta);
		float futurY = getFuturY(delta);

		float vX = futurX - getX();
		float vY = futurY - getY();

		this.setX(futurX);
		this.hitbox.setX(futurX - 8);
		if (isCollision()) {
			this.setX(getX() - vX);
			this.hitbox.setX(hitbox.getX() - vX);
		}

		this.setY(futurY);
		this.hitbox.setY(futurY - 20);
		if (isCollision()) { // Collision
			this.setY(getY() - vY);
			this.hitbox.setY(hitbox.getY() - vY);
			// if (this.getVelocityY() < 0) { // Head collision
			// this.setVerticalState(VerticalState.FALLING);
			// this.setVelocityY(GRAVITY);
			// } else if (this.getVelocityY() > 0 && this.getVerticalState() ==
			// VerticalState.FALLING) { // Feet collision
			// this.setVerticalState(VerticalState.LANDING);
			// this.setVelocityY(0);
			// }

			switch (this.getVerticalState()) {
			case PREPARING_JUMP:
				this.setVelocityY(-0.4f);
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
		} else {// No collision
			if (this.getVerticalState().equals(VerticalState.PREPARING_JUMP)) {
				this.setVerticalState(VerticalState.JUMPING);
			} else if ((this.getVerticalState() == VerticalState.JUMPING
					|| this.getVerticalState() == VerticalState.FALLING)) {
				this.setVelocityY(this.getVelocityY() + GRAVITY);
			} else if (this.getVerticalState() == VerticalState.NONE && this.getVelocityY() != 0) {
				this.setVerticalState(VerticalState.FALLING);

			} else if (this.getVerticalState() == VerticalState.NONE && this.getVelocityY() == 0) {
				this.setVelocityY(GRAVITY);
			} else if (this.getVerticalState() == VerticalState.LANDING) {
				this.setVerticalState(VerticalState.NONE);
				this.setVelocityY(0);

			}
			// switch (this.getVerticalState()) {
			// case JUMPING:
			// this.setVelocityY(this.getVelocityY() + GRAVITY);
			//
			// break;
			// case FALLING:
			// this.setVelocityY(this.getVelocityY() + GRAVITY);
			// break;
			// case LANDING:
			// this.setVerticalState(VerticalState.NONE);
			// break;
			// case NONE:
			// this.setVelocityY(GRAVITY);
			// break;
			// }
		}
		System.out.println("Vertical=" + this.getVerticalState() + "/horizontal=" + this.getHorizontalState()
				+ "/VelocityY=" + this.getVelocityY());
	}

	private boolean isCollision(float x, float y) {
		return false || this.getMap().isCollision(x, y);
	}

	private boolean isCollision() {
		boolean collision = false;
		float[] pts = hitbox.getPoints();
		for (int i = 0; i < pts.length; i += 2) {
			float x = pts[i];
			float y = pts[i + 1];
			collision = collision || this.getMap().isCollision(x, y);
		}
		return collision;
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
			}
		} else {
			switch (this.getHorizontalState()) {
			// case PREPARING_JUMP:
			// animation = this.animations.getJumpPreparation(this.getDirection());
			// break;

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
