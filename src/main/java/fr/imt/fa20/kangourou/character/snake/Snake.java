package fr.imt.fa20.kangourou.character.snake;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import fr.imt.fa20.kangourou.character.Character;
import fr.imt.fa20.kangourou.character.Direction;
import fr.imt.fa20.kangourou.character.AnimationsLoader.PlayerAnimations;
import fr.imt.fa20.kangourou.character.AnimationsLoader.SnakeAnimations;
import fr.imt.fa20.kangourou.map.Map;

public class Snake extends Character {
	private static final int SPRITE_Y_INDEX = 3;
	private SnakeState state = SnakeState.WALKING;
	private SnakeAnimations animations;

	public Snake(Map map) {
		super(map);
	}

	public void init() throws SlickException {
		SpriteSheet spriteSheet = new SpriteSheet("sprites/characters.png", 32, 32);
		this.animations = new SnakeAnimations(spriteSheet, SPRITE_Y_INDEX);
	}

	public void update(int delta) {
		if (this.state != SnakeState.WALKING)
			return;

		float futurX = getFuturX(delta);
		float futurY = getFuturY(delta);

		boolean collision = this.getMap().isCollision(futurX, futurY);
		if (collision) {
			// this.moving = false;
		} else {
			this.setX(futurX);
			this.setY(futurY);
		}

	}

	protected float getFuturX(int delta) {
		float futurX = this.getX();
		switch (this.getDirection()) {
		case RIGHT:
			futurX = this.getX() + .1f * delta;
			break;
		case LEFT:
			futurX = this.getY() - .1f * delta;
			break;
		}

		return futurX;
	}

	protected float getFuturY(int delta) {
		// TODO
		float futurY = this.getY();
		return futurY;

	}

	protected Animation getAnimation() {
		Animation animation = new Animation();
		switch (this.state) {
		case WALKING:
			animation = this.animations.getWalk(this.getDirection());
			break;
		case STANDING_BY:
			animation = this.animations.getStandgBy(this.getDirection());
			break;
		case SUFFERING:
			animation = this.animations.getSuffer(this.getDirection());
			break;
		}
		return animation;
	}
}
