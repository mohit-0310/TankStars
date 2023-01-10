package Screens;

import Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Application;
import com.mygdx.game.TiledObjectUtil;

public class PlayScreen2 implements Screen {
    private Application game;

    private boolean DEBUG = false;
    private final float SCALE = 2.0f;
    private SpriteBatch batch;

    public float PPM = 32;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera camera;
    private Viewport gamePort;

    private OrthogonalTiledMapRenderer tmr;
    private TiledMap map;

    private World world;
    private Body player, platform;
    private Texture texture;
    private Hud hud;

//    private Viewport gamePort;
//    private Hud hud;
//
//    private TmxMapLoader mapLoader;
//    private TiledMap map;
//    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen2(Application game) {
        this.game = game;


        camera = new OrthographicCamera();
        gamePort = new FitViewport(game.V_WIDTH,game.V_HEIGHT, camera);
        hud =new Hud(game.batch);
//        gamePort = new FitViewport(game.V_WIDTH,game.V_HEIGHT, gamecam);
//        hud =new Hud(game.batch);
//        mapLoader = new TmxMapLoader();
//        map = mapLoader.load("design1.tmx");
//        renderer = new OrthogonalTiledMapRenderer(map);
//        gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
//        camera.setToOrtho(false, game.V_WIDTH/2,game.V_HEIGHT/2);
        world = new World(new Vector2(0, -9.8f), false);
        b2dr = new Box2DDebugRenderer();

        player = createBox(150, 300 ,32,32,false);
//        platform = createBox(150, 140,64,32,true);
        batch = new SpriteBatch();
//        texture =new Texture("");
        map = new TmxMapLoader().load("design1.tmx");
        tmr = new OrthogonalTiledMapRenderer(map);
        camera.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        TiledObjectUtil.parseTiledObjectLayer(world, map.getLayers().get("Object Layer 1").getObjects());
    }



    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() > 445) {
                camera.position.x += 100 * dt;
            } else {
                camera.position.x -= 100 * dt;
            }
        }
    }

    public void update(float dt) {
        handleInput(dt);
        camera.update();
//        renderer.setView(gamecam);
        world.step(1/60f, 6,2);
        inputUpdate(dt);
//        cameraUpdate(dt);
        tmr.setView(camera);
//        camera.update();
        batch.setProjectionMatrix(camera.combined);
        hud.update(dt);

    }

    public void inputUpdate(float dt){
        int hForce = 0;

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            hForce -=4;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            hForce +=4;
        }

        player.setLinearVelocity(hForce*5, player.getLinearVelocity().y);
    }

    public void cameraUpdate(float dt){ //remove for the camera to not move with player


        camera.update();
    }

    @Override
    public void render(float delta) {
//        update(delta);
        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tmr.render();

        b2dr.render(world, camera.combined.scl(PPM));
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
//        renderer.render();
//        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
//        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
//        camera.setToOrtho(false, width/SCALE, height/SCALE);
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
        world.dispose();
        b2dr.dispose();
        batch.dispose();
        texture.dispose();
        tmr.dispose();
        map.dispose();
        hud.dispose();
    }

    public Body createBox(int x, int y, int width, int height, boolean isStatic){
        Body pBody;
        BodyDef def = new BodyDef();
        Vector2 gravity = new Vector2(0, 0);

        if(isStatic){
            def.type = BodyDef.BodyType.StaticBody;
        }
        else{
            def.type = BodyDef.BodyType.DynamicBody;
        }

        def.position.set(x/PPM,y/PPM);
        def.fixedRotation = true;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();

        shape.setAsBox(width / 2 / PPM,height / 2 / PPM);
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = shape;
        fixtureDef1.density = 0.5f;
        fixtureDef1.friction = 3f;
        Fixture fixture1 = pBody.createFixture(fixtureDef1);
        pBody.createFixture(shape, 1.0f);


        shape.dispose();

        return pBody;
    }

}
