package fr.imt.fa20.kangourou.character.player;

public enum PlayerState {
	PREPARING_JUMP,
	JUMPING, // moving upwards
	FALLING, // moving downards
	LANDING,
	STANDING_BY,
	SUFFERING,
	SLASHING,
	PUNCHING,
	RUNNING,
	CLIMBING,
	FROM_BEHIND // player's back
}
