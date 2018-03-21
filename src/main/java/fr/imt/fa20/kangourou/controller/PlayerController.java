package fr.imt.fa20.kangourou.controller;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import fr.imt.fa20.kangourou.character.Direction;
import fr.imt.fa20.kangourou.character.player.Player;
import fr.imt.fa20.kangourou.character.player.PlayerState;
import fr.imt.fa20.kangourou.character.state.HorizontalState;
import fr.imt.fa20.kangourou.character.state.VerticalState;

public class PlayerController implements KeyListener {

	private Player player;
	private Input input;

	public PlayerController(Player player, Input input) {
		this.player = player;
		this.input = input;
	}

	@Override
	public void setInput(Input input) {
		this.input = input;
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void inputEnded() {

	}

	@Override
	public void inputStarted() {

	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_RIGHT:
			this.player.setDirection(Direction.RIGHT);
			this.player.setHorizontalState(HorizontalState.RUNNING);
			break;
		case Input.KEY_LEFT:
			this.player.setDirection(Direction.LEFT);
			this.player.setHorizontalState(HorizontalState.RUNNING);
			break;
		case Input.KEY_UP:
			if (this.player.getVerticalState() == VerticalState.NONE) {
				this.player.setVerticalState(VerticalState.PREPARING_JUMP);
				//this.player.setHorizontalState(HorizontalState.PREPARING_JUMP);
				//this.player.setVerticalState(VerticalState.JUMPING);
				//this.player.setVelocityY(-0.5f);
			}

			break;
		case Input.KEY_SPACE:
			this.player.setHorizontalState(HorizontalState.PUNCHING);
			break;
		}

	}

	@Override
	public void keyReleased(int key, char c) {
		if (input.isKeyDown(Input.KEY_LEFT)) {
			this.player.setHorizontalState(HorizontalState.RUNNING);
			this.player.setDirection(Direction.LEFT);
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			this.player.setHorizontalState(HorizontalState.RUNNING);
			this.player.setDirection(Direction.RIGHT);
		} else {
			this.player.setHorizontalState(HorizontalState.STANDING_BY);
		}
	}

}
