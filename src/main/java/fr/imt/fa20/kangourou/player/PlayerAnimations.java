package fr.imt.fa20.kangourou.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class PlayerAnimations {
	// Time between sprites
	private static final int TIME_MS = 100;

	private SpriteSheet spriteSheet;

	// y index of player's sprites on the sheet. See sprites/characters.png
	private int spriteYIndex;

	private Animation standgBy_right;
	private Animation standBy_left;
	private Animation walk_right;
	private Animation walk_left;
	private Animation jumpPreparation_right;
	private Animation jumpPreparation_left;
	private Animation jump_right;
	private Animation jump_left;
	private Animation fall_right;
	private Animation fall_left;
	private Animation landing_right;
	private Animation landing_left;
	private Animation suffer_right;
	private Animation suffer_left;
	private Animation slash_right;
	private Animation slash_left;
	private Animation punch_right;
	private Animation punch_left;
	private Animation run_right;
	private Animation run_left;
	private Animation climb;
	private Animation back;

	public PlayerAnimations(SpriteSheet spriteSheet, int yIndex) {
		this.spriteSheet = spriteSheet;
		this.spriteYIndex = yIndex;
		this.loadAnimations();
	}

	private void loadAnimations() {
		this.standgBy_right = loadAnimation(0);
		this.standBy_left = loadHorizontalFlipAnimation(0);
		this.walk_right = loadAnimation(0, 1, 2, 3);
		this.walk_left = loadHorizontalFlipAnimation(0, 1, 2, 3);
		this.jumpPreparation_right = loadAnimation(4);
		this.jumpPreparation_left = loadHorizontalFlipAnimation(4);
		this.jump_right = loadAnimation(5);
		this.jump_left = loadHorizontalFlipAnimation(5);
		this.fall_right = loadAnimation(6);
		this.fall_left = loadHorizontalFlipAnimation(6);
		this.landing_right = loadAnimation(7);
		this.landing_left = loadHorizontalFlipAnimation(7);
		this.suffer_right = loadAnimation(8, 9, 8);
		this.suffer_left = loadHorizontalFlipAnimation(8, 9, 8);
		this.slash_right = loadAnimation(11, 10, 11, 12);
		this.slash_left = loadHorizontalFlipAnimation(11, 10, 11, 12);
		this.punch_right = loadAnimation(11, 13, 12);
		this.punch_left = loadHorizontalFlipAnimation(11, 13, 12);
		this.run_right = loadAnimation(14, 15, 16, 17);
		this.run_left = loadHorizontalFlipAnimation(14, 15, 16, 17);
		this.climb = loadAnimation(18, 19, 20, 21);
		this.back = loadAnimation(22);
	}

	private Animation loadAnimation(int... spriteXIndex) {
		Animation animation = new Animation();
		for (int x : spriteXIndex) {
			animation.addFrame(spriteSheet.getSprite(x, spriteYIndex), TIME_MS);
		}
		return animation;
	}

	private Animation loadHorizontalFlipAnimation(int... spriteXIndex) {
		Animation animation = new Animation();
		for (int x : spriteXIndex) {
			animation.addFrame(spriteSheet.getSprite(x, spriteYIndex).getFlippedCopy(true, false), TIME_MS);
		}
		return animation;
	}

	public Animation getStandgBy(PlayerDirection direction) {
		if (direction == PlayerDirection.RIGHT)
			return this.standgBy_right;
		else
			return this.standBy_left;
	}

	public Animation getWalk(PlayerDirection direction) {
		if (direction == PlayerDirection.RIGHT)
			return this.walk_right;
		else
			return this.walk_left;
	}

	public Animation getJumpPreparation(PlayerDirection direction) {
		if (direction == PlayerDirection.RIGHT)
			return this.jumpPreparation_right;
		else
			return this.jumpPreparation_left;
	}

	public Animation getJump(PlayerDirection direction) {
		if (direction == PlayerDirection.RIGHT)
			return this.jump_right;
		else
			return this.jump_left;

	}

	public Animation getFall(PlayerDirection direction) {
		if (direction == PlayerDirection.RIGHT)
			return this.fall_right;
		else
			return this.fall_left;

	}

	public Animation getLanding(PlayerDirection direction) {
		if (direction == PlayerDirection.RIGHT)
			return this.landing_right;
		else
			return this.landing_left;
	}

	public Animation getSuffer(PlayerDirection direction) {
		if (direction == PlayerDirection.RIGHT)
			return this.suffer_right;
		else
			return this.suffer_left;
	}

	public Animation getSlash(PlayerDirection direction) {
		if (direction == PlayerDirection.RIGHT)
			return this.slash_right;
		else
			return this.slash_left;

	}

	public Animation getPunch(PlayerDirection direction) {
		if (direction == PlayerDirection.RIGHT)
			return this.punch_right;
		else
			return this.punch_left;
	}

	public Animation getRun(PlayerDirection direction) {
		if (direction == PlayerDirection.RIGHT)
			return this.run_right;
		else
			return this.run_left;
	}

	public Animation getClimb() {
		return this.climb;
	}

	public Animation getBack() {
		return this.back;
	}

}
