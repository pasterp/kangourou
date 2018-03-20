package fr.imt.fa20.kangourou.controller;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import fr.imt.fa20.kangourou.character.Direction;
import fr.imt.fa20.kangourou.character.player.Player;
import fr.imt.fa20.kangourou.character.player.PlayerState;

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
			this.player.setState(PlayerState.RUNNING);
			break;
		case Input.KEY_LEFT:
			this.player.setDirection(Direction.LEFT);
			this.player.setState(PlayerState.RUNNING);
			break;
		case Input.KEY_SPACE:
			this.player.setState(PlayerState.PREPARING_JUMP);
			break;
		}

	}

	@Override
	public void keyReleased(int key, char c) {
		if (input.isKeyDown(Input.KEY_LEFT)) {
			this.player.setState(PlayerState.RUNNING);
			this.player.setDirection(Direction.LEFT);
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			this.player.setState(PlayerState.RUNNING);
			this.player.setDirection(Direction.RIGHT);
		} else {
			this.player.setState(PlayerState.STANDING_BY);
		}
	}

}
