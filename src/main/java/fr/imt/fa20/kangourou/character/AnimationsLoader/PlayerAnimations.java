package fr.imt.fa20.kangourou.character.AnimationsLoader;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

import fr.imt.fa20.kangourou.character.Direction;

public class PlayerAnimations extends AnimationsLoader {

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
		super(spriteSheet, yIndex);
	}

	public void loadAnimations() {
		this.standgBy_right = this.loadAnimation(0);
		this.standBy_left = this.loadHorizontalFlipAnimation(0);
		this.walk_right = this.loadAnimation(0, 1, 2, 3);
		this.walk_left = this.loadHorizontalFlipAnimation(0, 1, 2, 3);
		this.jumpPreparation_right = this.loadAnimation(4);
		this.jumpPreparation_left = this.loadHorizontalFlipAnimation(4);
		this.jump_right = this.loadAnimation(5);
		this.jump_left = this.loadHorizontalFlipAnimation(5);
		this.fall_right = this.loadAnimation(6);
		this.fall_left = this.loadHorizontalFlipAnimation(6);
		this.landing_right = this.loadAnimation(7);
		this.landing_left = this.loadHorizontalFlipAnimation(7);
		this.suffer_right = this.loadAnimation(8, 9, 8);
		this.suffer_left = this.loadHorizontalFlipAnimation(8, 9, 8);
		this.slash_right = this.loadAnimation(11, 10, 11, 12);
		this.slash_left = this.loadHorizontalFlipAnimation(11, 10, 11, 12);
		this.punch_right = this.loadAnimation(11, 13, 12);
		this.punch_left = this.loadHorizontalFlipAnimation(11, 13, 12);
		this.run_right = this.loadAnimation(14, 15, 16, 17);
		this.run_left = this.loadHorizontalFlipAnimation(14, 15, 16, 17);
		this.climb = this.loadAnimation(18, 19, 20, 21);
		this.back = this.loadAnimation(22);
	}

	public Animation getStandgBy(Direction direction) {
		if (direction == Direction.RIGHT)
			return this.standgBy_right;
		else
			return this.standBy_left;
	}

	public Animation getWalk(Direction direction) {
		if (direction == Direction.RIGHT)
			return this.walk_right;
		else
			return this.walk_left;
	}

	public Animation getJumpPreparation(Direction direction) {
		if (direction == Direction.RIGHT)
			return this.jumpPreparation_right;
		else
			return this.jumpPreparation_left;
	}

	public Animation getJump(Direction direction) {
		if (direction == Direction.RIGHT)
			return this.jump_right;
		else
			return this.jump_left;

	}

	public Animation getFall(Direction direction) {
		if (direction == Direction.RIGHT)
			return this.fall_right;
		else
			return this.fall_left;

	}

	public Animation getLanding(Direction direction) {
		if (direction == Direction.RIGHT)
			return this.landing_right;
		else
			return this.landing_left;
	}

	public Animation getSuffer(Direction direction) {
		if (direction == Direction.RIGHT)
			return this.suffer_right;
		else
			return this.suffer_left;
	}

	public Animation getSlash(Direction direction) {
		if (direction == Direction.RIGHT)
			return this.slash_right;
		else
			return this.slash_left;

	}

	public Animation getPunch(Direction direction) {
		if (direction == Direction.RIGHT)
			return this.punch_right;
		else
			return this.punch_left;
	}

	public Animation getRun(Direction direction) {
		if (direction == Direction.RIGHT)
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
