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

	private int gravity = 3;
	
	/** Player **/
	private Player player;

	/** Game... */
	private GameContainer container;
	private TiledMap map;

	

	/** Keys **/
	private boolean isLeftPressed = false, isRightPressed = false;

	public Game() {
		super("Slick Kangourou");
		
	}

	@Override
	public void init(GameContainer container) throws SlickException  {
		this.container = container;
		this.map = new TiledMap("map/map1.tmx");
		 player = new Player();
		
	}
	
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.translate(container.getWidth() / 2 - player.x, container.getHeight() / 2 - player.y);
		this.map.render(0, 0);
		
		g.drawAnimation(player.getAnimation(), player.x - 50, player.y - 130);
		g.setColor(new Color(255, 0, 0, 1.0f));
		g.fillOval(player.x, player.y, 2, 2);
	}

	

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		System.out.println("left:" + this.isLeftPressed + "  right:" + this.isRightPressed);

		if (this.isLeftPressed && !this.isRightPressed) {
			player.moving = true;
			player.direction = -1;
		} else if (this.isRightPressed && !this.isLeftPressed) {
			player.moving = true;
			player.direction = 1;
		} else if (!this.isLeftPressed && !this.isRightPressed) {
			player.moving = false;
		}

		if (player.moving) {
			float futurX = player.x;
			futurX = player.x + .2f * delta * player.direction;
			Image tile = this.map.getTileImage((int) futurX / this.map.getTileWidth(),
					(int) player.y / this.map.getTileHeight(), this.map.getLayerIndex("logic"));
			if (tile != null) {
				player.moving = false;
			} else {
				player.x = futurX;
			}
		}

		float futurY = player.y;
		futurY += .2f * delta * gravity;
		Image tile = this.map.getTileImage((int) player.x / this.map.getTileWidth(),
				(int) futurY / this.map.getTileHeight(), this.map.getLayerIndex("logic"));
		if (tile != null) {
			player.moving = false;
		} else {
			player.y = futurY;
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
			player.moving = true;
			player.direction *= -1;
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
