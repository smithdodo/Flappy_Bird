package Launcher;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import GameEntity.*;

import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

public class Launcher extends BasicGame {
	static Input input;
	// a random number generater for obstaqcales posY
	public static Random r;
	Bird player;
	// speed of obstacle
	public static float SPEED = 3.5f;
	public static List<Obstacle> obs;
	public static final long INTERVAL = 1000;
	public long lastObstacal;
	public static boolean gameOver;
	boolean impacted;
	public static List<Ground> grounds;// for moving ground
	public Image ground;
	public Image sprites;
	public SpriteSheet spriteSheet;
	public Image[] birds = new Image[4];
	public Animation bird;
	public static Integer score;

	Image bg;
	//public static Music fly;

	public Launcher(String name) {
		super(name);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		//input = new Input(500);
		r = new Random();
		player = new Bird("player");
		obs = new LinkedList<>();
		lastObstacal = Sys.getTime();
		//gameOver = false;
		impacted = false;
		bg = new Image("assets/background.png");
		grounds = new LinkedList<Ground>();
		grounds.add(new Ground("ground", 0));
		ground = new Image("assets/ground.png");
		sprites = new Image("assets/sprites.png");
		birds[0] = sprites.getSubImage(690, 560, 34, 24);// m
		birds[1] = sprites.getSubImage(690, 534, 34, 24);// u
		birds[2] = sprites.getSubImage(690, 560, 34, 24);// m
		birds[3] = sprites.getSubImage(690, 586, 34, 24);// d
		bird = new Animation(birds, 50);
		score = new Integer(0);
		//fly=new Music("assets/flyup.mp3");
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {

		if (gameOver) {
			gameOver=false;
			//arg0.pause();
		}

		player.update(input);
		for (Obstacle o : obs) {
			o.update();
		}

		// generate obstacle each second
		if (Sys.getTime() - lastObstacal > this.INTERVAL) {
			obs.add(new Obstacle("obstc"));
			lastObstacal = Sys.getTime();
		}
		// generate new ground and delete old
		if (grounds.size() < 2 && grounds.get(0).getPosX() < 0)
			grounds.add(new Ground("ground", grounds.get(0).getPosX() + 335));
		// update ground
		for (Ground g : grounds) {
			g.update();
		}

		// check impact
		for (Obstacle o : obs) {
			if (o.hitboxes.get(0).intersects(player.getHitbox())
					|| o.hitboxes.get(1).intersects(player.getHitbox()))
				gameOver = true;
		}

	}

	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		g.drawImage(bg, 0, 0);
		// draw obstacles
		g.setColor(Color.green);
		for (Obstacle o : obs) {
			// upper obstacle
			g.drawImage(sprites, o.getPosX(), 0, o.getPosX() + 52, o.getPosY(),
					582, 644 - o.hitboxes.get(0).getMaxY(), 634, 644);
			Integer i = new Integer(o.getHeight());
			g.drawString(i.toString(), o.getPosX(), o.getPosY());
			// lower obstacle
			g.drawImage(sprites, o.getPosX() + 5, o.getPosY() + o.getHeight(),
					o.getPosX() + 52, 400, 636, 324, 688, 736 - o.getHeight()
							- o.getPosY());

		}
		// draw bird
		g.rotate(player.getPosX(), player.getPosY(), player.getRotation());
		g.drawAnimation(bird, player.getPosX() - 18, player.getPosY() - 12);
		g.rotate(player.getPosX(), player.getPosY(), -player.getRotation());
		// draw ground
		for (Ground grd : grounds) {
			g.drawImage(ground, grd.getPosX(), grd.getPosY());
		}
		g.setColor(Color.red);
		g.drawString(this.score.toString(), 270f, 10f);
		if (gameOver) {
			g.setColor(Color.lightGray);
			g.drawString("GAME OVER!!", 100, 200);
		}

	}

	public static void main(String[] args) {
		input=new Input(0);
		gameOver=false;
		try {
			AppGameContainer app = new AppGameContainer(new Launcher(
					"Flappy Bird"));
			app.setDisplayMode(288, 512, false);
			app.setShowFPS(true);
			app.setTargetFrameRate(70);
			app.setVSync(true);
			app.start();
			//app.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
