package com.thechief.hectic.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.thechief.hectic.Main;
import com.thechief.hectic.entities.Enemy;
import com.thechief.hectic.entities.Entity;
import com.thechief.hectic.entities.Player;
import com.thechief.hectic.entities.Spawner;
import com.thechief.hectic.entity.pickup.PickupSpawner;
import com.thechief.hectic.graphics.ScoreBoard;

public class GameState extends State {

	public static final float GRAVITY = -25f;

	public static int SCORE = 0, HIGH_SCORE = 0;
	
	public static boolean DIED = false;

	private Player player;
	private Spawner spawner;
	private PickupSpawner ps;
	
	private ScoreBoard scoreBoard;
	
	public Array<Entity> entities = new Array<Entity>();
	public Array<Enemy> enemies = new Array<Enemy>();
	
	@Override
	public void create() {
		Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
		setUp(Main.WIDTH, Main.HEIGHT);
		player = new Player(this, new Vector2(camera.position.x - 32, 128), 64, 64);
		entities.add(player);
		spawner = new Spawner(this, new Vector2(camera.position.x - 32, Main.HEIGHT - 156), 64, 64);
		entities.add(spawner);
		scoreBoard = new ScoreBoard(camera, new Vector2(20, Main.HEIGHT - 20));
		entities.add(scoreBoard);
		ps = new PickupSpawner(this);
		entities.add(ps);
	}

	@Override
	public void update(float dt) {
		for (Entity e : entities) {
			e.update(dt);
		}
		System.out.println(camera.position.x);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		for (Entity e : entities) {
			e.render(sb);
		}
		sb.end();
	}

	@Override
	public void dispose() {

	}

	public Player getPlayer() {
		return player;
	}
	
}
