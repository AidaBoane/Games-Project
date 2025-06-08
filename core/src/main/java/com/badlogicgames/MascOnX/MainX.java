package com.badlogicgames.MascOnX;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screens.GameScreen;
import screens.MenuScreen;
import screens.OpcoesScreen;
import screens.ScreenManager;

public class MainX extends Game {
    public ScreenManager screenManager;
    public SpriteBatch batch;
    public Music musica;  // música do menu

    @Override
    public void create() {
        batch = new SpriteBatch();

        // Inicia com a música do menu
        musica = Gdx.audio.newMusic(Gdx.files.internal("menumusic.mp3"));
        musica.setLooping(true);
        musica.play();

        // Exemplo: se quiser usar menu no futuro com navegação via screenManager:
        // Map<String, List<String>> graph = GraphLoader.loadGraph("assets/game_screen_graph.json");
        // screenManager = new ScreenManager(this, graph, "MenuScreen");
        // screenManager.switchTo("MenuScreen");

        // Inicia diretamente na primeira sala
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        if (batch != null) batch.dispose();
        if (musica != null) musica.dispose();
    }
}
