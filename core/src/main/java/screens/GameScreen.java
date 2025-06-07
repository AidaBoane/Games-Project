package screens;

import assets.Espinhos;
import assets.Inimigo;
import assets.Plataforma;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogicgames.MascOnX.MainX;
import com.badlogic.gdx.math.Vector2;
import entity.CameraComponent;
import assets.Background1;
import entity.IRenderableComponent;
import entity.IUpdateComponent;

import java.util.ArrayList;
import java.util.List;


public class GameScreen implements Screen {

    private final List<IRenderableComponent> renderables = new ArrayList<>();
    private final List<IUpdateComponent> updatables = new ArrayList<>();

    private final MainX game;
    private SpriteBatch batch;
    private CameraComponent cameraComponent;
    private Music gameMusic;
    private Background1 background1;
    private Vector2 posicaoCamera;
    private float velocidadeCamera = 0.1f;

    public GameScreen(MainX game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = game.batch;

        // Câmera
        cameraComponent = new CameraComponent(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        posicaoCamera = new Vector2(cameraComponent.camera.position.x, cameraComponent.camera.position.y);
        cameraComponent.follow(posicaoCamera);
        cameraComponent.update();



        // Música da sala de jogo
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("gamemusic.mp3"));
        gameMusic.setLooping(true);
        gameMusic.play();

        // Parar música do menu, se necessário
        if (game.musica != null) {
            game.musica.stop();
        }

        // Carregar fundo com obstáculos e decoração
        background1 = new Background1();
        for (Plataforma p : Plataforma.gerarPlataformas()) {
            renderables.add(p);
        }
        for (Espinhos p : Espinhos.gerarEspinhos()) {
            renderables.add(p);
        }
        List<Inimigo> inimigos = Inimigo.gerarInimigos();

        for (Inimigo inimigo : inimigos) {
            renderables.add(inimigo);
            updatables.add(inimigo); // movimento do monster1

        }

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Simular movimento da câmera (substituir pelo jogador real no futuro)

        posicaoCamera.x += velocidadeCamera;  // Move  para direita
        cameraComponent.follow(posicaoCamera);
        cameraComponent.update();


        // Desenhar cenário com câmera

        for (IUpdateComponent u : updatables) {
            u.update(delta);
        }

        batch.setProjectionMatrix(cameraComponent.camera.combined);
        batch.begin();
        background1.render(batch); // fundo
        for (IRenderableComponent r : renderables) {
            r.render(batch);
        }
        batch.end();
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
        if (gameMusic != null) gameMusic.dispose();
        if (background1 != null) background1.dispose();
        for (IRenderableComponent r : renderables) {
            if (r instanceof Plataforma) {
                ((Plataforma) r).dispose();
            } else if (r instanceof Espinhos) {
                ((Espinhos) r).dispose();
            } else if (r instanceof Inimigo) {
                ((Inimigo) r).dispose();
            }
        }
    }
}
