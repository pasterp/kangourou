package fr.imt.fa20.kangourou.character.player;

import fr.imt.fa20.kangourou.character.Character;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import fr.imt.fa20.kangourou.character.Direction;
import fr.imt.fa20.kangourou.character.AnimationsLoader.AnimationsLoader;
import fr.imt.fa20.kangourou.character.AnimationsLoader.PlayerAnimations;
import fr.imt.fa20.kangourou.map.Map;

public class Player extends Character {

	private static final int SPRITE_Y_INDEX = 0;
	private PlayerState state = PlayerState.STANDING_BY;
	private PlayerAnimations animations;

	public Player(Map map) {
		super(map);
	}

	public void init() throws SlickException {
		SpriteSheet spriteSheet = new SpriteSheet("sprites/characters.png", 32, 32);
		this.animations = new PlayerAnimations(spriteSheet, SPRITE_Y_INDEX);
	}

	public void update(int delta) {
		if (this.state == PlayerState.STANDING_BY)
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
			futurX = this.getX() - .1f * delta;
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
		case STANDING_BY:
			animation = this.animations.getStandgBy(this.getDirection());
			break;
		case SUFFERING:
			animation = this.animations.getSuffer(this.getDirection());
			break;
		case SLASHING:
			animation = this.animations.getSlash(this.getDirection());
			break;
		case PUNCHING:
			animation = this.animations.getPunch(this.getDirection());
			break;
		case RUNNING:
			animation = this.animations.getRun(this.getDirection());
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

	public PlayerState getState() {
		return state;
	}

	public void setState(PlayerState state) {
		this.state = state;
	}

}
