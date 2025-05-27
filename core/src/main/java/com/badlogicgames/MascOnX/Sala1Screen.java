package com.badlogicgames.MascOnX;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Sala1Screen implements Screen {

    private final MainX game;
    private SpriteBatch batch;
    private Texture background;
    private Player player; // classe do personagem
    private Array<Enemy> enemies; // lista de inimigos
    private FitViewport viewport;

    public Sala1Screen(MainX game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        viewport = new FitViewport(19, 10.2f);
        background = new Texture("sala1.png");

        player = new Player(1, 1); // posição inicial à esquerda
        enemies = new Array<>();
        spawnEnemies(); // adiciona inimigos à tela
    }

    private void spawnEnemies() {
        enemies.add(new Enemy(10, 1)); // ex: inimigo do lado direito
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        player.update(delta);
        player.draw(batch);

        for (Enemy enemy : enemies) {
            enemy.update(delta);
            enemy.draw(batch);

            // Colisão/ataque
            if (player.isAttacking() && player.getBounds().overlaps(enemy.getBounds())) {
                enemy.hit();
            }
        }

        batch.end();
    }

    @Override public void resize(int width, int height) { viewport.update(width, height, true); }
    @Override public void dispose() {
        batch.dispose();
        background.dispose();
        player.dispose();
        for (Enemy enemy : enemies) enemy.dispose();
    }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
