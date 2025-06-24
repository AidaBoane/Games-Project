package systems;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class GameAssetManager {
    private final AssetManager manager;

    public GameAssetManager() {
        manager = new AssetManager();
    }

    public void loadAllAssets() {
        // Texturas do jogador
        for (String anim : new String[]{"Idle_", "Run_", "Jump_", "Jump_Throw_", "Slide_", "Dead_"}) {
            for (int i = 0; i < 10; i++) {
                String path = "player/" + String.format(anim + "%03d.png", i);
                manager.load(path, Texture.class);
            }
        }


        manager.load("player/kunai.png", Texture.class);


        manager.load("background1.png", Texture.class);
        manager.load("background2.png", Texture.class);
        manager.load("menubackground.png", Texture.class);
        manager.load("opcoesbackground.png", Texture.class);
        manager.load("IADE.png", Texture.class);
        manager.load("gameover.png", Texture.class);
        manager.load("exit.png", Texture.class);
        manager.load("voltar.png", Texture.class);


        manager.load("monster1.png", Texture.class);
        manager.load("espinhos.png", Texture.class);
        manager.load("plataforma_media.png", Texture.class);
        manager.load("skeletonpendurado.png", Texture.class);
        manager.load("fire.png", Texture.class);


        manager.load("menumusic.mp3", Music.class);
        manager.load("gamemusic.mp3", Music.class);
    }

    public void updateLoading() {
        manager.update();
    }

    public boolean isFinished() {
        return manager.update();
    }

    public <T> T get(String path, Class<T> type) {
        return manager.get(path, type);
    }

    public void dispose() {
        manager.dispose();
    }

    public AssetManager getInternalManager() {
        return manager;
    }
}
