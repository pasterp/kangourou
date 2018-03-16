package fr.imt.fa20.kangourou;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Golem extends Character {
	public static final String SPRITE_SHEET_FILE = "sprites/characters.png";
	public static final int SPRITE_SIZE = 32;
	public static final int SPRITE_Y_INDEX = 0;
	public static final int TIME_MS = 100;
	public static final int NB_ANIMATIONS = 22;

	private float x = 100, y = 100;
	private boolean moving = false;
	private boolean onStair = false;
	private Animation[] animations;
	private Map map;
	private SpriteSheet spriteSheet;

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

	public Golem(Map map) throws SlickException {
		super(map);
		this.spriteSheet = new SpriteSheet(SPRITE_SHEET_FILE, SPRITE_SIZE, SPRITE_SIZE);
	}

	public void init() {
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

	@Override
	public void render(Graphics g) throws SlickException {
		if (this.getDirection() == Direction.LEFT) {
			if (this.isMoving()) {
				g.drawAnimation(this.walk_left, x - 32, y - 60);

			} else {
				g.drawAnimation(this.standBy_left, x - 32, y - 60);
			}
		} else {
			if (this.isMoving()) {
				g.drawAnimation(this.walk_right, x - 32, y - 60);

			} else {
				g.drawAnimation(this.standgBy_right, x - 32, y - 60);
			}
		}

	}
}
