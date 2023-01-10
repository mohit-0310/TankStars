package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Application;

public class LoadingScreen implements Screen {

    private Application app;

    private ShapeRenderer shapeRenderer;
    private float progress;
    private Texture texture;


    public LoadingScreen(Application app) {
        this.app = app;
        this.shapeRenderer = new ShapeRenderer();
        texture = new Texture("homescreen2.jpg");
    }
//    private void loadTextures() {
//        backgroundTexture = new Texture("images/background.png");
//        backgroundSprite =new Sprite(backgroundTexture);
//    }
//
//    public void renderBackground() {
//        backgroundSprite.draw(spriteBatch);
//    }

    private void queueAssets() {
//        app.assets.load("img/splash.png", Texture.class);
        app.assets.load("ui/uiskin.atlas", TextureAtlas.class);
    }

    @Override
    public void show() {
        System.out.println("LOADING");
        shapeRenderer.setProjectionMatrix(app.camera.combined);
        this.progress = 0f;
        queueAssets();
    }

    private void update(float delta) {
        progress = MathUtils.lerp(progress, app.assets.getProgress(), .1f);
        if (app.assets.update() && progress >= app.assets.getProgress() - .001f) {
            app.setScreen(app.playScreen2);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

//        spriteBatch.begin();
//        renderBackground(); //In first place!!!!
//        drawStuff();
//        drawMoreStuff();
//        drawMoreMoreStuff();
//        spriteBatch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(100, app.camera.viewportHeight - 50, app.camera.viewportWidth - 200, 24);

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(100, app.camera.viewportHeight - 50, progress * (app.camera.viewportWidth - 200), 24);
        shapeRenderer.end();

        app.batch.begin();
        app.batch.draw(texture, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()-100);
        app.font24.draw(app.batch, "Loading", 50, 465);
        app.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
