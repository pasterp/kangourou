package fr.imt.fa20.kangourou.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import fr.imt.fa20.kangourou.camera.Camera;
import fr.imt.fa20.kangourou.character.player.Player;
import fr.imt.fa20.kangourou.controller.PlayerController;
import fr.imt.fa20.kangourou.controller.TriggerController;
import fr.imt.fa20.kangourou.map.Map;

public class ObjectsGame extends BasicGame {

	public static final int HEIGHT = 640;
	public static final int WIDTH = 320;

	private GameContainer container;
	private Map map = new Map();
	private Player player = new Player(map);
	private TriggerController triggers = new TriggerController(map, player);
	private Camera camera;

	public static void main(String[] args) throws SlickException {
		NativeLoader loader = new NativeLoader();
		loader.loadLibrary("lwjgl64");
		AppGameContainer app = new AppGameContainer(new ObjectsGame(), HEIGHT, WIDTH, false);
		app.setAlwaysRender(true);
		app.start();
	}

	public ObjectsGame() {
		super("Level One");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		this.map.init();
		this.player.init();
		camera = new Camera(player,container);
		PlayerController controler = new PlayerController(this.player, container.getInput());
		container.getInput().addKeyListener(controler);
		this.container = container;
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.scale(2, 2);// double graphical size

		this.camera.place(g);
		this.map.renderBackground();
		this.player.render(g);
		this.map.renderForeground(g);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		this.triggers.update();
		this.player.update(delta);
		this.camera.update();
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
