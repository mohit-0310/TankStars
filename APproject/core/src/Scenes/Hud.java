package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Application;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer timeLeft;
    private Integer p1Score;
    private Integer p2Score;
    private float t;

    private Label timeleftLabel;
    private Label p1scoreLabel;
    private Label p2scoreLabel;
    private Label timeLabel;
    private Label p1Label;
    private Label p2Label;

    public Hud(SpriteBatch sb){
        timeLeft=180;
        p1Score=0;
        p2Score=0;

        viewport = new FitViewport(Application.V_WIDTH, Application.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        timeleftLabel =new Label(String.format("%03d", timeLeft), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        p1scoreLabel = new Label(String.format("%03d", p1Score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        p2scoreLabel = new Label(String.format("%03d", p2Score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        p1Label = new Label("Player 1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        p2Label = new Label("Player 2", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(p1Label).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(p2Label).expandX().padTop(10);
        table.row();
        table.add(p1scoreLabel).expandX().padTop(10);
        table.add(timeleftLabel).expandX().padTop(10);
        table.add(p2scoreLabel).expandX().padTop(10);

        stage.addActor(table);



    }
    public void update(float dt){
        t+=dt;

        if(t>=1){
            timeLeft--;
            timeleftLabel.setText(String.format("%03d", timeLeft));
            t=0;
        }
        if(timeLeft<1){
            Gdx.app.exit();
        }

    }
    public void dispose(){
        stage.dispose();
    }
}
