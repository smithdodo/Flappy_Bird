package GameEntity;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public abstract class BasicGameObject extends BasicGame {
	// basic coordinate of object
	public float posX, posY;
	public float speed;
	public Shape hitbox;

	public BasicGameObject(String name, float x, float y, float speed) {
		super(name);
		this.posX = x;
		this.posY = y;
		this.speed=speed;
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub

	}

	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}
	
	public Shape getHitbox(){
		return hitbox;
	}

}
