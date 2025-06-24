package com.badlogicgames.MascOnX;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entity.ScreenGraph;
import screens.*;
import systems.AudioController;

public class MainX extends Game {
    public SpriteBatch batch;
    public Music musica;
    public ScreenGraph screenGraph;

    @Override
    public void create() {
        batch = new SpriteBatch();

        musica = Gdx.audio.newMusic(Gdx.files.internal("menumusic.mp3"));
        musica.setLooping(true);
        musica.setVolume(0.5f);

        if (AudioController.isMusicaAtivada()) {
            musica.play();
        }

       screenGraph = new ScreenGraph(this);

        screenGraph.addScreen("MenuScreen", new MenuScreen(this));
        screenGraph.addScreen("OpcoesScreen", new OpcoesScreen(this));
        screenGraph.addScreen("ComoJogarScreen", new ComoJogarScreen(this));
        screenGraph.addScreen("GameScreen", new GameScreen(this));
        screenGraph.addScreen("GameScreen2", new GameScreen2(this));
        screenGraph.addScreen("GameOverScreen", new GameOverScreen(this));

        screenGraph.connectScreens("MenuScreen", "OpcoesScreen");
        screenGraph.connectScreens("MenuScreen", "GameScreen");
        screenGraph.connectScreens("MenuScreen", "GameScreen2");

        screenGraph.connectScreens("OpcoesScreen", "MenuScreen");
        screenGraph.connectScreens("OpcoesScreen", "GameScreen");
        screenGraph.connectScreens("OpcoesScreen", "GameScreen2");

        screenGraph.connectScreens("ComoJogarScreen", "MenuScreen");

        screenGraph.connectScreens("GameScreen", "MenuScreen");
        screenGraph.connectScreens("GameScreen", "GameScreen2");
        screenGraph.connectScreens("GameScreen", "GameOverScreen");

        screenGraph.connectScreens("GameScreen2", "MenuScreen");
        screenGraph.connectScreens("GameScreen2", "GameScreen");
        screenGraph.connectScreens("GameScreen2", "GameOverScreen");

        screenGraph.connectScreens("GameOverScreen", "GameScreen");
        screenGraph.connectScreens("GameOverScreen", "GameScreen2");
        screenGraph.connectScreens("GameOverScreen", "MenuScreen");

        screenGraph.setInitialScreen("MenuScreen");
    }

    @Override
    public void dispose() {
        if (batch != null) batch.dispose();
        if (musica != null) musica.dispose();
    }
}
