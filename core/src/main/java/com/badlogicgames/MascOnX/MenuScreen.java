package com.badlogicgames.MascOnX;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** First screen of the application. Displayed after the application is created. */
public class MenuScreen implements Screen {

    private final MainX game;
    private SpriteBatch batch;
    private Texture background;
    private Texture axe;
    private BitmapFont font;
    Music music;
    Texture logoiade;

    private final String[] menuItems = {"Jogar", "Opções", "Sair"};
    private int selectedIndex = 0;


    public MenuScreen(MainX mainX) {
        this.game = mainX;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture("menu_background.png"); // substitua pela sua imagem
        axe = new Texture("axe.png"); // imagem do machado
        font = new BitmapFont(); // Fonte padrão, sem dependência externa

        // Prepare your screen here.
        Gdx.input.setInputProcessor(new InputAdapter(){

    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP) {
            selectedIndex = (selectedIndex + menuItems.length - 1) % menuItems.length;
        } else if (keycode == Input.Keys.DOWN) {
            selectedIndex = (selectedIndex + 1) % menuItems.length;
        } else if (keycode == Input.Keys.ENTER) {
            handleSelection();
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        int height = Gdx.graphics.getHeight();
        for (int i = 0; i < menuItems.length; i++) {
            int itemY = height / 2 + (menuItems.length - i - 1) * 40;
            if (screenY > itemY - 20 && screenY < itemY + 20) {
                selectedIndex = i;
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        handleSelection();
        return true;
    }

    private void handleSelection() {
        switch (selectedIndex) {
            case 0:
                System.out.println("Jogar");
                break; // game.setScreen(new GameScreen(game));
            case 1:
                System.out.println("Opções");
                break; // game.setScreen(new OptionsScreen(game));
            case 2:
                Gdx.app.exit();
                break;
        }
    }

});
    }
    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.


        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            selectedIndex = (selectedIndex + menuItems.length - 1) % menuItems.length;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            selectedIndex = (selectedIndex + 1) % menuItems.length;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            switch (selectedIndex) {
                case 0:
                    System.out.println("Iniciar jogo...");
                    break; // game.setScreen(new GameScreen(game));
                case 1:
                    System.out.println("Abrir opções...");
                    break; // game.setScreen(new OptionsScreen(game));
                case 2:
                    Gdx.app.exit();
                    break;
            }

        }
        // Limpar tela
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (int i = 0; i < menuItems.length; i++) {
            String prefix = (i == selectedIndex) ? " " : "   ";
            font.draw(batch, prefix + menuItems[i], 100, 300 - i * 40);
        }
        batch.end();


}
    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.

        batch.dispose();
        font.dispose();

    }
}
