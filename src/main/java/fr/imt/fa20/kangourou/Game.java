package fr.imt.fa20.kangourou;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * A game using Slick2d
 */
public class Game extends BasicGame {

	public Game() {
		super("Slick Kangourou");
		// TODO Auto-generated constructor stub
	}

	/** Screen width */
	private static final int WIDTH = 800;
	/** Screen height */
	private static final int HEIGHT = 600;

	private GameContainer container;
	private TiledMap map;

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		this.map = new TiledMap("map/map.tmx");
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		this.map.render(0, 0);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
	}

	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			this.container.exit();
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
