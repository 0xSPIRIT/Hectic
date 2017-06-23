package com.thechief.hectic.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StateManager {

	private StateManager() {
	}

	private static State currentState;

	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State s) {
		if (currentState != null) {
			currentState.dispose();
		}
		currentState = s;
		currentState.create();
	}

	public static void updateAndRenderCurrentState(SpriteBatch sb, float dt) {
		if (getCurrentState() != null) {
			getCurrentState().update(dt);
			getCurrentState().render(sb);
		}
	}

}
