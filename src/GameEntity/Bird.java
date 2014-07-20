package GameEntity;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;

import Launcher.Launcher;

public class Bird extends BasicGameObject {
	// set accelerator
	private static final float ACCE = 0.3f;
	// set rotation
	private float rotation;
	private final float rotaitionIncrease = 1.5f;// how fast the bird rotate
	private final float IMPACTRADIUS = 13;

	public Bird(String name) {
		super(name, 100, 200, 0);
		this.hitbox = new Circle(posX, posY, IMPACTRADIUS);
		rotation = 0;
	}

	public void update(Input in) {
		if (!Launcher.gameOver && this.posY > 10
				&& in.isKeyDown(Keyboard.KEY_SPACE)) {
			this.speed = -4.0f;
			rotation = -45;
		}
		/*
		 * if (in.isKeyDown(Keyboard.KEY_LEFT)) this.posX -= 3; if
		 * (in.isKeyDown(Keyboard.KEY_RIGHT)) this.posX += 3;
		 */

		// movement
		posY += speed;
		speed += ACCE;
		hitbox.setLocation(posX, posY);
		hitbox.setCenterX(posX);
		hitbox.setCenterY(posY);
		rotation += this.rotaitionIncrease;

		// bouncing
		bouncing();
	}

	private void bouncing() {
		// set bounds and bouncing regel
		if (posY > 390 && speed > 1.32) {
			// for test
			System.out.println(this.posY + " " + this.speed);
			this.speed = -speed*0.6f;
			this.rotation = -45;
			this.posY = 390;
		} else if (posY >= 390 && speed != 0) {
			speed = 0;
			this.rotation = 0;
			this.posY = 390;
		}
		// rotation limit
		if (this.rotation > 90) {
			this.rotation = 90;
		}
	}

	public float getRotation() {
		return rotation;
	}
}
