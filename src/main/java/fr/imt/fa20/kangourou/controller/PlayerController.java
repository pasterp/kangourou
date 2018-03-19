package fr.imt.fa20.kangourou.controller;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import fr.imt.fa20.kangourou.character.Direction;
import fr.imt.fa20.kangourou.character.player.Player;
import fr.imt.fa20.kangourou.character.player.PlayerState;

public class PlayerController implements KeyListener {

	private Player player;

	public PlayerController(Player player) {
		this.player = player;
	}

	@Override
	public void setInput(Input input) {

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
		// case Input.KEY_UP:
		// this.player.setJumpState(JumpState.PREPARING);
		// this.player.setMoving(true);
		// break;
		// case Input.KEY_DOWN:
		// this.player.setFaceRight(true);
		// this.player.setMoving(true);
		// break;
		}

	}

	@Override
	public void keyReleased(int key, char c) {
		this.player.setState(PlayerState.STANDING_BY);
	}

}
