package fr.imt.fa20.kangourou.old;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class ObjectsGame extends BasicGame {

	/** Screen width */
	private static final int WIDTH = 320;
	/** Screen height */
	private static final int HEIGHT = 160;

	private GameContainer container;
	private Map map;
	private Character golem;
	private Camera camera;

	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new ObjectsGame(), 800, 600, false).start();
	}

	public ObjectsGame() throws SlickException {
		super("Level One");
		this.map = new Map();
		this.golem = new Golem(map);
		this.camera = new Camera(golem);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		this.map.init();
		this.golem.init();
		CharacterController controler = new CharacterController(this.golem);
		container.getInput().addKeyListener(controler);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		this.camera.place(container, g);
		this.map.renderBackground();
		this.golem.render(g);
		this.map.renderForeground();
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		this.golem.update(delta);
		this.camera.update(container);
	}

	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			this.container.exit();
		}
	}

	@Override
	public void keyPressed(int key, char c) {
	}
}