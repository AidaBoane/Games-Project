package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogicgames.MascOnX.MainX;

public class AjudaScreen implements Screen {
    private final MainX game;
    private SpriteBatch spriteBatch;
    private Texture background;
    private Texture logoIADE;
    private Texture setaVoltar;

    private Rectangle setaBounds;

    private BitmapFont font;
    private FitViewport viewport;

    public AjudaScreen(MainX game) {
        this.game = game;
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(19, 10.2f);
        font = new BitmapFont();

        background = new Texture("ajuda_background.png"); // <-- Altere para o fundo que desejar
        logoIADE = new Texture("IADE.png");
        setaVoltar = new Texture("voltar.png");

        setaBounds = new Rectangle(0.2f, 8.7f, 1f, 1f); // Mesma posição do botão voltar
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);

        Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        viewport.unproject(mouse);

        spriteBatch.begin();

        spriteBatch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        spriteBatch.draw(logoIADE, 0, 9.55f, 4f, 0.7f);
        spriteBatch.draw(setaVoltar, setaBounds.x, setaBounds.y, setaBounds.width, setaBounds.height);

        if (setaBounds.contains(mouse.x, mouse.y)) {
            spriteBatch.setColor(0, 0, 0, 0.2f);
            spriteBatch.draw(setaVoltar, setaBounds.x, setaBounds.y, setaBounds.width, setaBounds.height);
            spriteBatch.setColor(Color.WHITE);

            if (Gdx.input.justTouched()) {
                game.setScreen(new MenuScreen(game));
            }
        }

        spriteBatch.end();
    }

    @Override public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        spriteBatch.dispose();
        background.dispose();
        logoIADE.dispose();
        setaVoltar.dispose();
        font.dispose();
    }
}
