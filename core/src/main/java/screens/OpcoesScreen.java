 package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogicgames.MascOnX.MainX;

public class OpcoesScreen implements Screen {

    private final MainX game;
    private Stage stage;
    private Texture backgroundTexture;

    private boolean somAtivo = true;
    private boolean musicaAtiva = true;

    private ImageButton somButton;
    private ImageButton musicaButton;
    private ImageButton ajudaButton;

    public OpcoesScreen(MainX game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backgroundTexture = new Texture("menubackground.png");

        Texture somTexture = new Texture("som.png");
        Texture musicaTexture = new Texture("musica.png");
        Texture ajudaTexture = new Texture("ajuda.png");

        somButton = new ImageButton(new TextureRegionDrawable(somTexture));
        musicaButton = new ImageButton(new TextureRegionDrawable(musicaTexture));
        ajudaButton = new ImageButton(new TextureRegionDrawable(ajudaTexture));

        // Dimensões reais da imagem dos botões
        float buttonWidth = somButton.getWidth();
        float buttonHeight = somButton.getHeight();
        float espacamento = 20f;

        float centerX = Gdx.graphics.getWidth() / 2f - buttonWidth / 2;
        float totalHeight = 3f * buttonHeight + 2 * espacamento;
        float startY = Gdx.graphics.getHeight() / 2f - totalHeight / 2f;

        somButton.setPosition(centerX, startY + (buttonHeight + espacamento) * 2);
        musicaButton.setPosition(centerX, startY + (buttonHeight + espacamento));
        ajudaButton.setPosition(centerX, startY);

        // Listeners
        somButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                somAtivo = !somAtivo;
                updateSomButton();
            }
        });

        musicaButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                musicaAtiva = !musicaAtiva;
                updateMusicaButton();
                if (musicaAtiva) {
                    game.musica.play();
                } else {
                    game.musica.pause();
                }
            }
        });

        ajudaButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ComoJogarScreen(game));
            }
        });

        updateSomButton();
        updateMusicaButton();

        stage.addActor(somButton);
        stage.addActor(musicaButton);
        stage.addActor(ajudaButton);
    }

    private void updateSomButton() {
        somButton.setColor(somAtivo ? Color.WHITE : Color.DARK_GRAY);
    }

    private void updateMusicaButton() {
        musicaButton.setColor(musicaAtiva ? Color.WHITE : Color.DARK_GRAY);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        backgroundTexture.dispose();
        ((TextureRegionDrawable) somButton.getStyle().imageUp).getRegion().getTexture().dispose();
        ((TextureRegionDrawable) musicaButton.getStyle().imageUp).getRegion().getTexture().dispose();
        ((TextureRegionDrawable) ajudaButton.getStyle().imageUp).getRegion().getTexture().dispose();

    }
}
