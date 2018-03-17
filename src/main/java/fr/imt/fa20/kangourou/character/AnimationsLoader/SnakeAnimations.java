package fr.imt.fa20.kangourou.character.AnimationsLoader;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

import fr.imt.fa20.kangourou.character.Direction;

public class SnakeAnimations extends AnimationsLoader {

	private Animation standgBy_right;
	private Animation standBy_left;
	private Animation walk_right;
	private Animation walk_left;
	private Animation suffering_right;
	private Animation suffering_left;

	public SnakeAnimations(SpriteSheet spriteSheet, int yIndex) {
		super(spriteSheet, yIndex);
	}

	protected void loadAnimations() {
		this.standgBy_right = loadAnimation(0);
		this.standBy_left = loadHorizontalFlipAnimation(0);
		this.walk_right = loadAnimation(0, 1, 2, 3);
		this.walk_left = loadHorizontalFlipAnimation(0, 1, 2, 3);
		this.suffering_right = loadAnimation(0, 4, 0, 4, 0, 4);
		this.suffering_left = loadHorizontalFlipAnimation(0, 4, 0, 4, 0, 4);
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

	public Animation getSuffer(Direction direction) {
		if (direction == Direction.RIGHT)
			return this.suffering_right;
		else
			return this.suffering_left;
	}
}
