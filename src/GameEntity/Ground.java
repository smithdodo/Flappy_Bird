package GameEntity;

import Launcher.*;

public class Ground extends BasicGameObject {

	public Ground(String name, float start) {
		super(name, start, 400, Launcher.SPEED);
	}

	public void update() {
		if (!Launcher.gameOver) {
			posX -= speed;
			if (posX < -340)
				Launcher.grounds.remove(this);
		}
	}

}
