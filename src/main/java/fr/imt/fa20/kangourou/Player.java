package fr.imt.fa20.kangourou;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player {

	/** Personage **/
	float x = 300, y = 300;
	int direction = 0;
	boolean moving = false;
	Animation animation = new Animation();
	
	

	public Player() throws SlickException {
		SpriteSheet spriteSheet;
		spriteSheet = new SpriteSheet("sprite/kangaroo.png", 100, 130);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 0), 500);
		animation.addFrame(spriteSheet.getSprite(1, 0), 200);
		this.animation = animation;
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



	public int getDirection() {
		return direction;
	}



	public void setDirection(int direction) {
		this.direction = direction;
	}



	public boolean isMoving() {
		return moving;
	}



	public void setMoving(boolean moving) {
		this.moving = moving;
	}



	public Animation getAnimation() {
		return animation;
	}



	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

}
