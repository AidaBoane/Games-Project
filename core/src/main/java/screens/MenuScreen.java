package screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogicgames.MascOnX.MainX;
import systems.AudioController;

public class MenuScreen implements Screen {
    private final MainX game;

    private SpriteBatch spriteBatch;
    private Texture background;
    private Texture logoiadeTexture;
    private Texture ajudaTexture;
    private BitmapFont font;
    private FitViewport viewport;
    private Rectangle[] buttonBounds;

    public MenuScreen(MainX game) {
        this.game = game;
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(19, 10.2f);
        font = new BitmapFont();

        background = new Texture("menubackground.png");
        logoiadeTexture = new Texture("IADE.png");
       


        if (game.musica == null) {
            game.musica = Gdx.audio.newMusic(Gdx.files.internal("menumusic.mp3"));
            game.musica.setLooping(true);
            game.musica.setVolume(0.5f);

            if (AudioController.isMusicaAtivada()) {
                game.musica.play();
            }
        } else if (!game.musica.isPlaying() && AudioController.isMusicaAtivada()) {
            game.musica.play();
        }

        // Botões do menu
        buttonBounds = new Rectangle[4];
        float buttonWidth = 6f;
        float buttonHeight = 0.8f;
        float x = (viewport.getWorldWidth() - buttonWidth) / 2.050f;

        buttonBounds[0] = new Rectangle(x, 4.8f, buttonWidth, buttonHeight); // JOGAR
        buttonBounds[1] = new Rectangle(x, 3.7f, buttonWidth, buttonHeight); // OPÇÕES
        buttonBounds[2] = new Rectangle(x, 2.6f, buttonWidth, buttonHeight); // AJUDA
        buttonBounds[3] = new Rectangle(x, 1.5f, buttonWidth, buttonHeight); // SAIR
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);

        spriteBatch.begin();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        spriteBatch.draw(background, 0, 0, worldWidth, worldHeight);
        spriteBatch.draw(logoiadeTexture, 0, 9.55f, 4f, 0.7f);

        Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        viewport.unproject(mouse);

        for (int i = 0; i < buttonBounds.length; i++) {
            Rectangle bounds = buttonBounds[i];


            if (bounds.contains(mouse.x, mouse.y)) {
                spriteBatch.setColor(0, 0, 0, 0.2f);
                spriteBatch.draw(background, bounds.x, bounds.y, bounds.width, bounds.height);
                spriteBatch.setColor(Color.WHITE);

                if (Gdx.input.justTouched()) {
                    handleButtonClick(i);
                }
            }
        }

        spriteBatch.end();
    }

    private void handleButtonClick(int index) {
        switch (index) {
            case 0: // JOGAR
                if (game.musica != null) game.musica.stop();
                game.setScreen(new GameScreen(game));
                break;

            case 1: // OPÇÕES
                game.setScreen(new OpcoesScreen(game));
                break;

            case 2: // AJUDA
                game.setScreen(new AjudaScreen(game));
                break;

            case 3: // SAIR
                Gdx.app.exit();
                break;
        }
    }

    @Override public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        font.dispose();
        spriteBatch.dispose();
        background.dispose();
        logoiadeTexture.dispose();
        ajudaTexture.dispose();
    }
}
