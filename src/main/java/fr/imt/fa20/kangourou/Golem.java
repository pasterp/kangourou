package fr.imt.fa20.kangourou;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Golem {
	public static final String SPRITE_SHEET_FILE = "sprite/characters.png";
	public static final int SPRITE_SIZE = 32;
	public static final int SPRITE_Y_INDEX = 0;
	public static final int TIME_MS = 100;
	public static final int NB_ANIMATIONS = 22;

	private float x = 100, y = 100;
	private int direction = 0;
	private boolean moving = false;
	private boolean onStair = false;
	private Animation[] animations;
	private Map map;
	private SpriteSheet spriteSheet;

	public Golem(Map map) throws SlickException {
		this.map = map;
		this.spriteSheet = new SpriteSheet(SPRITE_SHEET_FILE, SPRITE_SIZE, SPRITE_SIZE);
		this.animations = new Animation[NB_ANIMATIONS];
	}

	public void init() throws SlickException {

		// standing by right
		this.animations[0] = loadAnimation(0);
		// standing by left
		this.animations[1] = loadHorizontalFlipAnimation(0);

		// walking right
		this.animations[2] = loadAnimation(0, 1, 2, 3);
		// walking left
		this.animations[3] = loadHorizontalFlipAnimation(0, 1, 2, 3);

		// Jump preparation + Jump right preparation
		this.animations[4] = loadAnimation(4);
		// Jump left preparation
		this.animations[5] = loadHorizontalFlipAnimation(4);

		// moving upwards + moving upwards right
		this.animations[6] = loadAnimation(5);
		// moving upwards left
		this.animations[7] = loadHorizontalFlipAnimation(5);

		// moving downards + moving downards right
		this.animations[8] = loadAnimation(6);
		// moving downards left
		this.animations[9] = loadHorizontalFlipAnimation(6);

		// landing + landing right
		this.animations[10] = loadAnimation(7);
		// landing left
		this.animations[11] = loadHorizontalFlipAnimation(7);

		// hit right
		this.animations[12] = loadAnimation(8, 9, 8);
		// hit left
		this.animations[13] = loadHorizontalFlipAnimation(8, 9, 8);

		// slash right
		this.animations[14] = loadAnimation(11, 10, 11, 12);
		// slash left
		this.animations[15] = loadHorizontalFlipAnimation(11, 10, 11, 12);

		// punch right
		this.animations[16] = loadAnimation(11, 13, 12);
		// punch left
		this.animations[17] = loadHorizontalFlipAnimation(11, 13, 12);

		// run right
		this.animations[18] = loadAnimation(14, 15, 16, 17);
		// run left
		this.animations[19] = loadHorizontalFlipAnimation(14, 15, 16, 17);

		// climb
		this.animations[20] = loadAnimation(18, 19, 20, 21);

		// character's back
		this.animations[21] = loadAnimation(22);
	}

	private Animation loadAnimation(int... spriteXIndex) {
		Animation animation = new Animation();
		for (int x : spriteXIndex) {
			animation.addFrame(spriteSheet.getSprite(x, SPRITE_Y_INDEX), TIME_MS);
		}
		return animation;
	}

	private Animation loadHorizontalFlipAnimation(int... spriteXIndex) {
		Animation animation = new Animation();
		for (int x : spriteXIndex) {
			animation.addFrame(spriteSheet.getSprite(x, SPRITE_Y_INDEX).getFlippedCopy(true, false), TIME_MS);
		}
		return animation;
	}

	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		return animation;
	}
}
