package fr.imt.fa20.kangourou.controller;

import org.newdawn.slick.SlickException;

import fr.imt.fa20.kangourou.character.player.Player;
import fr.imt.fa20.kangourou.map.Map;

public class TriggerController {

	private Map map;
	private Player player;

	public TriggerController(Map map, Player player) {
		this.map = map;
		this.player = player;
	}

	public void update() throws SlickException {
		for (int objectID = 0; objectID < this.map.getObjectCount(); objectID++) {
			if (isInTrigger(objectID)) {
				if ("teleport".equals(this.map.getObjectType(objectID))) {
					this.teleport(objectID);
				} else if ("change-map".equals(this.map.getObjectType(objectID))) {
					this.changeMap(objectID);
				}
			}
		}
	}

	private boolean isInTrigger(int id) {
		return this.player.getX() > this.map.getObjectX(id)
				&& this.player.getX() < this.map.getObjectX(id) + this.map.getObjectWidth(id)
				&& this.player.getY() > this.map.getObjectY(id)
				&& this.player.getY() < this.map.getObjectY(id) + this.map.getObjectHeight(id);
	}

	private void teleport(int objectID) {
		this.player.setX(
				Float.parseFloat(this.map.getObjectProperty(objectID, "dest-x", Float.toString(this.player.getX()))));
		this.player.setY(
				Float.parseFloat(this.map.getObjectProperty(objectID, "dest-y", Float.toString(this.player.getY()))));
	}

	private void changeMap(int objectID) throws SlickException {
		this.teleport(objectID);
		String newMap = this.map.getObjectProperty(objectID, "dest-map", "undefined");
		if (!"undefined".equals(newMap)) {
			this.map.changeMap("map/" + newMap);
		}
	}

}
