package GameEntity;

import java.util.LinkedList;
import java.util.List;
import Launcher.Launcher;

import org.newdawn.slick.geom.*;

/**
 * 
 * 
 * 
 * @param y
 *            vectical center of the passage
 * @param h
 *            height of the passage
 * 
 * @author smithdodo
 * 
 * 
 */

public class Obstacle extends BasicGameObject {
	// height of passage
	private int height;
	// 2 hitboxes
	public List<Shape> hitboxes = new LinkedList<>();
	public static boolean passed;

	// random number generator

	public Obstacle(String name) {
		super(name, 300, Launcher.r.nextInt(350), Launcher.SPEED);
		this.height = Launcher.r.nextInt(40) + 80;

		// the passage should not be to far alway from last obstacle
		if (!Launcher.obs.isEmpty()) {
			float diff = posY// height diff with last obstacle
					- Launcher.obs.get(Launcher.obs.size() - 1).getPosY();
			if (Math.pow(2, diff) > 90000) {
				if (diff >= 0)
					posY = Launcher.obs.get(Launcher.obs.size() - 1).getPosY() + 100;
				else
					posY = Launcher.obs.get(Launcher.obs.size() - 1).getPosY() - 100;
			}
		}
		if (posY > 390 - height)
			posY = 390 - height;

		this.hitboxes.add(new Rectangle(posX, 0, 52, posY));
		this.hitboxes.add(new Rectangle(posX, posY + height, 52, 400 - posY
				- height));

		passed = false;
	}

	public void update() {
		if (!Launcher.gameOver) {
			this.posX -= this.speed;
			this.hitboxes.get(0).setX(this.posX);
			this.hitboxes.get(1).setX(this.posX);
			if (this.posX < -60) {
				List<Obstacle> t = new LinkedList<>();
				for (Obstacle o : Launcher.obs) {
					if (o != this)
						t.add(o);
				}
				Launcher.obs = t;
			}
			if (this.posX < 40 && !passed) {
				Launcher.score++;
				passed = true;
			}
		}

	}

	public int getHeight() {
		return height;
	}

}
