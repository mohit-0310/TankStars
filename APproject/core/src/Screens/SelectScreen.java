package Screens;

import Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Application;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SelectScreen implements Screen {
    private Application app;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    private ShapeRenderer shapeRenderer;
    private Skin skin;
    private Stage stage;
    private Texture texture;
//    private TmxMapLoader mapLoader;
//    private TiledMap map;
//    private OrthogonalTiledMapRenderer renderer;

    private final Texture tank1;
    private final Texture tank2;
    private final Texture tank3;

    private TextButton label1, label2 , ButtonStart;

    public SelectScreen(Application game){
        this.app = game;
        this.stage = new Stage(new FitViewport(Application.V_WIDTH, Application.V_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
        this.texture = new Texture("scene1.png");
        this.tank1 = new Texture("tank1.png");
        this.tank2 = new Texture("tank2.png");
        this.tank3 = new Texture("tank3.png");

    }
    @Override
    public void show() {
        System.out.println("Select MENU");
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font24);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        initButtons();
    }
    private void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        app.batch.begin();
        app.batch.draw(texture, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        app.batch.end();
        app.batch.begin();
        app.batch.draw(tank1, 70,250,150,150);
        app.batch.end();
        app.batch.begin();
        app.batch.draw(tank2, 230,250,150,150);
        app.batch.end();
        app.batch.begin();
        app.batch.draw(tank3, 390,250,150,150);
        app.batch.end();
        app.batch.begin();
        app.batch.draw(tank1, 70,80,150,150);
        app.batch.end();
        app.batch.begin();
        app.batch.draw(tank2, 230,80,150,150);
        app.batch.end();
        app.batch.begin();
        app.batch.draw(tank3, 390,80,150,150);
        app.batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        shapeRenderer.dispose();
    }

    private void initButtons(){
        label1 = new TextButton("Tank 1" , skin , "default");
        label1.setPosition(10,240);
        label1.setSize(50,30);
        label1.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        label1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.mainMenuScreen);
            }
        });
        label2 = new TextButton("Tank 2" , skin , "default");
        label2.setPosition(10,115);
        label2.setSize(50,30);
        label2.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        label2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.mainMenuScreen);
            }
        });
        ButtonStart = new TextButton("Start Game" , skin , "default");
        ButtonStart.setPosition(550,100);
        ButtonStart.setSize(280,60);
        ButtonStart.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        ButtonStart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.playScreen);
            }
        });
        stage.addActor(label1);
        stage.addActor(label2);
        stage.addActor(ButtonStart);
    }
}
