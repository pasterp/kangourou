package fr.imt.fa20.kangourou.map;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.GeomUtil;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.css.Rect;

public class Map {
	private TiledMap tiledMap;
	private List<Rectangle> collisions;
	public int width;
	public int height;

	public void init() throws SlickException {
		this.tiledMap = new TiledMap("map/map_level_oneV3.tmx");
		width = tiledMap.getWidth();
		height = tiledMap.getHeight();

		collisions = new LinkedList<Rectangle>();
		int logicLayer = tiledMap.getLayerIndex("logic");
		int tileSize = tiledMap.getTileWidth();


		for (int x = 0; x < tiledMap.getWidth(); x++) {
			for (int y = 0; y < tiledMap.getHeight(); y++) {
				Image tile = tiledMap.getTileImage(x, y, logicLayer);
				if (tile != null) {
					collisions.add(new Rectangle(x * tileSize, y * tileSize, tileSize, tileSize));
				}
			}
		}

	}

	public void renderBackground() {
		// background
		this.tiledMap.render(0, 0, 0);
		// water
		this.tiledMap.render(0, 0, 1);
		// roof
		this.tiledMap.render(0, 0, 2);
		// ground
		this.tiledMap.render(0, 0, 3);


	}

	public void renderForeground(Graphics g) {
		// front cave
		this.tiledMap.render(0, 0, 4);
		// signs
		this.tiledMap.render(0, 0, 5);
		// grass
		this.tiledMap.render(0, 0, 6);

//		for (Shape r : collisions) {
//			g.setColor(Color.red);
//			g.draw(r);
//		}
	}

	public boolean isCollision(float x, float y) {
		int tileW = this.tiledMap.getTileWidth();
		int tileH = this.tiledMap.getTileHeight();
		int logicLayer = this.tiledMap.getLayerIndex("logic");
		Image tile = this.tiledMap.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
		boolean collision = tile != null;
		// if (collision) {
		// Color color = tile.getColor((int) x % tileW, (int) y % tileH);
		// collision = color.getAlpha() > 0;
		// }
		return collision;
	}

	public void changeMap(String file) throws SlickException {
		this.tiledMap = new TiledMap(file);
	}

	public int getObjectCount() {
		return this.tiledMap.getObjectCount(0);
	}

	public String getObjectType(int objectID) {
		return this.tiledMap.getObjectType(0, objectID);
	}

	public float getObjectX(int objectID) {
		return this.tiledMap.getObjectX(0, objectID);
	}

	public float getObjectY(int objectID) {
		return this.tiledMap.getObjectY(0, objectID);
	}

	public float getObjectWidth(int objectID) {
		return this.tiledMap.getObjectWidth(0, objectID);
	}

	public float getObjectHeight(int objectID) {
		return this.tiledMap.getObjectHeight(0, objectID);
	}

	public String getObjectProperty(int objectID, String propertyName, String def) {
		return this.tiledMap.getObjectProperty(0, objectID, propertyName, def);
	}

	public Shape isCollision(Shape hitbox) {
		for(Shape s : collisions) {
			if(s.intersects(hitbox))
				return s;
		}
		return null;
	}

}