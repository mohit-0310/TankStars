package com.mygdx.game;

import Screens.MainScreen;
import Screens.PlayScreen2;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import Screens.LoadingScreen;

import Screens.PlayScreen;

public class Application extends Game {

	public static final int V_WIDTH = 890;
	public static final int V_HEIGHT = 380;

	public OrthographicCamera camera;
	public SpriteBatch batch;

	public BitmapFont font24, font;

	public AssetManager assets;

	public LoadingScreen loadingScreen;

	public MainScreen mainMenuScreen;
	public PlayScreen playScreen;
	public PlayScreen2 playScreen2;

	@Override
	public void create() {
		assets = new AssetManager();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, V_WIDTH, V_HEIGHT);
		batch = new SpriteBatch();
		font24 = new BitmapFont();
		font = new BitmapFont();

		loadingScreen = new LoadingScreen(this);
		mainMenuScreen = new MainScreen(this);
		playScreen = new PlayScreen(this);
		playScreen2 = new PlayScreen2(this);
		font24.setColor(Color.BLACK);

		this.setScreen(loadingScreen);
	}

	@Override
	public void render() {
		super.render();

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		assets.dispose();
		loadingScreen.dispose();

		mainMenuScreen.dispose();
		playScreen.dispose();
	}

}