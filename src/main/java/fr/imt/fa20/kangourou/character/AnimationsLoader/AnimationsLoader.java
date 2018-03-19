package fr.imt.fa20.kangourou.character.AnimationsLoader;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public abstract class AnimationsLoader {
	// Time between sprites
	private static final int TIME_MS = 100;

	private SpriteSheet spriteSheet;

	// y index of player's sprites on the sheet. See sprites/characters.png
	private int spriteYIndex;

	public AnimationsLoader(SpriteSheet spriteSheet, int yIndex) {
		this.spriteSheet = spriteSheet;
		this.spriteYIndex = yIndex;
		this.loadAnimations();
	}

	protected abstract void loadAnimations();

	protected Animation loadAnimation(int... spriteXIndex) {
		Animation animation = new Animation();
		for (int x : spriteXIndex) {
			animation.addFrame(spriteSheet.getSprite(x, spriteYIndex), TIME_MS);
		}
		return animation;
	}

	protected Animation loadHorizontalFlipAnimation(int... spriteXIndex) {
		Animation animation = new Animation();
		for (int x : spriteXIndex) {
			animation.addFrame(spriteSheet.getSprite(x, spriteYIndex).getFlippedCopy(true, false), TIME_MS);
		}
		return animation;
	}
}
