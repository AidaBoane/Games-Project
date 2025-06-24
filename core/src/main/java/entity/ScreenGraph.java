// ScreenGraph.java
package entity;

import com.badlogic.gdx.Screen;
import com.badlogicgames.MascOnX.MainX;

import java.util.HashMap;
import java.util.Map;

public class ScreenGraph {
    public static class ScreenNode {
        public final Screen screen;
        public final Map<String, ScreenNode> neighbors = new HashMap<>();

        public ScreenNode(Screen screen) {
            this.screen = screen;
        }

        public void addNeighbor(String name, ScreenNode node) {
            neighbors.put(name, node);
        }
    }

    private final Map<String, ScreenNode> nodes = new HashMap<>();
    private ScreenNode currentNode;
    private final MainX game;

    public ScreenGraph(MainX game) {
        this.game = game;
    }

    public void addScreen(String name, Screen screen) {
        nodes.put(name, new ScreenNode(screen));
    }

    public void connectScreens(String from, String to) {
        ScreenNode fromNode = nodes.get(from);
        ScreenNode toNode = nodes.get(to);
        if (fromNode != null && toNode != null) {
            fromNode.addNeighbor(to, toNode);
        }
    }

    public void setInitialScreen(String name) {
        currentNode = nodes.get(name);
        if (currentNode != null) {
            game.setScreen(currentNode.screen);
        }
    }

    public void navigateTo(String screenName) {
        ScreenNode next = currentNode.neighbors.get(screenName);
        if (next != null) {
            currentNode = next;
            game.setScreen(next.screen);
        } else {
            System.out.println("[ERRO] Transição inválida de " + getCurrentScreenName() + " para " + screenName);
        }
    }

    public String getCurrentScreenName() {
        for (Map.Entry<String, ScreenNode> entry : nodes.entrySet()) {
            if (entry.getValue() == currentNode) return entry.getKey();
        }
        return null;
    }

    public Screen getCurrentScreen() {
        return currentNode.screen;
    }
}

