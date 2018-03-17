package fr.imt.fa20.kangourou.old;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class CharacterController implements KeyListener {

	private Character character;

	public CharacterController(Character character) {
		this.character = character;
	}

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_LEFT:
			this.character.setDirection(Direction.LEFT);
			this.character.setMoving(true);
			break;
		case Input.KEY_RIGHT:
			this.character.setDirection(Direction.LEFT);
			this.character.setMoving(true);
			break;
		// case Input.KEY_UP:
		// // TODO
		// this.character.setMoving(true);
		// break;
		// case Input.KEY_DOWN:
		// // TODO
		// this.character.setMoving(true);
		// break;
		}

	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		this.character.setMoving(false);

	}

}
