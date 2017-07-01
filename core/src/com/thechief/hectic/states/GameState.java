package com.thechief.hectic.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.thechief.hectic.Main;
import com.thechief.hectic.entities.Enemy;
import com.thechief.hectic.entities.Entity;
import com.thechief.hectic.entities.Meteorite;
import com.thechief.hectic.entities.Player;
import com.thechief.hectic.entities.Spawner;
import com.thechief.hectic.entity.pickup.PickupSpawner;
import com.thechief.hectic.graphics.ScoreBoard;

public class GameState extends State {

	public static final float GRAVITY = -25f;
	public static boolean PAUSED = false;

	public static boolean EPILEPSY = true;

	public static int SCORE = 0, HIGH_SCORE = 0;

	public static boolean DIED = false;

	private Player player;
	private Spawner spawner;
	private PickupSpawner ps;

	private ScoreBoard scoreBoard;

	public Array<Entity> entities = new Array<Entity>();
	public Array<Enemy> enemies = new Array<Enemy>();
	public Array<Meteorite> meteors = new Array<Meteorite>();

	private int time = 0;

	@Override
	public void create() {
		setUp(Main.WIDTH, Main.HEIGHT);
		player = new Player(this, new Vector2(camera.position.x - 32, 0), 64, 64);
		entities.add(player);
		spawner = new Spawner(this, new Vector2(camera.position.x - 32, Main.HEIGHT - 156), 64, 64);
		entities.add(spawner);
		scoreBoard = new ScoreBoard(camera, new Vector2(20, Main.HEIGHT - 20));
		entities.add(scoreBoard);
		ps = new PickupSpawner(this);
		entities.add(ps);
	}

	int between = 10;

	@Override
	public void update(float dt) {
		if (EPILEPSY) {
			time++;
			if (time % 600 == 0) {
				between--;
				between = MathUtils.clamp(between, 5, 10);
			}
		}
		if (time % between == 0) {
			Gdx.gl.glClearColor(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1f);
		}
		if (Gdx.input.isKeyJustPressed(Keys.E)) {
			StateManager.setCurrentState(new ShopState(camera));
		}
		if (PAUSED) {
			player.update(dt);
			return;
		}
		for (Entity e : entities) {
			e.update(dt);
		}
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

	private int amplitude = -1;
	private boolean finishedShake = false;
	private float bx, by;

	public void shakeScreen(int amp) {
		if (amplitude == -1) {
			amplitude = amp;
			bx = camera.position.x;
			by = camera.position.y;
		}

		amplitude *= 0.9;

		camera.position.x += amplitude;
		camera.position.y += amplitude;

		if (amplitude < 0.2f) {
			finishedShake = true;
		}

		if (finishedShake) {
			amplitude = -1;
			camera.position.x = bx;
			camera.position.y = by;
		}
	}

	public Player getPlayer() {
		return player;
	}

}
