package screens;


import java.util.List;
import java.util.Map;

import com.badlogicgames.MascOnX.*;
import screens.OpcoesScreen;

public class ScreenManager {

    private final MainX game;
    private final Map<String, List<String>> graph;
    private String currentScreenName;

    public ScreenManager(MainX game, Map<String, List<String>> graph, String initialScreen) {
        this.game = game;
        this.graph = graph;
        this.currentScreenName = initialScreen;
    }

    public boolean canGoTo(String targetScreen) {
        return graph.get(currentScreenName).contains(targetScreen);
    }

    public void switchTo(String targetScreen) {
        if (canGoTo(targetScreen)) {
            switch (targetScreen) {
                case "MenuScreen":
                    game.setScreen(new MenuScreen(game));
                    break;
                case "OpcoesScreen":
                    game.setScreen(new OpcoesScreen(game));
                    break;
                case "ComoJogarScreen":
                    game.setScreen(new ComoJogarScreen(game));
                    break;
                case "GameScreen":
                    game.setScreen(new GameScreen(game));
                    break;
                case "GameScreen2":
                    game.setScreen(new GameScreen2(game));
                    break;
                case "GameScreen3":
                    game.setScreen(new GameScreen3(game));
                    break;
                case "GameOverScreen":
                    game.setScreen(new GameOverScreen(game));
                    break;
            }
            currentScreenName = targetScreen;
        } else {
            System.out.println("Transição não permitida de " + currentScreenName + " para " + targetScreen);
        }
    }
}

