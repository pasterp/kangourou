package fr.imt.fa20.kangourou.camera;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import fr.imt.fa20.kangourou.character.Character;

public class Camera {

	private Character player;
	private float xCamera, yCamera, w, h;
	GameContainer container;

	public Camera(Character player, GameContainer container) {
		this.container = container;
		this.player = player;
		
		yCamera = 0; //Until map be taller
		xCamera = player.getX();

		w = container.getWidth()/32.f; //20
		h = container.getHeight()/32.f; //10
	}

	public void place(Graphics g) {
		g.translate(-xCamera,yCamera);
		//negative x is going to right
		//x is pixel based move
		// (0,0) is upper-left of the map
	}

	public void update() {

		if(player.getX() > xCamera + 6*w){
			xCamera = player.getX() - 6*w ;
		}
		if(player.getX() < xCamera + 5*w){
			xCamera = player.getX() - 5*w;
		}



		xCamera = Math.max(xCamera, 0);
		xCamera = Math.min(xCamera, (player.getMap().width-20)*16);
	}

}
