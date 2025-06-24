package screens;

import assets.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogicgames.MascOnX.MainX;
import components.Portal;
import entity.*;
import player.Player;
import entity.PlayerState;
import scenes.ComponentScene;
import systems.AudioManager;
import systems.CollisionHandler;
import systems.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    private float invulnerabilidadeTempo = 0f;
    private final float INVULNERABILIDADE_DURACAO = 2.0f;

    private final List<IRenderableComponent> renderables = new ArrayList<>();
    private final List<IUpdateComponent> updatables = new ArrayList<>();

    private final MainX game;
    private SpriteBatch batch;
    private CameraComponent cameraComponent;
    private AudioManager audioManager;
    private CollisionHandler collisionHandler;
    private InputHandler inputHandler;
    private Background1 background1;
    private Player player;
    private List<Plataforma> plataformasDaCena;
    private List<Spines> espinhos;
    private List<Inimigo> inimigos;
    private ComponentScene componentScene;
    private OrthographicCamera hudCamera;
    private float tempoTransicao = 0f;
    private Portal portal;

    public GameScreen(MainX game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = game.batch;

        if (game.musica != null && game.musica.isPlaying()) {
            game.musica.stop();
        }

        background1 = new Background1();
        componentScene = new ComponentScene();

        plataformasDaCena = Plataforma.gerarPlataformas();
        renderables.addAll(plataformasDaCena);

        espinhos = Spines.gerarEspinhos();
        renderables.addAll(espinhos);

        inimigos = Inimigo.gerarInimigos();
        for (Inimigo i : inimigos) {
            renderables.add(i);
            updatables.add(i);
        }

        player = new Player(new Vector2(10, 90));
        player.setState(PlayerState.IDLE);

        List<Rectangle> plataformas = new ArrayList<>();
        for (Plataforma p : plataformasDaCena) plataformas.add(p.getBounds());
        player.checkPlataformaColisao(plataformas);

        cameraComponent = new CameraComponent(
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight(),
            background1.getWorldWidth(),
            background1.getWorldHeight()
        );

        audioManager = new AudioManager();
        audioManager.init(game);
        audioManager.playMusic("gamemusic.mp3", true);

        collisionHandler = new CollisionHandler();
        inputHandler = new InputHandler(player);

        hudCamera = new OrthographicCamera();
        hudCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        portal = new Portal(17981, 260);
        renderables.add(portal);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (IUpdateComponent u : updatables) u.update(delta);

        List<Rectangle> plataformas = new ArrayList<>();
        for (Plataforma p : plataformasDaCena) plataformas.add(p.getBounds());

        player.checkPlataformaColisao(plataformas);
        inputHandler.handleInput();
        player.update(delta);

        portal.update(delta, player);

        if (player.isTeleporting()) {
            tempoTransicao += delta;
            if (tempoTransicao >= 1.5f) {
                game.setScreen(new MenuScreen(game));
            }
        }

        cameraComponent.update(player.getPosition());
        batch.setProjectionMatrix(cameraComponent.getCamera().combined);

        batch.begin();
        background1.render(batch, cameraComponent.getCamera().position.x, delta);
        for (IRenderableComponent r : renderables) r.render(batch);
        player.render(batch);
        batch.end();

        // HUD (corações)
        hudCamera.update();
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
        componentScene.render(batch);
        batch.end();

        if (invulnerabilidadeTempo <= 0f && player.getState() != PlayerState.DEAD) {
            collisionHandler.verificarColisoes(player, coletarColidiveis());

            if (collisionHandler.jogadorFoiAtingido()) {
                componentScene.takeDamage();
                invulnerabilidadeTempo = INVULNERABILIDADE_DURACAO;

                if (componentScene.isDead()) {
                    player.setState(PlayerState.DEAD);
                }
            }
        } else {
            invulnerabilidadeTempo -= delta;
        }

        if (player.getState() == PlayerState.DEAD && player.isDeadAnimationFinished()) {
            game.setScreen(new GameOverScreen(game));
            return;
        }

        for (Kunai kunai : player.getKunais()) {
            for (Inimigo inimigo : inimigos) {
                if (inimigo.isVivo() && inimigo.getBounds().overlaps(kunai.getBounds())) {
                    inimigo.morrer();
                }
            }
        }
    }

    private List<CollidableComponent> coletarColidiveis() {
        List<CollidableComponent> colidiveis = new ArrayList<>();
        colidiveis.addAll(plataformasDaCena);
        colidiveis.addAll(espinhos);
        for (Inimigo inimigo : inimigos) {
            if (inimigo.isVivo()) {
                colidiveis.add(inimigo);
            }
        }
        return colidiveis;
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        audioManager.dispose();
        background1.dispose();

        for (IRenderableComponent r : renderables) {
            if (r instanceof Plataforma) ((Plataforma) r).dispose();
            else if (r instanceof Spines) ((Spines) r).dispose();
            else if (r instanceof Inimigo) ((Inimigo) r).dispose();
        }

        componentScene.dispose();
        if (portal != null) portal.dispose();
    }
}
