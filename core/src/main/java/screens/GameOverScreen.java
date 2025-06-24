package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogicgames.MascOnX.MainX;

public class GameOverScreen implements Screen {

    private final MainX game;
    private SpriteBatch batch;
    private Texture gameOverTexture;
    private Texture exitTexture;

    public GameOverScreen(MainX game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = game.batch;
        gameOverTexture = new Texture(Gdx.files.internal("gameover.png"));
        exitTexture = new Texture(Gdx.files.internal("exit.png"));
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        float centerX = (Gdx.graphics.getWidth() - gameOverTexture.getWidth()) / 2f;
        float centerY = (Gdx.graphics.getHeight() - gameOverTexture.getHeight()) / 2f;
        batch.draw(gameOverTexture, centerX, centerY);


        float exitX = Gdx.graphics.getWidth() - exitTexture.getWidth() - 20;
        float exitY = 20;
        batch.draw(exitTexture, exitX, exitY);

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            game.setScreen(new MenuScreen(game));
        }
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
        gameOverTexture.dispose();
        exitTexture.dispose();
    }
}
