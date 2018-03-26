package fr.imt.fa20.kangourou.character.player;

import fr.imt.fa20.kangourou.character.Character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;

import fr.imt.fa20.kangourou.character.AnimationsLoader.PlayerAnimations;
import fr.imt.fa20.kangourou.character.state.HorizontalState;
import fr.imt.fa20.kangourou.character.state.VerticalState;
import fr.imt.fa20.kangourou.map.Map;

public class Player extends Character {

	private static final int SPRITE_Y_INDEX = 0;
	private static final float JUMP_VELOCITY = -3.5f;
	private PlayerAnimations animations;

	public Player(Map map) {
		super(map);
	}

	public void init() throws SlickException {
		SpriteSheet spriteSheet = new SpriteSheet("sprites/characters.png", 32, 32);
		this.animations = new PlayerAnimations(spriteSheet, SPRITE_Y_INDEX);
	}

	public void update(int delta) {
		//delta in ms

		float futurX = getFuturX(delta);
		float futurY = getFuturY(delta);

		float vX = futurX - x;
		float vY = futurY - y;

		this.setX(futurX);
		this.hitbox.setX(futurX - 7);
		if (isCollision()) {
			this.setX(getX() - vX);
			this.hitbox.setX(hitbox.getX() - vX);
		}

		this.setY(futurY);
		this.hitbox.setY(futurY - 21);
		if (isCollision()) { // Collision
			if(vY>0){
				rollbackFromCollide(getCollider(), vY>0);
			}else{
				y = y - vY;
				hitbox.setY(hitbox.getMinY() - vY);
			}

			handleJumpState(true);
		} else {// No collision
			handleJumpState(false);
		}
	}

	private void rollbackFromCollide(Shape collider, boolean down){
		//casting to int bug the thing if the collider is higher
		do{
			y = (int)y + ((down) ? -0.01f : 0.01f);
			hitbox.setY((int)hitbox.getMinY() + ((down) ? -0.01f : 0.01f));
		}while(hitbox.intersects(collider));
	}

	private void handleJumpState(boolean collide){
		switch(getVerticalState()){
		case PREPARING_JUMP:
			velocityY = JUMP_VELOCITY;
			this.setVerticalState(VerticalState.JUMPING);
			break;
		case JUMPING:
			velocityY += GRAVITY;
			if(collide ||velocityY >= 0.0f){
				setVerticalState(VerticalState.FALLING);
			}
		break;
		case FALLING:
			velocityY += GRAVITY;	
			if(collide){
				setVerticalState(VerticalState.LANDING);
			}
		break;
		case LANDING:
			velocityY = GRAVITY;
			setVerticalState(VerticalState.NONE);
		break;
		case NONE:
			velocityY = GRAVITY;
			if(!collide){
				setVerticalState(VerticalState.FALLING);
			}
		break;
		}
	}
  
  	private boolean isCollision() {
		return getCollider() != null;
	}
	private Shape getCollider(){
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
