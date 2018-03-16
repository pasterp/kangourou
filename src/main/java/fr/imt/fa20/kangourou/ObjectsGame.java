package fr.imt.fa20.kangourou;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class ObjectsGame extends BasicGame {
	public ObjectsGame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	private GameContainer container;
	private Map map;
	private Character character = new Character(map);
	  private Camera camera = new Camera(character);
	  
	  public ObjectsGame() {
		  
	  }

	// [...] suite du code
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		this.map.renderBackground();
		this.character.render(g);
		this.map.renderForeground();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		this.map.init();
		this.character.init();
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		this.character.update(delta);
		this.updateCamera(container);
	}

	private void updateCamera(GameContainer container) {
		int w = container.getWidth() / 10;
		if (this.character.getX() > this.xCamera + w) {
			this.xCamera = this.character.getX() - w;
		} else if (this.character.getX() < this.xCamera - w) {
			this.xCamera = this.character.getX() + w;
		}
		int h = container.getHeight() / 8;
		if (this.character.getY() > this.yCamera + h) {
			this.yCamera = this.character.getY() - h;
		} else if (this.character.getY() < this.yCamera - h) {
			this.yCamera = this.character.getY() + h;
		}
	}

	private void changeMap(int objectID) throws SlickException {
		this.character.teleport(objectID);
		String newMap = this.map.getObjectProperty(objectID, "dest-map", "undefined");
		if (!"undefined".equals(newMap)) {
			this.map.changeMap("map/" + newMap);
		}
	}
}