package fr.imt.fa20.kangourou;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

/**
 * A game using Slick2d
 */
public class Game extends BasicGame {

	/** Screen width */
	private static final int WIDTH = 800;
	/** Screen height */
	private static final int HEIGHT = 600;

	/** Game... */
	private GameContainer container;
	private TiledMap map;

	/** Personnage **/
	private float x = 300, y = 300;
	private int direction = 0;
	private boolean moving = false;
	private Animation animation = new Animation();

	/** Keys **/
	private boolean isLeftPressed = false, isRightPressed = false;

	public Game() {
		super("Slick Kangourou");
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		this.map.render(0, 0);

		g.drawAnimation(animation, x - 50, y - 130);
		g.setColor(new Color(255, 0, 0, 1.0f));
		g.fillOval(x, y, 2, 2);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		this.map = new TiledMap("map/map1.tmx");
		SpriteSheet spriteSheet = new SpriteSheet("sprite/kangaroo.png", 100, 130);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 0), 500);
		animation.addFrame(spriteSheet.getSprite(1, 0), 200);
		this.animation = animation;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		System.out.println("left:" + this.isLeftPressed + "  right:" + this.isRightPressed);

		if (this.isLeftPressed && !this.isRightPressed) {
			this.moving = true;
			this.direction = -1;
		} else if (this.isRightPressed && !this.isLeftPressed) {
			this.moving = true;
			this.direction = 1;
		} else if (!this.isLeftPressed && !this.isRightPressed) {
			this.moving = false;
		}

		if (this.moving) {
			this.x += .2f * delta * direction;
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			System.out.println("Bye bye!");
			container.exit();
		}

		switch (key) {
		case Input.KEY_LEFT:
			this.isLeftPressed = false;
			break;
		case Input.KEY_RIGHT:
			this.isRightPressed = false;
			break;
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_LEFT:
			this.isLeftPressed = true;
			break;
		case Input.KEY_RIGHT:
			this.isRightPressed = true;
			break;
		}

		if (this.isLeftPressed && this.isRightPressed) {
			this.moving = true;
			this.direction *= -1;
		}
	}

	public static void main(String[] args) throws SlickException {
		NativeLoader loader = new NativeLoader();
		loader.loadLibrary("lwjgl64");

		AppGameContainer app = new AppGameContainer(new Game());
		app.setDisplayMode(WIDTH, HEIGHT, false);
		app.setForceExit(false);
		app.start();
	}

}
