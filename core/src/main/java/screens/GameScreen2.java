package screens;

import assets.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogicgames.MascOnX.MainX;
import entity.CameraComponent;
import entity.IRenderableComponent;
import entity.IUpdateComponent;
import entity.PlayerState;
import player.Player;
import systems.AudioController;

import java.util.ArrayList;
import java.util.List;

public class GameScreen2 implements Screen {

    private final List<IRenderableComponent> renderables = new ArrayList<>();
    private final List<IUpdateComponent> updatables = new ArrayList<>();

    private final MainX game;
    private SpriteBatch batch;
    private CameraComponent cameraComponent;
    private Music gameMusic;
    private Background2 background2;
    private Player player;


    public GameScreen2(MainX game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = game.batch;

        background2 = new Background2();

        float viewportWidth = (float) Gdx.graphics.getWidth();
        float viewportHeight = (float) Gdx.graphics.getHeight();

        cameraComponent = new CameraComponent(
            viewportWidth,
            viewportHeight,
            background2.getWorldWidth(),
            background2.getWorldHeight()
        );

        // Música da sala respeitando a configuração global
        if (AudioController.isMusicaAtivada()) {
            gameMusic = Gdx.audio.newMusic(Gdx.files.internal("gamemusic.mp3"));
            gameMusic.setLooping(true);
            gameMusic.play();
        }

        // Para a música do menu, se estiver tocando
        if (game.musica != null && game.musica.isPlaying()) {
            game.musica.stop();
        }

        for (Plataforma p : Plataforma2.gerarPlataformas()) {
            renderables.add(p);
        }

        for (Spines s : Spines.gerarEspinhos()) {
            renderables.add(s);
        }

        List<Inimigo> inimigos = Inimigo.gerarInimigos();
        for (Inimigo i : inimigos) {
            renderables.add(i);
            updatables.add(i);
        }

        player = new Player(new Vector2(159, 180));
        player.setState(PlayerState.RUN);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (IUpdateComponent u : updatables) u.update(delta);

        Vector2 playerPos = player.getPosition();
        float cameraX = Math.max(cameraComponent.getViewportWidth() / 2f,
            Math.min(playerPos.x, background2.getWorldWidth() - cameraComponent.getViewportWidth() / 2f));
        float cameraY = cameraComponent.getViewportHeight() / 2f;

        cameraComponent.follow(new Vector2(cameraX, cameraY));
        cameraComponent.update(player.getPosition());

        batch.setProjectionMatrix(cameraComponent.getCamera().combined);
        batch.begin();
        background2.render(batch, cameraComponent.getCamera().position.x, delta);
        for (IRenderableComponent r : renderables) r.render(batch);
        player.render(batch);
        batch.end();

        // Controles
        boolean moving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.moveX(200 * delta);
            player.setFacingRight(true);
            player.setState(PlayerState.RUN);
            moving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.moveX(-200 * delta);
            player.setFacingRight(false);
            player.setState(PlayerState.RUN);
            moving = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.jump();
            moving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.startSlide();
            moving = true;
        } else {
            player.stopSlide();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.setState(PlayerState.JUMPTHROW);
            player.throwKunai();
            moving = true;
        }

        if (!moving && player.isOnGround() && !player.isSliding()) {
            player.setState(PlayerState.IDLE);
        }

        player.update(delta);
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        if (gameMusic != null) gameMusic.dispose();
        if (background2 != null) background2.dispose();

        for (IRenderableComponent r : renderables) {
            if (r instanceof Plataforma) ((Plataforma) r).dispose();
            else if (r instanceof Spines) ((Spines) r).dispose();
            else if (r instanceof Inimigo) ((Inimigo) r).dispose();
        }
    }
}
